package kitchenpos.delivery_order.application;

import static kitchenpos.Fixtures.INVALID_ID;
import static kitchenpos.Fixtures.deliveryOrder;
import static kitchenpos.Fixtures.deliveryOrderTable;
import static kitchenpos.Fixtures.menu;
import static kitchenpos.Fixtures.menuProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import kitchenpos.delivery_order.FakeKitchenridersClient;
import kitchenpos.delivery_order.InMemoryDeliveryOrderRepository;
import kitchenpos.delivery_order.InMemoryDeliveryOrderTableRepository;
import kitchenpos.delivery_order.domain.DeliveryOrder;
import kitchenpos.delivery_order.domain.DeliveryOrderLineItem;
import kitchenpos.delivery_order.domain.DeliveryOrderRepository;
import kitchenpos.delivery_order.domain.DeliveryOrderStatus;
import kitchenpos.delivery_order.domain.DeliveryOrderTable;
import kitchenpos.delivery_order.domain.DeliveryOrderTableRepository;
import kitchenpos.delivery_order.domain.DeliveryOrderType;
import kitchenpos.menu.InMemoryMenuRepository;
import kitchenpos.menu.domain.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class DeliveryOrderServiceTest {
    private DeliveryOrderRepository deliveryOrderRepository;
    private MenuRepository menuRepository;
    private DeliveryOrderTableRepository deliveryOrderTableRepository;
    private FakeKitchenridersClient kitchenridersClient;
    private DeliveryOrderService deliveryOrderService;

    @BeforeEach
    void setUp() {
        deliveryOrderRepository = new InMemoryDeliveryOrderRepository();
        menuRepository = new InMemoryMenuRepository();
        deliveryOrderTableRepository = new InMemoryDeliveryOrderTableRepository();
        kitchenridersClient = new FakeKitchenridersClient();
        deliveryOrderService = new DeliveryOrderService(deliveryOrderRepository, menuRepository,
            deliveryOrderTableRepository, kitchenridersClient);
    }

    @DisplayName("1개 이상의 등록된 메뉴로 배달 주문을 등록할 수 있다.")
    @Test
    void createDeliveryOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(
            DeliveryOrderType.DELIVERY, "서울시 송파구 위례성대로 2", createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        final DeliveryOrder actual = deliveryOrderService.create(expected);
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

    @DisplayName("1개 이상의 등록된 메뉴로 포장 주문을 등록할 수 있다.")
    @Test
    void createTakeoutOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(DeliveryOrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, 3L));
        final DeliveryOrder actual = deliveryOrderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getType()).isEqualTo(expected.getType()),
            () -> assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.WAITING),
            () -> assertThat(actual.getOrderDateTime()).isNotNull(),
            () -> assertThat(actual.getOrderLineItems()).hasSize(1)
        );
    }

    @DisplayName("1개 이상의 등록된 메뉴로 매장 주문을 등록할 수 있다.")
    @Test
    void createEatInOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final UUID orderTableId = deliveryOrderTableRepository.save(deliveryOrderTable(true, 4)).getId();
        final DeliveryOrder expected = createOrderRequest(DeliveryOrderType.EAT_IN, orderTableId, createOrderLineItemRequest(menuId, 19_000L, 3L));
        final DeliveryOrder actual = deliveryOrderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getType()).isEqualTo(expected.getType()),
            () -> assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.WAITING),
            () -> assertThat(actual.getOrderDateTime()).isNotNull(),
            () -> assertThat(actual.getOrderLineItems()).hasSize(1),
            () -> assertThat(actual.getOrderTable().getId()).isEqualTo(expected.getOrderTableId())
        );
    }

    @DisplayName("주문 유형이 올바르지 않으면 등록할 수 없다.")
    @NullSource
    @ParameterizedTest
    void create(final DeliveryOrderType type) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(type, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<DeliveryOrderLineItem> deliveryOrderLineItems) {
        final DeliveryOrder expected = createOrderRequest(DeliveryOrderType.TAKEOUT, deliveryOrderLineItems);
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

    @DisplayName("매장 주문은 주문 항목의 수량이 0 미만일 수 있다.")
    @ValueSource(longs = -1L)
    @ParameterizedTest
    void createDeliveryOrder(final long quantity) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final UUID orderTableId = deliveryOrderTableRepository.save(deliveryOrderTable(true, 4)).getId();
        final DeliveryOrder expected = createOrderRequest(
            DeliveryOrderType.EAT_IN, orderTableId, createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertDoesNotThrow(() -> deliveryOrderService.create(expected));
    }

    @DisplayName("매장 주문을 제외한 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.")
    @ValueSource(longs = -1L)
    @ParameterizedTest
    void createWithoutDeliveryOrder(final long quantity) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(
            DeliveryOrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배달 주소가 올바르지 않으면 배달 주문을 등록할 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void create(final String deliveryAddress) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(
            DeliveryOrderType.DELIVERY, deliveryAddress, createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈 테이블에는 매장 주문을 등록할 수 없다.")
    @Test
    void createEmptyTableDeliveryOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final UUID orderTableId = deliveryOrderTableRepository.save(deliveryOrderTable(false, 0)).getId();
        final DeliveryOrder expected = createOrderRequest(
            DeliveryOrderType.EAT_IN, orderTableId, createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, false, menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(DeliveryOrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final DeliveryOrder expected = createOrderRequest(DeliveryOrderType.TAKEOUT, createOrderLineItemRequest(menuId, 16_000L, 3L));
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(
            DeliveryOrderStatus.WAITING, deliveryOrderTable(true, 4))).getId();
        final DeliveryOrder actual = deliveryOrderService.accept(orderId);
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.ACCEPTED);
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, deliveryOrderTable(true, 4))).getId();
        assertThatThrownBy(() -> deliveryOrderService.accept(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("배달 주문을 접수되면 배달 대행사를 호출한다.")
    @Test
    void acceptDeliveryOrder() {
        final UUID orderId = deliveryOrderRepository.save(
            deliveryOrder(DeliveryOrderStatus.WAITING, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = deliveryOrderService.accept(orderId);
        assertAll(
            () -> assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.ACCEPTED),
            () -> assertThat(kitchenridersClient.getOrderId()).isEqualTo(orderId),
            () -> assertThat(kitchenridersClient.getDeliveryAddress()).isEqualTo("서울시 송파구 위례성대로 2")
        );
    }

    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.ACCEPTED)).getId();
        final DeliveryOrder actual = deliveryOrderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status)).getId();
        assertThatThrownBy(() -> deliveryOrderService.serve(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달한다.")
    @Test
    void startDelivery() {
        final UUID orderId = deliveryOrderRepository.save(
            deliveryOrder(DeliveryOrderStatus.SERVED, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = deliveryOrderService.startDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.DELIVERING);
    }

    @DisplayName("배달 주문만 배달할 수 있다.")
    @Test
    void startDeliveryWithoutDeliveryOrder() {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.SERVED)).getId();
        assertThatThrownBy(() -> deliveryOrderService.startDelivery(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("서빙된 주문만 배달할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void startDelivery(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> deliveryOrderService.startDelivery(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달 완료한다.")
    @Test
    void completeDelivery() {
        final UUID orderId = deliveryOrderRepository.save(
            deliveryOrder(DeliveryOrderStatus.DELIVERING, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = deliveryOrderService.completeDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.DELIVERED);
    }

    @DisplayName("배달 중인 주문만 배달 완료할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "DELIVERING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDelivery(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> deliveryOrderService.completeDelivery(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final DeliveryOrder expected = deliveryOrderRepository.save(
            deliveryOrder(DeliveryOrderStatus.DELIVERED, "서울시 송파구 위례성대로 2"));
        final DeliveryOrder actual = deliveryOrderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.COMPLETED);
    }

    @DisplayName("배달 주문의 경우 배달 완료된 주문만 완료할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "DELIVERED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDeliveryOrder(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> deliveryOrderService.complete(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("포장 및 매장 주문의 경우 서빙된 주문만 완료할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeTakeoutAndDeliveryOrder(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status)).getId();
        assertThatThrownBy(() -> deliveryOrderService.complete(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문 테이블의 모든 매장 주문이 완료되면 빈 테이블로 설정한다.")
    @Test
    void completeDeliveryOrder() {
        final DeliveryOrderTable deliveryOrderTable = deliveryOrderTableRepository.save(deliveryOrderTable(true, 4));
        final DeliveryOrder expected = deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.SERVED,
            deliveryOrderTable));
        final DeliveryOrder actual = deliveryOrderService.complete(expected.getId());
        assertAll(
            () -> assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.COMPLETED),
            () -> assertThat(deliveryOrderTableRepository.findById(deliveryOrderTable.getId()).get().isOccupied()).isFalse(),
            () -> assertThat(deliveryOrderTableRepository.findById(deliveryOrderTable.getId()).get().getNumberOfGuests()).isEqualTo(0)
        );
    }

    @DisplayName("완료되지 않은 매장 주문이 있는 주문 테이블은 빈 테이블로 설정하지 않는다.")
    @Test
    void completeNotTable() {
        final DeliveryOrderTable deliveryOrderTable = deliveryOrderTableRepository.save(deliveryOrderTable(true, 4));
        deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.ACCEPTED, deliveryOrderTable));
        final DeliveryOrder expected = deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.SERVED,
            deliveryOrderTable));
        final DeliveryOrder actual = deliveryOrderService.complete(expected.getId());
        assertAll(
            () -> assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.COMPLETED),
            () -> assertThat(deliveryOrderTableRepository.findById(deliveryOrderTable.getId()).get().isOccupied()).isTrue(),
            () -> assertThat(deliveryOrderTableRepository.findById(deliveryOrderTable.getId()).get().getNumberOfGuests()).isEqualTo(4)
        );
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        final DeliveryOrderTable deliveryOrderTable = deliveryOrderTableRepository.save(deliveryOrderTable(true, 4));
        deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.SERVED, deliveryOrderTable));
        deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.DELIVERED, "서울시 송파구 위례성대로 2"));
        final List<DeliveryOrder> actual = deliveryOrderService.findAll();
        assertThat(actual).hasSize(2);
    }

    private DeliveryOrder createOrderRequest(
        final DeliveryOrderType type,
        final String deliveryAddress,
        final DeliveryOrderLineItem... deliveryOrderLineItems
    ) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setType(type);
        deliveryOrder.setDeliveryAddress(deliveryAddress);
        deliveryOrder.setOrderLineItems(Arrays.asList(deliveryOrderLineItems));
        return deliveryOrder;
    }

    private DeliveryOrder createOrderRequest(final DeliveryOrderType deliveryOrderType, final DeliveryOrderLineItem... deliveryOrderLineItems) {
        return createOrderRequest(deliveryOrderType, Arrays.asList(deliveryOrderLineItems));
    }

    private DeliveryOrder createOrderRequest(final DeliveryOrderType deliveryOrderType, final List<DeliveryOrderLineItem> deliveryOrderLineItems) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setType(deliveryOrderType);
        deliveryOrder.setOrderLineItems(deliveryOrderLineItems);
        return deliveryOrder;
    }

    private DeliveryOrder createOrderRequest(
        final DeliveryOrderType type,
        final UUID orderTableId,
        final DeliveryOrderLineItem... deliveryOrderLineItems
    ) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setType(type);
        deliveryOrder.setOrderTableId(orderTableId);
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
