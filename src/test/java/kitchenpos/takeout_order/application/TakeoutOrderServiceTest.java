package kitchenpos.takeout_order.application;

import static kitchenpos.Fixtures.INVALID_ID;
import static kitchenpos.Fixtures.menu;
import static kitchenpos.Fixtures.menuProduct;
import static kitchenpos.Fixtures.takeoutOrder;
import static kitchenpos.Fixtures.takeoutOrderTable;
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
import kitchenpos.menu.InMemoryMenuRepository;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.takeout_order.InMemoryTakeoutOrderRepository;
import kitchenpos.takeout_order.InMemoryTakeoutOrderTableRepository;
import kitchenpos.takeout_order.domain.TakeoutOrder;
import kitchenpos.takeout_order.domain.TakeoutOrderLineItem;
import kitchenpos.takeout_order.domain.TakeoutOrderRepository;
import kitchenpos.takeout_order.domain.TakeoutOrderStatus;
import kitchenpos.takeout_order.domain.TakeoutOrderTable;
import kitchenpos.takeout_order.domain.TakeoutOrderTableRepository;
import kitchenpos.takeout_order.domain.TakeoutOrderType;
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

class TakeoutOrderServiceTest {
    private TakeoutOrderRepository takeoutOrderRepository;
    private MenuRepository menuRepository;
    private TakeoutOrderTableRepository takeoutOrderTableRepository;
    private FakeKitchenridersClient kitchenridersClient;
    private TakeoutOrderService takeoutOrderService;

    @BeforeEach
    void setUp() {
        takeoutOrderRepository = new InMemoryTakeoutOrderRepository();
        menuRepository = new InMemoryMenuRepository();
        takeoutOrderTableRepository = new InMemoryTakeoutOrderTableRepository();
        kitchenridersClient = new FakeKitchenridersClient();
        takeoutOrderService = new TakeoutOrderService(takeoutOrderRepository, menuRepository,
            takeoutOrderTableRepository, kitchenridersClient);
    }

