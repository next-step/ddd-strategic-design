package kitchenpos.deliveryorder.application;

import kitchenpos.application.InMemoryMenuRepository;
import kitchenpos.application.InMemoryOrderRepository;
import kitchenpos.deliveryorder.infra.FakeKitchenridersClient;
import kitchenpos.domain.DeliveryOrderRepository;
import kitchenpos.domain.OrderStatus;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.order.common.domain.OrderLineItem;
import kitchenpos.order.common.domain.OrderType;
import kitchenpos.order.deliveryorder.application.DeliveryOrderService;
import kitchenpos.order.deliveryorder.domain.DeliveryOrder;
import kitchenpos.order.deliveryorder.domain.DeliveryOrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.math.BigDecimal;
import java.util.*;

import static kitchenpos.Fixtures.eatInOrder;
import static kitchenpos.deliveryorder.fixture.DeliveryOrderFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeliveryOrderServiceTest {
    private DeliveryOrderRepository deliveryOrderRepository;
    private MenuRepository menuRepository;
    private FakeKitchenridersClient kitchenridersClient;
    private DeliveryOrderService deliveryOrderService;

    @BeforeEach
    void setUp() {
        deliveryOrderRepository = new InMemoryOrderRepository();
        menuRepository = new InMemoryMenuRepository();
        kitchenridersClient = new FakeKitchenridersClient();
        deliveryOrderService = new DeliveryOrderService(deliveryOrderRepository, menuRepository, kitchenridersClient);
    }

    @DisplayName("1개 이상의 등록된 메뉴로 배달 주문을 등록할 수 있다.")
    @Test
    void createDeliveryOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final DeliveryOrder expected = createDeliveryOrderRequest(
                OrderType.DELIVERY, "서울시 송파구 위례성대로 2", createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        final DeliveryOrder actual = deliveryOrderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getType()).isEqualTo(expected.getType()),
                () -> assertThat(actual.getStatus()).isEqualTo(OrderStatus.WAITING),
                () -> assertThat(actual.getOrderDateTime()).isNotNull(),
                () -> assertThat(actual.getOrderLineItems()).hasSize(1),
                () -> assertThat(actual.getDeliveryAddress()).isEqualTo(expected.getDeliveryAddress())
        );
    }

    @DisplayName("주문 유형이 올바르지 않으면 등록할 수 없다.")
    @NullSource
    @ParameterizedTest
    void create(final OrderType type) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final DeliveryOrder expected = createDeliveryOrderRequest(
                OrderType.DELIVERY, "서울시 송파구 위례성대로 2", createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<OrderLineItem> orderLineItems) {
        final DeliveryOrder expected = createDeliveryOrderRequest(OrderType.DELIVERY, "서울시 송파구 위례성대로 2", orderLineItems);
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static List<Arguments> orderLineItems() {
        return Arrays.asList(
                null,
                Arguments.of(Collections.emptyList()),
                Arguments.of(Arrays.asList(createOrderLineItemRequest(INVALID_ID, 19_000L, 3L)))
        );
    }

    @DisplayName("배달 주소가 올바르지 않으면 배달 주문을 등록할 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void create(final String deliveryAddress) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final DeliveryOrder expected = createDeliveryOrderRequest(
                OrderType.DELIVERY, deliveryAddress, createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, false, menuProduct())).getId();
        final DeliveryOrder expected = createDeliveryOrderRequest(
                OrderType.DELIVERY, "서울시 송파구 위례성대로 2", createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final DeliveryOrder expected = createDeliveryOrderRequest(
                OrderType.DELIVERY, "서울시 송파구 위례성대로 2", createOrderLineItemRequest(menuId, 16_000L, 3L)
        );
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = deliveryOrderRepository.save(
                        deliveryOrder(DeliveryOrderStatus.WAITING, "서울시 송파구 위례성대로 2"))
                .getId();
        final DeliveryOrder actual = deliveryOrderService.accept(orderId);
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.ACCEPTED);
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(
                        deliveryOrder(status, "서울시 송파구 위례성대로 2"))
                .getId();
        assertThatThrownBy(() -> deliveryOrderService.accept(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("배달 주문을 접수되면 배달 대행사를 호출한다.")
    @Test
    void acceptDeliveryOrder() {
        final UUID orderId = deliveryOrderRepository.save(
                        deliveryOrder(DeliveryOrderStatus.WAITING, "서울시 송파구 위례성대로 2"))
                .getId();
        final DeliveryOrder actual = deliveryOrderService.accept(orderId);
        assertAll(
                () -> assertThat(actual.getStatus()).isEqualTo(OrderStatus.ACCEPTED),
                () -> assertThat(kitchenridersClient.getOrderId()).isEqualTo(orderId),
                () -> assertThat(kitchenridersClient.getDeliveryAddress()).isEqualTo("서울시 송파구 위례성대로 2")
        );
    }

    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final UUID orderId = deliveryOrderRepository.save(
                        deliveryOrder(DeliveryOrderStatus.WAITING, "서울시 송파구 위례성대로 2"))
                .getId();
        final DeliveryOrder actual = deliveryOrderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> deliveryOrderService.serve(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달한다.")
    @Test
    void startDelivery() {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.PICKEDUP, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = deliveryOrderService.startDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.DELIVERING);
    }

    @DisplayName("배달 주문만 배달할 수 있다.")
    @Test
    void startDeliveryWithoutDeliveryOrder() {
        final UUID orderId = deliveryOrderRepository.save(
                        deliveryOrder(DeliveryOrderStatus.PICKEDUP, "서울시 송파구 위례성대로 2"))
                .getId();
        assertThatThrownBy(() -> deliveryOrderService.startDelivery(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("서빙된 주문만 배달할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "PICKEDUP", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void startDelivery(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(
                        deliveryOrder(DeliveryOrderStatus.PICKEDUP, "서울시 송파구 위례성대로 2"))
                .getId();
        assertThatThrownBy(() -> deliveryOrderService.startDelivery(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달 완료한다.")
    @Test
    void completeDelivery() {
        final UUID orderId = deliveryOrderRepository.save(
                        deliveryOrder(DeliveryOrderStatus.DELIVERING, "서울시 송파구 위례성대로 2"))
                .getId();
        final DeliveryOrder actual = deliveryOrderService.completeDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.DELIVERED);
    }

    @DisplayName("배달 중인 주문만 배달 완료할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "DELIVERING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDelivery(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(
                        deliveryOrder(status, "서울시 송파구 위례성대로 2"))
                .getId();
        assertThatThrownBy(() -> deliveryOrderService.completeDelivery(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final DeliveryOrder expected = deliveryOrderRepository.save(
                        deliveryOrder(DeliveryOrderStatus.DELIVERED, "서울시 송파구 위례성대로 2"));
        final DeliveryOrder actual = deliveryOrderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @DisplayName("배달 주문의 경우 배달 완료된 주문만 완료할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "DELIVERED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDeliveryOrder(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(
                deliveryOrder(status, "서울시 송파구 위례성대로 2"))
                .getId();
        assertThatThrownBy(() -> deliveryOrderService.complete(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.WAITING, "서울시 송파구 위례성대로 2"));
        deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.DELIVERING, "서울시 송파구 위례성대로 2"));
        final List<DeliveryOrder> actual = deliveryOrderService.findAll();
        assertThat(actual).hasSize(2);
    }

    private DeliveryOrder createDeliveryOrderRequest(
            final OrderType type,
            final String deliveryAddress,
            final OrderLineItem... orderLineItems
    ) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setType(type);
        deliveryOrder.setDeliveryAddress(deliveryAddress);
        deliveryOrder.setOrderLineItems(Arrays.asList(orderLineItems));
        return deliveryOrder;
    }

    private DeliveryOrder createDeliveryOrderRequest(
            final OrderType type,
            final String deliveryAddress,
            final List<OrderLineItem> orderLineItems
    ) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setType(type);
        deliveryOrder.setDeliveryAddress(deliveryAddress);
        deliveryOrder.setOrderLineItems(orderLineItems);
        return deliveryOrder;
    }

    private static OrderLineItem createOrderLineItemRequest(final UUID menuId, final long price, final long quantity) {
        final OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSeq(new Random().nextLong());
        orderLineItem.setMenuId(menuId);
        orderLineItem.setPrice(BigDecimal.valueOf(price));
        orderLineItem.setQuantity(quantity);
        return orderLineItem;
    }
}
