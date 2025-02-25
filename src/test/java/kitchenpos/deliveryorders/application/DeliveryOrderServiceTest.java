package kitchenpos.deliveryorders.application;

import static kitchenpos.Fixtures.INVALID_ID;
import static kitchenpos.Fixtures.deliveryOrder;
import static kitchenpos.Fixtures.menu;
import static kitchenpos.Fixtures.menuProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import kitchenpos.deliveryorders.application.out.DeliveryOrderRepository;
import kitchenpos.deliveryorders.domain.DeliveryOrder;
import kitchenpos.deliveryorders.domain.DeliveryOrderLineItem;
import kitchenpos.deliveryorders.domain.DeliveryOrderStatus;
import kitchenpos.eatinorders.application.FakeKitchenridersClient;
import kitchenpos.menus.application.InMemoryMenuRepository;
import kitchenpos.menus.application.out.MenuRepository;
import kitchenpos.shared.domain.OrderType;
import kitchenpos.takeoutorders.application.InMemoryTakeOutOrderRepository;
import kitchenpos.takeoutorders.application.out.TakeOutOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

class DeliveryOrderServiceTest {

    private DeliveryOrderRepository deliveryOrderRepository;
    private TakeOutOrderRepository takeOutOrderRepository;
    private MenuRepository menuRepository;
    private FakeKitchenridersClient kitchenridersClient;
    private DeliveryOrderService orderService;

    @BeforeEach
    void setUp() {
        deliveryOrderRepository = new InMemoryDeliveryOrderRepository();
        menuRepository = new InMemoryMenuRepository();
        takeOutOrderRepository = new InMemoryTakeOutOrderRepository();
        kitchenridersClient = new FakeKitchenridersClient();
        orderService = new DeliveryOrderService(deliveryOrderRepository, menuRepository,
            kitchenridersClient);
    }

    @DisplayName("1개 이상의 등록된 메뉴로 배달 주문을 등록할 수 있다.")
    @Test
    void createDeliveryOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(
            OrderType.DELIVERY, "서울시 송파구 위례성대로 2", createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        final DeliveryOrder actual = orderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getType()).isEqualTo(expected.getType()),
            () -> assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.WAITING),
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
        final DeliveryOrder expected = createOrderRequest(type,
            createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> orderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<DeliveryOrderLineItem> DeliveryOrderLineItems) {
        final DeliveryOrder expected = createOrderRequest(OrderType.TAKEOUT,
            DeliveryOrderLineItems);
        assertThatThrownBy(() -> orderService.create(expected))
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
        final DeliveryOrder expected = createOrderRequest(
            OrderType.DELIVERY, deliveryAddress, createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        assertThatThrownBy(() -> orderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, false, menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(OrderType.TAKEOUT,
            createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> orderService.create(expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(OrderType.TAKEOUT,
            createOrderLineItemRequest(menuId, 16_000L, 3L));
        assertThatThrownBy(() -> orderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = deliveryOrderRepository.save(
            deliveryOrder(DeliveryOrderStatus.WAITING, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = orderService.accept(orderId);
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.ACCEPTED);
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "서울시 송파구 위례성대로 2"))
            .getId();
        assertThatThrownBy(() -> orderService.accept(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("배달 주문을 접수되면 배달 대행사를 호출한다.")
    @Test
    void acceptDeliveryOrder() {
        final UUID orderId = deliveryOrderRepository.save(
            deliveryOrder(DeliveryOrderStatus.WAITING, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = orderService.accept(orderId);
        assertAll(
            () -> assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.ACCEPTED),
            () -> assertThat(kitchenridersClient.getOrderId()).isEqualTo(orderId),
            () -> assertThat(kitchenridersClient.getDeliveryAddress()).isEqualTo("서울시 송파구 위례성대로 2")
        );
    }

    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final UUID orderId = deliveryOrderRepository.save(
            deliveryOrder(DeliveryOrderStatus.ACCEPTED, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = orderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "서울시 송파구 위례성대로 2"))
            .getId();
        assertThatThrownBy(() -> orderService.serve(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달한다.")
    @Test
    void startDelivery() {
        final UUID orderId = deliveryOrderRepository.save(
            deliveryOrder(DeliveryOrderStatus.SERVED, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = orderService.startDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.DELIVERING);
    }

    @DisplayName("서빙된 주문만 배달할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void startDelivery(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "서울시 송파구 위례성대로 2"))
            .getId();
        assertThatThrownBy(() -> orderService.startDelivery(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달 완료한다.")
    @Test
    void completeDelivery() {
        final UUID orderId = deliveryOrderRepository.save(
            deliveryOrder(DeliveryOrderStatus.DELIVERING, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = orderService.completeDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.DELIVERED);
    }

    @DisplayName("배달 중인 주문만 배달 완료할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "DELIVERING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDelivery(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "서울시 송파구 위례성대로 2"))
            .getId();
        assertThatThrownBy(() -> orderService.completeDelivery(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final DeliveryOrder expected = deliveryOrderRepository.save(
            deliveryOrder(DeliveryOrderStatus.DELIVERED, "서울시 송파구 위례성대로 2"));
        final DeliveryOrder actual = orderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.COMPLETED);
    }

    @DisplayName("배달 주문의 경우 배달 완료된 주문만 완료할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "DELIVERED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDeliveryOrder(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "서울시 송파구 위례성대로 2"))
            .getId();
        assertThatThrownBy(() -> orderService.complete(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        deliveryOrderRepository.save(
            deliveryOrder(DeliveryOrderStatus.DELIVERED, "서울시 송파구 위례성대로 2"));
        deliveryOrderRepository.save(
            deliveryOrder(DeliveryOrderStatus.DELIVERED, "서울시 송파구 위례성대로 3"));
        final List<DeliveryOrder> actual = orderService.findAll();
        assertThat(actual).hasSize(2);
    }

    private DeliveryOrder createOrderRequest(
        final OrderType type,
        final String deliveryAddress,
        final DeliveryOrderLineItem... DeliveryOrderLineItems
    ) {
        final DeliveryOrder DeliveryOrder = new DeliveryOrder();
        DeliveryOrder.setType(type);
        DeliveryOrder.setDeliveryAddress(deliveryAddress);
        DeliveryOrder.setOrderLineItems(Arrays.asList(DeliveryOrderLineItems));
        return DeliveryOrder;
    }

    private DeliveryOrder createOrderRequest(final OrderType orderType,
        final DeliveryOrderLineItem... DeliveryOrderLineItems) {
        return createOrderRequest(orderType, Arrays.asList(DeliveryOrderLineItems));
    }

    private DeliveryOrder createOrderRequest(final OrderType orderType,
        final List<DeliveryOrderLineItem> DeliveryOrderLineItems) {
        final DeliveryOrder DeliveryOrder = new DeliveryOrder();
        DeliveryOrder.setType(orderType);
        DeliveryOrder.setOrderLineItems(DeliveryOrderLineItems);
        return DeliveryOrder;
    }

    private static DeliveryOrderLineItem createOrderLineItemRequest(final UUID menuId,
        final long price, final long quantity) {
        final DeliveryOrderLineItem DeliveryOrderLineItem = new DeliveryOrderLineItem();
        DeliveryOrderLineItem.setSeq(new Random().nextLong());
        DeliveryOrderLineItem.setMenuId(menuId);
        DeliveryOrderLineItem.setPrice(BigDecimal.valueOf(price));
        DeliveryOrderLineItem.setQuantity(quantity);
        return DeliveryOrderLineItem;
    }
}
