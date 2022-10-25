package kitchenpos.deliveryorder;

import kitchenpos.DeliverOrderFixtures;
import kitchenpos.deliveryorder.application.DeliverOrderService;
import kitchenpos.deliveryorder.domain.DeliveryOrder;
import kitchenpos.deliveryorder.domain.DeliveryOrderLineItem;
import kitchenpos.deliveryorder.domain.OrderStatus;
import kitchenpos.deliveryorder.domain.OrderType;
import kitchenpos.deliveryorder.repository.DeliveryOrderRepository;
import kitchenpos.menu.InMemoryMenuRepository;
import kitchenpos.menu.menu.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.math.BigDecimal;
import java.util.*;

import static kitchenpos.DeliverOrderFixtures.order;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeliveryDeliverEatInOrderServiceTest {
    private DeliveryOrderRepository deliveryOrderRepository;
    private MenuRepository menuRepository;
    private FakeKitchenridersClient kitchenridersClient;
    private DeliverOrderService deliverOrderService;

    @BeforeEach
    void setUp() {
        deliveryOrderRepository = new InMemoryDeliveryOrderRepository();
        menuRepository = new InMemoryMenuRepository();
        kitchenridersClient = new FakeKitchenridersClient();
        deliverOrderService = new DeliverOrderService(deliveryOrderRepository, menuRepository, kitchenridersClient);
    }

    @DisplayName("1개 이상의 등록된 메뉴로 배달 주문을 등록할 수 있다.")
    @Test
    void createDeliveryOrder() {
        final UUID menuId = menuRepository.save(DeliverOrderFixtures.menu(19_000L, true, DeliverOrderFixtures.menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(
                OrderType.DELIVERY, "서울시 송파구 위례성대로 2", createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        final DeliveryOrder actual = deliverOrderService.create(expected);
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
        final UUID menuId = menuRepository.save(DeliverOrderFixtures.menu(19_000L, true, DeliverOrderFixtures.menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(type, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> deliverOrderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<DeliveryOrderLineItem> deliveryOrderLineItems) {
        final DeliveryOrder expected = createOrderRequest(OrderType.TAKEOUT, deliveryOrderLineItems);
        assertThatThrownBy(() -> deliverOrderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static List<Arguments> orderLineItems() {
        return Arrays.asList(
                null,
                Arguments.of(Collections.emptyList()),
                Arguments.of(Arrays.asList(createOrderLineItemRequest(DeliverOrderFixtures.INVALID_ID, 19_000L, 3L)))
        );
    }

    @DisplayName("매장 주문을 제외한 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.")
    @ValueSource(longs = -1L)
    @ParameterizedTest
    void createWithoutDeliveryOrder(final long quantity) {
        final UUID menuId = menuRepository.save(DeliverOrderFixtures.menu(19_000L, true, DeliverOrderFixtures.menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(
                OrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertThatThrownBy(() -> deliverOrderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배달 주소가 올바르지 않으면 배달 주문을 등록할 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void create(final String deliveryAddress) {
        final UUID menuId = menuRepository.save(DeliverOrderFixtures.menu(19_000L, true, DeliverOrderFixtures.menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(
                OrderType.DELIVERY, deliveryAddress, createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        assertThatThrownBy(() -> deliverOrderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(DeliverOrderFixtures.menu(19_000L, false, DeliverOrderFixtures.menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(OrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> deliverOrderService.create(expected))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(DeliverOrderFixtures.menu(19_000L, true, DeliverOrderFixtures.menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(OrderType.TAKEOUT, createOrderLineItemRequest(menuId, 16_000L, 3L));
        assertThatThrownBy(() -> deliverOrderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = deliveryOrderRepository.save(order(OrderStatus.WAITING, "주소")).getId();
        final DeliveryOrder actual = deliverOrderService.accept(orderId);
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.ACCEPTED);
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final OrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(order(status, "주소")).getId();
        assertThatThrownBy(() -> deliverOrderService.accept(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("배달 주문을 접수되면 배달 대행사를 호출한다.")
    @Test
    void acceptDeliveryOrder() {
        final UUID orderId = deliveryOrderRepository.save(order(OrderStatus.WAITING, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = deliverOrderService.accept(orderId);
        assertAll(
                () -> assertThat(actual.getStatus()).isEqualTo(OrderStatus.ACCEPTED),
                () -> assertThat(kitchenridersClient.getOrderId()).isEqualTo(orderId),
                () -> assertThat(kitchenridersClient.getDeliveryAddress()).isEqualTo("서울시 송파구 위례성대로 2")
        );
    }

    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final UUID orderId = deliveryOrderRepository.save(order(OrderStatus.ACCEPTED, "주소")).getId();
        final DeliveryOrder actual = deliverOrderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final OrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(order(status, "주소")).getId();
        assertThatThrownBy(() -> deliverOrderService.serve(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달한다.")
    @Test
    void startDelivery() {
        final UUID orderId = deliveryOrderRepository.save(order(OrderStatus.SERVED, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = deliverOrderService.startDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.DELIVERING);
    }

    @DisplayName("서빙된 주문만 배달할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void startDelivery(final OrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(order(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> deliverOrderService.startDelivery(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달 완료한다.")
    @Test
    void completeDelivery() {
        final UUID orderId = deliveryOrderRepository.save(order(OrderStatus.DELIVERING, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = deliverOrderService.completeDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.DELIVERED);
    }

    @DisplayName("배달 중인 주문만 배달 완료할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "DELIVERING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDelivery(final OrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(order(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> deliverOrderService.completeDelivery(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final DeliveryOrder expected = deliveryOrderRepository.save(order(OrderStatus.DELIVERED, "서울시 송파구 위례성대로 2"));
        final DeliveryOrder actual = deliverOrderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @DisplayName("배달 주문의 경우 배달 완료된 주문만 완료할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "DELIVERED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDeliveryOrder(final OrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(order(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> deliverOrderService.complete(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        deliveryOrderRepository.save(order(OrderStatus.SERVED, "주소"));
        deliveryOrderRepository.save(order(OrderStatus.DELIVERED, "서울시 송파구 위례성대로 2"));
        final List<DeliveryOrder> actual = deliverOrderService.findAll();
        assertThat(actual).hasSize(2);
    }

    private DeliveryOrder createOrderRequest(
            final OrderType type,
            final String deliveryAddress,
            final DeliveryOrderLineItem... deliveryOrderLineItems
    ) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setType(type);
        deliveryOrder.setDeliveryAddress(deliveryAddress);
        deliveryOrder.setOrderLineItems(Arrays.asList(deliveryOrderLineItems));
        return deliveryOrder;
    }

    private DeliveryOrder createOrderRequest(final OrderType orderType, final DeliveryOrderLineItem... deliveryOrderLineItems) {
        return createOrderRequest(orderType, Arrays.asList(deliveryOrderLineItems));
    }

    private DeliveryOrder createOrderRequest(final OrderType orderType, final List<DeliveryOrderLineItem> deliveryOrderLineItems) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setType(orderType);
        deliveryOrder.setOrderLineItems(deliveryOrderLineItems);
        return deliveryOrder;
    }

    private DeliveryOrder createOrderRequest(
            final OrderType type,
            final UUID orderTableId,
            final DeliveryOrderLineItem... deliveryOrderLineItems
    ) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setType(type);
        deliveryOrder.setOrderLineItems(Arrays.asList(deliveryOrderLineItems));
        return deliveryOrder;
    }

    private static DeliveryOrderLineItem createOrderLineItemRequest(final UUID menuId, final long price, final long quantity) {
        final DeliveryOrderLineItem deliveryOrderLineItem = new DeliveryOrderLineItem();
        deliveryOrderLineItem.setSeq(new Random().nextLong());
        deliveryOrderLineItem.setMenuId(menuId);
        deliveryOrderLineItem.setPrice(BigDecimal.valueOf(price));
        deliveryOrderLineItem.setQuantity(quantity);
        return deliveryOrderLineItem;
    }
}
