package kitchenpos.eat_in_order.application;

import static kitchenpos.Fixtures.INVALID_ID;
import static kitchenpos.Fixtures.eatInOrder;
import static kitchenpos.Fixtures.eatInOrderTable;
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
import kitchenpos.eat_in_order.InMemoryEatInOrderRepository;
import kitchenpos.eat_in_order.InMemoryEatInOrderTableRepository;
import kitchenpos.eat_in_order.domain.EatInOrder;
import kitchenpos.eat_in_order.domain.EatInOrderLineItem;
import kitchenpos.eat_in_order.domain.EatInOrderRepository;
import kitchenpos.eat_in_order.domain.EatInOrderStatus;
import kitchenpos.eat_in_order.domain.EatInOrderTable;
import kitchenpos.eat_in_order.domain.EatInOrderTableRepository;
import kitchenpos.eat_in_order.domain.EatInOrderType;
import kitchenpos.menu.InMemoryMenuRepository;
import kitchenpos.menu.domain.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class EatInOrderServiceTest {
    private EatInOrderRepository eatInOrderRepository;
    private MenuRepository menuRepository;
    private EatInOrderTableRepository eatInOrderTableRepository;
    private EatInOrderService eatInOrderService;

    @BeforeEach
    void setUp() {
        eatInOrderRepository = new InMemoryEatInOrderRepository();
        menuRepository = new InMemoryMenuRepository();
        eatInOrderTableRepository = new InMemoryEatInOrderTableRepository();
        eatInOrderService = new EatInOrderService(
            eatInOrderRepository,
            menuRepository,
            eatInOrderTableRepository
        );
    }

    @DisplayName("1개 이상의 등록된 메뉴로 매장 주문을 등록할 수 있다.")
    @Test
    void createEatInOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final UUID orderTableId = eatInOrderTableRepository.save(eatInOrderTable(true, 4)).getId();
        final EatInOrder expected = createOrderRequest(EatInOrderType.EAT_IN, orderTableId,
            createOrderLineItemRequest(menuId, 19_000L, 3L));
        final EatInOrder actual = eatInOrderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getType()).isEqualTo(expected.getType()),
            () -> assertThat(actual.getStatus()).isEqualTo(EatInOrderStatus.WAITING),
            () -> assertThat(actual.getOrderDateTime()).isNotNull(),
            () -> assertThat(actual.getOrderLineItems()).hasSize(1),
            () -> assertThat(actual.getOrderTable().getId()).isEqualTo(expected.getOrderTableId())
        );
    }

    @DisplayName("주문 유형이 올바르지 않으면 등록할 수 없다.")
    @NullSource
    @ParameterizedTest
    void create(final EatInOrderType type) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final EatInOrder expected = createOrderRequest(type,
            createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> eatInOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<EatInOrderLineItem> eatInOrderLineItems) {
        final EatInOrder expected = createOrderRequest(EatInOrderType.EAT_IN, eatInOrderLineItems);
        assertThatThrownBy(() -> eatInOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("매장 주문은 주문 항목의 수량이 0 미만일 수 있다.")
    @ValueSource(longs = -1L)
    @ParameterizedTest
    void createEatInOrder(final long quantity) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final UUID orderTableId = eatInOrderTableRepository.save(eatInOrderTable(true, 4)).getId();
        final EatInOrder expected = createOrderRequest(
            EatInOrderType.EAT_IN, orderTableId,
            createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertDoesNotThrow(() -> eatInOrderService.create(expected));
    }

    @DisplayName("빈 테이블에는 매장 주문을 등록할 수 없다.")
    @Test
    void createEmptyTableEatInOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final UUID orderTableId = eatInOrderTableRepository.save(eatInOrderTable(false, 0)).getId();
        final EatInOrder expected = createOrderRequest(
            EatInOrderType.EAT_IN, orderTableId, createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        assertThatThrownBy(() -> eatInOrderService.create(expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, false, menuProduct())).getId();
        final EatInOrder expected = createOrderRequest(EatInOrderType.EAT_IN,
            createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> eatInOrderService.create(expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final EatInOrder expected = createOrderRequest(EatInOrderType.EAT_IN,
            createOrderLineItemRequest(menuId, 16_000L, 3L));
        assertThatThrownBy(() -> eatInOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = eatInOrderRepository.save(eatInOrder(
            EatInOrderStatus.WAITING, eatInOrderTable(true, 4))).getId();
        final EatInOrder actual = eatInOrderService.accept(orderId);
        assertThat(actual.getStatus()).isEqualTo(EatInOrderStatus.ACCEPTED);
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = EatInOrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final EatInOrderStatus status) {
        final UUID orderId = eatInOrderRepository.save(eatInOrder(status, eatInOrderTable(true, 4)))
            .getId();
        assertThatThrownBy(() -> eatInOrderService.accept(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final EatInOrderTable eatInOrderTable = eatInOrderTableRepository.save(
            eatInOrderTable(true, 4));
        final UUID orderId = eatInOrderRepository.save(
            eatInOrder(EatInOrderStatus.ACCEPTED, eatInOrderTable)).getId();
        final EatInOrder actual = eatInOrderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(EatInOrderStatus.SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = EatInOrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final EatInOrderStatus status) {
        final EatInOrderTable eatInOrderTable = eatInOrderTableRepository.save(
            eatInOrderTable(true, 4));
        final UUID orderId = eatInOrderRepository.save(eatInOrder(status, eatInOrderTable)).getId();
        assertThatThrownBy(() -> eatInOrderService.serve(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final EatInOrderTable eatInOrderTable = eatInOrderTableRepository.save(
            eatInOrderTable(true, 4));
        final EatInOrder expected = eatInOrderRepository.save(
            eatInOrder(EatInOrderStatus.SERVED, eatInOrderTable));
        final EatInOrder actual = eatInOrderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(EatInOrderStatus.COMPLETED);
    }

    @DisplayName("매장 주문의 경우 서빙된 주문만 완료할 수 있다.")
    @EnumSource(value = EatInOrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeTakeoutAndEatInOrder(final EatInOrderStatus status) {
        final EatInOrderTable eatInOrderTable = eatInOrderTableRepository.save(
            eatInOrderTable(true, 4));
        final UUID orderId = eatInOrderRepository.save(eatInOrder(status, eatInOrderTable)).getId();
        assertThatThrownBy(() -> eatInOrderService.complete(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문 테이블의 모든 매장 주문이 완료되면 빈 테이블로 설정한다.")
    @Test
    void completeEatInOrder() {
        final EatInOrderTable eatInOrderTable = eatInOrderTableRepository.save(
            eatInOrderTable(true, 4));
        final EatInOrder expected = eatInOrderRepository.save(eatInOrder(EatInOrderStatus.SERVED,
            eatInOrderTable));
        final EatInOrder actual = eatInOrderService.complete(expected.getId());
        assertAll(
            () -> assertThat(actual.getStatus()).isEqualTo(EatInOrderStatus.COMPLETED),
            () -> assertThat(eatInOrderTableRepository.findById(eatInOrderTable.getId()).get()
                .isOccupied()).isFalse(),
            () -> assertThat(eatInOrderTableRepository.findById(eatInOrderTable.getId()).get()
                .getNumberOfGuests()).isEqualTo(0)
        );
    }

    @DisplayName("완료되지 않은 매장 주문이 있는 주문 테이블은 빈 테이블로 설정하지 않는다.")
    @Test
    void completeNotTable() {
        final EatInOrderTable eatInOrderTable = eatInOrderTableRepository.save(
            eatInOrderTable(true, 4));
        eatInOrderRepository.save(eatInOrder(EatInOrderStatus.ACCEPTED, eatInOrderTable));
        final EatInOrder expected = eatInOrderRepository.save(eatInOrder(EatInOrderStatus.SERVED,
            eatInOrderTable));
        final EatInOrder actual = eatInOrderService.complete(expected.getId());
        assertAll(
            () -> assertThat(actual.getStatus()).isEqualTo(EatInOrderStatus.COMPLETED),
            () -> assertThat(eatInOrderTableRepository.findById(eatInOrderTable.getId()).get()
                .isOccupied()).isTrue(),
            () -> assertThat(eatInOrderTableRepository.findById(eatInOrderTable.getId()).get()
                .getNumberOfGuests()).isEqualTo(4)
        );
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        final EatInOrderTable eatInOrderTable = eatInOrderTableRepository.save(
            eatInOrderTable(true, 4));
        eatInOrderRepository.save(eatInOrder(EatInOrderStatus.SERVED, eatInOrderTable));
        eatInOrderRepository.save(eatInOrder(EatInOrderStatus.COMPLETED, eatInOrderTable));
        final List<EatInOrder> actual = eatInOrderService.findAll();
        assertThat(actual).hasSize(2);
    }

    private EatInOrder createOrderRequest(final EatInOrderType eatInOrderType,
        final EatInOrderLineItem... eatInOrderLineItems) {
        return createOrderRequest(eatInOrderType, Arrays.asList(eatInOrderLineItems));
    }

    private EatInOrder createOrderRequest(final EatInOrderType eatInOrderType,
        final List<EatInOrderLineItem> eatInOrderLineItems) {
        final EatInOrder eatInOrder = new EatInOrder();
        eatInOrder.setType(eatInOrderType);
        eatInOrder.setOrderLineItems(eatInOrderLineItems);
        return eatInOrder;
    }

    private EatInOrder createOrderRequest(
        final EatInOrderType type,
        final UUID orderTableId,
        final EatInOrderLineItem... eatInOrderLineItems
    ) {
        final EatInOrder eatInOrder = new EatInOrder();
        eatInOrder.setType(type);
        eatInOrder.setOrderTableId(orderTableId);
        eatInOrder.setOrderLineItems(Arrays.asList(eatInOrderLineItems));
        return eatInOrder;
    }

    private static List<Arguments> orderLineItems() {
        return Arrays.asList(
            null,
            Arguments.of(Collections.emptyList()),
            Arguments.of(List.of(createOrderLineItemRequest(INVALID_ID, 19_000L, 3L)))
        );
    }

    private static EatInOrderLineItem createOrderLineItemRequest(final UUID menuId,
        final long price, final long quantity) {
        final EatInOrderLineItem eatInOrderLineItem = new EatInOrderLineItem();
        eatInOrderLineItem.setSeq(new Random().nextLong());
        eatInOrderLineItem.setMenuId(menuId);
        eatInOrderLineItem.setPrice(BigDecimal.valueOf(price));
        eatInOrderLineItem.setQuantity(quantity);
        return eatInOrderLineItem;
    }
}