    @DisplayName("1개 이상의 등록된 메뉴로 배달 주문을 등록할 수 있다.")
    @Test
    void createDeliveryOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeoutOrder expected = createOrderRequest(
            TakeoutOrderType.DELIVERY, "서울시 송파구 위례성대로 2", createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        final TakeoutOrder actual = takeoutOrderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getType()).isEqualTo(expected.getType()),
            () -> assertThat(actual.getStatus()).isEqualTo(TakeoutOrderStatus.WAITING),
            () -> assertThat(actual.getOrderDateTime()).isNotNull(),
            () -> assertThat(actual.getOrderLineItems()).hasSize(1),
            () -> assertThat(actual.getDeliveryAddress()).isEqualTo(expected.getDeliveryAddress())
        );
    }

    @DisplayName("1개 이상의 등록된 메뉴로 포장 주문을 등록할 수 있다.")
    @Test
    void createTakeoutOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeoutOrder expected = createOrderRequest(TakeoutOrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, 3L));
        final TakeoutOrder actual = takeoutOrderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getType()).isEqualTo(expected.getType()),
            () -> assertThat(actual.getStatus()).isEqualTo(TakeoutOrderStatus.WAITING),
            () -> assertThat(actual.getOrderDateTime()).isNotNull(),
            () -> assertThat(actual.getOrderLineItems()).hasSize(1)
        );
    }

    @DisplayName("1개 이상의 등록된 메뉴로 매장 주문을 등록할 수 있다.")
    @Test
    void createEatInOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final UUID orderTableId = takeoutOrderTableRepository.save(takeoutOrderTable(true, 4)).getId();
        final TakeoutOrder expected = createOrderRequest(TakeoutOrderType.EAT_IN, orderTableId, createOrderLineItemRequest(menuId, 19_000L, 3L));
        final TakeoutOrder actual = takeoutOrderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getType()).isEqualTo(expected.getType()),
            () -> assertThat(actual.getStatus()).isEqualTo(TakeoutOrderStatus.WAITING),
            () -> assertThat(actual.getOrderDateTime()).isNotNull(),
            () -> assertThat(actual.getOrderLineItems()).hasSize(1),
            () -> assertThat(actual.getOrderTable().getId()).isEqualTo(expected.getOrderTableId())
        );
    }

    @DisplayName("주문 유형이 올바르지 않으면 등록할 수 없다.")
    @NullSource
    @ParameterizedTest
    void create(final TakeoutOrderType type) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeoutOrder expected = createOrderRequest(type, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> takeoutOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<TakeoutOrderLineItem> takeoutOrderLineItems) {
        final TakeoutOrder expected = createOrderRequest(TakeoutOrderType.TAKEOUT, takeoutOrderLineItems);
        assertThatThrownBy(() -> takeoutOrderService.create(expected))
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
    void createTakeoutOrder(final long quantity) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final UUID orderTableId = takeoutOrderTableRepository.save(takeoutOrderTable(true, 4)).getId();
        final TakeoutOrder expected = createOrderRequest(
            TakeoutOrderType.EAT_IN, orderTableId, createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertDoesNotThrow(() -> takeoutOrderService.create(expected));
    }

    @DisplayName("매장 주문을 제외한 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.")
    @ValueSource(longs = -1L)
    @ParameterizedTest
    void createWithoutTakeoutOrder(final long quantity) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeoutOrder expected = createOrderRequest(
            TakeoutOrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertThatThrownBy(() -> takeoutOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배달 주소가 올바르지 않으면 배달 주문을 등록할 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void create(final String deliveryAddress) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeoutOrder expected = createOrderRequest(
            TakeoutOrderType.DELIVERY, deliveryAddress, createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        assertThatThrownBy(() -> takeoutOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈 테이블에는 매장 주문을 등록할 수 없다.")
    @Test
    void createEmptyTableTakeoutOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final UUID orderTableId = takeoutOrderTableRepository.save(takeoutOrderTable(false, 0)).getId();
        final TakeoutOrder expected = createOrderRequest(
            TakeoutOrderType.EAT_IN, orderTableId, createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        assertThatThrownBy(() -> takeoutOrderService.create(expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, false, menuProduct())).getId();
        final TakeoutOrder expected = createOrderRequest(TakeoutOrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> takeoutOrderService.create(expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeoutOrder expected = createOrderRequest(TakeoutOrderType.TAKEOUT, createOrderLineItemRequest(menuId, 16_000L, 3L));
        assertThatThrownBy(() -> takeoutOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = takeoutOrderRepository.save(takeoutOrder(
            TakeoutOrderStatus.WAITING, takeoutOrderTable(true, 4))).getId();
        final TakeoutOrder actual = takeoutOrderService.accept(orderId);
        assertThat(actual.getStatus()).isEqualTo(TakeoutOrderStatus.ACCEPTED);
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = TakeoutOrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final TakeoutOrderStatus status) {
        final UUID orderId = takeoutOrderRepository.save(takeoutOrder(status, takeoutOrderTable(true, 4))).getId();
        assertThatThrownBy(() -> takeoutOrderService.accept(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("배달 주문을 접수되면 배달 대행사를 호출한다.")
    @Test
    void acceptDeliveryOrder() {
        final UUID orderId = takeoutOrderRepository.save(
            takeoutOrder(TakeoutOrderStatus.WAITING, "서울시 송파구 위례성대로 2")).getId();
        final TakeoutOrder actual = takeoutOrderService.accept(orderId);
        assertAll(
            () -> assertThat(actual.getStatus()).isEqualTo(TakeoutOrderStatus.ACCEPTED),
            () -> assertThat(kitchenridersClient.getOrderId()).isEqualTo(orderId),
            () -> assertThat(kitchenridersClient.getDeliveryAddress()).isEqualTo("서울시 송파구 위례성대로 2")
        );
    }

    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final UUID orderId = takeoutOrderRepository.save(takeoutOrder(TakeoutOrderStatus.ACCEPTED)).getId();
        final TakeoutOrder actual = takeoutOrderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(TakeoutOrderStatus.SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = TakeoutOrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final TakeoutOrderStatus status) {
        final UUID orderId = takeoutOrderRepository.save(takeoutOrder(status)).getId();
        assertThatThrownBy(() -> takeoutOrderService.serve(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달한다.")
    @Test
    void startDelivery() {
        final UUID orderId = takeoutOrderRepository.save(
            takeoutOrder(TakeoutOrderStatus.SERVED, "서울시 송파구 위례성대로 2")).getId();
        final TakeoutOrder actual = takeoutOrderService.startDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(TakeoutOrderStatus.DELIVERING);
    }

    @DisplayName("배달 주문만 배달할 수 있다.")
    @Test
    void startDeliveryWithoutDeliveryOrder() {
        final UUID orderId = takeoutOrderRepository.save(takeoutOrder(TakeoutOrderStatus.SERVED)).getId();
        assertThatThrownBy(() -> takeoutOrderService.startDelivery(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("서빙된 주문만 배달할 수 있다.")
    @EnumSource(value = TakeoutOrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void startDelivery(final TakeoutOrderStatus status) {
        final UUID orderId = takeoutOrderRepository.save(takeoutOrder(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> takeoutOrderService.startDelivery(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달 완료한다.")
    @Test
    void completeDelivery() {
        final UUID orderId = takeoutOrderRepository.save(
            takeoutOrder(TakeoutOrderStatus.DELIVERING, "서울시 송파구 위례성대로 2")).getId();
        final TakeoutOrder actual = takeoutOrderService.completeDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(TakeoutOrderStatus.DELIVERED);
    }

    @DisplayName("배달 중인 주문만 배달 완료할 수 있다.")
    @EnumSource(value = TakeoutOrderStatus.class, names = "DELIVERING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDelivery(final TakeoutOrderStatus status) {
        final UUID orderId = takeoutOrderRepository.save(takeoutOrder(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> takeoutOrderService.completeDelivery(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final TakeoutOrder expected = takeoutOrderRepository.save(
            takeoutOrder(TakeoutOrderStatus.DELIVERED, "서울시 송파구 위례성대로 2"));
        final TakeoutOrder actual = takeoutOrderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(TakeoutOrderStatus.COMPLETED);
    }

    @DisplayName("배달 주문의 경우 배달 완료된 주문만 완료할 수 있다.")
    @EnumSource(value = TakeoutOrderStatus.class, names = "DELIVERED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDeliveryOrder(final TakeoutOrderStatus status) {
        final UUID orderId = takeoutOrderRepository.save(takeoutOrder(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> takeoutOrderService.complete(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("포장 및 매장 주문의 경우 서빙된 주문만 완료할 수 있다.")
    @EnumSource(value = TakeoutOrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeTakeoutAndTakeoutOrder(final TakeoutOrderStatus status) {
        final UUID orderId = takeoutOrderRepository.save(takeoutOrder(status)).getId();
        assertThatThrownBy(() -> takeoutOrderService.complete(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문 테이블의 모든 매장 주문이 완료되면 빈 테이블로 설정한다.")
    @Test
    void completeTakeoutOrder() {
        final TakeoutOrderTable takeoutOrderTable = takeoutOrderTableRepository.save(takeoutOrderTable(true, 4));
        final TakeoutOrder expected = takeoutOrderRepository.save(takeoutOrder(TakeoutOrderStatus.SERVED,
            takeoutOrderTable));
        final TakeoutOrder actual = takeoutOrderService.complete(expected.getId());
        assertAll(
            () -> assertThat(actual.getStatus()).isEqualTo(TakeoutOrderStatus.COMPLETED),
            () -> assertThat(takeoutOrderTableRepository.findById(takeoutOrderTable.getId()).get().isOccupied()).isFalse(),
            () -> assertThat(takeoutOrderTableRepository.findById(takeoutOrderTable.getId()).get().getNumberOfGuests()).isEqualTo(0)
        );
    }

    @DisplayName("완료되지 않은 매장 주문이 있는 주문 테이블은 빈 테이블로 설정하지 않는다.")
    @Test
    void completeNotTable() {
        final TakeoutOrderTable takeoutOrderTable = takeoutOrderTableRepository.save(takeoutOrderTable(true, 4));
        takeoutOrderRepository.save(takeoutOrder(TakeoutOrderStatus.ACCEPTED, takeoutOrderTable));
        final TakeoutOrder expected = takeoutOrderRepository.save(takeoutOrder(TakeoutOrderStatus.SERVED,
            takeoutOrderTable));
        final TakeoutOrder actual = takeoutOrderService.complete(expected.getId());
        assertAll(
            () -> assertThat(actual.getStatus()).isEqualTo(TakeoutOrderStatus.COMPLETED),
            () -> assertThat(takeoutOrderTableRepository.findById(takeoutOrderTable.getId()).get().isOccupied()).isTrue(),
            () -> assertThat(takeoutOrderTableRepository.findById(takeoutOrderTable.getId()).get().getNumberOfGuests()).isEqualTo(4)
        );
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        final TakeoutOrderTable takeoutOrderTable = takeoutOrderTableRepository.save(takeoutOrderTable(true, 4));
        takeoutOrderRepository.save(takeoutOrder(TakeoutOrderStatus.SERVED, takeoutOrderTable));
        takeoutOrderRepository.save(takeoutOrder(TakeoutOrderStatus.DELIVERED, "서울시 송파구 위례성대로 2"));
        final List<TakeoutOrder> actual = takeoutOrderService.findAll();
        assertThat(actual).hasSize(2);
    }

    private TakeoutOrder createOrderRequest(
        final TakeoutOrderType type,
        final String deliveryAddress,
        final TakeoutOrderLineItem... takeoutOrderLineItems
    ) {
        final TakeoutOrder takeoutOrder = new TakeoutOrder();
        takeoutOrder.setType(type);
        takeoutOrder.setDeliveryAddress(deliveryAddress);
        takeoutOrder.setOrderLineItems(Arrays.asList(takeoutOrderLineItems));
        return takeoutOrder;
    }

    private TakeoutOrder createOrderRequest(final TakeoutOrderType takeoutOrderType, final TakeoutOrderLineItem... takeoutOrderLineItems) {
        return createOrderRequest(takeoutOrderType, Arrays.asList(takeoutOrderLineItems));
    }

    private TakeoutOrder createOrderRequest(final TakeoutOrderType takeoutOrderType, final List<TakeoutOrderLineItem> takeoutOrderLineItems) {
        final TakeoutOrder takeoutOrder = new TakeoutOrder();
        takeoutOrder.setType(takeoutOrderType);
        takeoutOrder.setOrderLineItems(takeoutOrderLineItems);
        return takeoutOrder;
    }

    private TakeoutOrder createOrderRequest(
        final TakeoutOrderType type,
        final UUID orderTableId,
        final TakeoutOrderLineItem... takeoutOrderLineItems
    ) {
        final TakeoutOrder takeoutOrder = new TakeoutOrder();
        takeoutOrder.setType(type);
        takeoutOrder.setOrderTableId(orderTableId);
        takeoutOrder.setOrderLineItems(Arrays.asList(takeoutOrderLineItems));
        return takeoutOrder;
    }

    private static TakeoutOrderLineItem createOrderLineItemRequest(final UUID menuId, final long price, final long quantity) {
        final TakeoutOrderLineItem takeoutOrderLineItem = new TakeoutOrderLineItem();
        takeoutOrderLineItem.setSeq(new Random().nextLong());
        takeoutOrderLineItem.setMenuId(menuId);
        takeoutOrderLineItem.setPrice(BigDecimal.valueOf(price));
        takeoutOrderLineItem.setQuantity(quantity);
        return takeoutOrderLineItem;
    }
}
