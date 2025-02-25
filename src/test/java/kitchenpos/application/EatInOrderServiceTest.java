package kitchenpos.application;

import kitchenpos.Fixtures;
import kitchenpos.shared.domain.OrderType;
import kitchenpos.eatinorder.application.EatInOrderService;
import kitchenpos.eatinorder.domain.*;
import kitchenpos.menu.domain.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.math.BigDecimal;
import java.util.*;

import static kitchenpos.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EatInOrderServiceTest {
    private EatInOrderRepository orderRepository;
    private MenuRepository menuRepository;
    private RestaurantTableRepository restaurantTableRepository;
    private EatInOrderService eatInOrderService;

    @BeforeEach
    void setUp() {
        orderRepository = new InMemoryEatInOrderRepository();
        menuRepository = new InMemoryMenuRepository();
        restaurantTableRepository = new InMemoryRestaurantTableRepository();
        eatInOrderService = new EatInOrderService(orderRepository, menuRepository, restaurantTableRepository);
    }


    @DisplayName("1개 이상의 등록된 메뉴로 매장 주문을 등록할 수 있다.")
    @Test
    void createEatInOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final UUID orderTableId = restaurantTableRepository.save(restaurantTable(true, 4)).getId();
        final EatInOrder expected = createOrderRequest(OrderType.EAT_IN, orderTableId, createOrderLineItemRequest(menuId, 19_000L, 3L));
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
    void create(final OrderType type) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final EatInOrder expected = createOrderRequest(type, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> eatInOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<EatInOrderLineItem> orderLineItems) {
        final EatInOrder expected = createOrderRequest(OrderType.EAT_IN, orderLineItems);
        assertThatThrownBy(() -> eatInOrderService.create(expected))
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
    void createEatInOrder(final long quantity) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final UUID orderTableId = restaurantTableRepository.save(restaurantTable(true, 4)).getId();
        final EatInOrder expected = createOrderRequest(
            OrderType.EAT_IN, orderTableId, createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertDoesNotThrow(() -> eatInOrderService.create(expected));
    }

    @DisplayName("빈 테이블에는 매장 주문을 등록할 수 없다.")
    @Test
    void createEmptyTableEatInOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final UUID orderTableId = restaurantTableRepository.save(restaurantTable(false, 0)).getId();
        final EatInOrder expected = createOrderRequest(
            OrderType.EAT_IN, orderTableId, createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        assertThatThrownBy(() -> eatInOrderService.create(expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, false, menuProduct())).getId();
        final EatInOrder expected = createOrderRequest(OrderType.EAT_IN, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> eatInOrderService.create(expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final EatInOrder expected = createOrderRequest(OrderType.EAT_IN, createOrderLineItemRequest(menuId, 16_000L, 3L));
        assertThatThrownBy(() -> eatInOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = orderRepository.save(eatInOrder(EatInOrderStatus.WAITING, restaurantTable(true, 4))).getId();
        final EatInOrder actual = eatInOrderService.accept(orderId);
        assertThat(actual.getStatus()).isEqualTo(EatInOrderStatus.ACCEPTED);
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = EatInOrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final EatInOrderStatus status) {
        final UUID orderId = orderRepository.save(eatInOrder(status, restaurantTable(true, 4))).getId();
        assertThatThrownBy(() -> eatInOrderService.accept(orderId))
            .isInstanceOf(IllegalStateException.class);
    }


    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final RestaurantTable restaurantTable = restaurantTableRepository.save(restaurantTable(true, 4));
        final UUID orderId = orderRepository.save(Fixtures.eatInOrder(EatInOrderStatus.ACCEPTED, restaurantTable)).getId();
        final EatInOrder actual = eatInOrderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(EatInOrderStatus.SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = EatInOrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final EatInOrderStatus status) {
        final RestaurantTable restaurantTable = restaurantTableRepository.save(restaurantTable(true, 4));
        final UUID orderId = orderRepository.save(Fixtures.eatInOrder(status, restaurantTable)).getId();
        assertThatThrownBy(() -> eatInOrderService.serve(orderId))
            .isInstanceOf(IllegalStateException.class);
    }


    @DisplayName("매장 주문의 경우 서빙된 주문만 완료할 수 있다.")
    @EnumSource(value = EatInOrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeTakeoutAndEatInOrder(final EatInOrderStatus status) {
        final RestaurantTable restaurantTable = restaurantTableRepository.save(restaurantTable(true, 4));
        final UUID orderId = orderRepository.save(Fixtures.eatInOrder(status, restaurantTable)).getId();
        assertThatThrownBy(() -> eatInOrderService.complete(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문 테이블의 모든 매장 주문이 완료되면 빈 테이블로 설정한다.")
    @Test
    void completeEatInOrder() {
        final RestaurantTable restaurantTable = restaurantTableRepository.save(restaurantTable(true, 4));
        final EatInOrder expected = orderRepository.save(eatInOrder(EatInOrderStatus.SERVED, restaurantTable));
        final EatInOrder actual = eatInOrderService.complete(expected.getId());
        assertAll(
            () -> assertThat(actual.getStatus()).isEqualTo(EatInOrderStatus.COMPLETED),
            () -> assertThat(restaurantTableRepository.findById(restaurantTable.getId()).get().isOccupied()).isFalse(),
            () -> assertThat(restaurantTableRepository.findById(restaurantTable.getId()).get().getNumberOfGuests()).isEqualTo(0)
        );
    }

    @DisplayName("완료되지 않은 매장 주문이 있는 주문 테이블은 빈 테이블로 설정하지 않는다.")
    @Test
    void completeNotTable() {
        final RestaurantTable restaurantTable = restaurantTableRepository.save(restaurantTable(true, 4));
        orderRepository.save(eatInOrder(EatInOrderStatus.ACCEPTED, restaurantTable));
        final EatInOrder expected = orderRepository.save(eatInOrder(EatInOrderStatus.SERVED, restaurantTable));
        final EatInOrder actual = eatInOrderService.complete(expected.getId());
        assertAll(
            () -> assertThat(actual.getStatus()).isEqualTo(EatInOrderStatus.COMPLETED),
            () -> assertThat(restaurantTableRepository.findById(restaurantTable.getId()).get().isOccupied()).isTrue(),
            () -> assertThat(restaurantTableRepository.findById(restaurantTable.getId()).get().getNumberOfGuests()).isEqualTo(4)
        );
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        final RestaurantTable restaurantTable = restaurantTableRepository.save(restaurantTable(true, 4));
        orderRepository.save(eatInOrder(EatInOrderStatus.SERVED, restaurantTable));
        orderRepository.save(eatInOrder(EatInOrderStatus.COMPLETED, restaurantTable));
        final List<EatInOrder> actual = eatInOrderService.findAll();
        assertThat(actual).hasSize(2);
    }

    private EatInOrder createOrderRequest(
        final OrderType type,
        final String deliveryAddress,
        final EatInOrderLineItem... orderLineItems
    ) {
        final EatInOrder order = new EatInOrder();
        order.setType(type);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderLineItems(Arrays.asList(orderLineItems));
        return order;
    }

    private EatInOrder createOrderRequest(final OrderType orderType, final EatInOrderLineItem... orderLineItems) {
        return createOrderRequest(orderType, Arrays.asList(orderLineItems));
    }

    private EatInOrder createOrderRequest(final OrderType orderType, final List<EatInOrderLineItem> orderLineItems) {
        final EatInOrder order = new EatInOrder();
        order.setType(orderType);
        order.setOrderLineItems(orderLineItems);
        return order;
    }

    private EatInOrder createOrderRequest(
        final OrderType type,
        final UUID orderTableId,
        final EatInOrderLineItem... orderLineItems
    ) {
        final EatInOrder order = new EatInOrder();
        order.setType(type);
        order.setOrderTableId(orderTableId);
        order.setOrderLineItems(Arrays.asList(orderLineItems));
        return order;
    }

    private static EatInOrderLineItem createOrderLineItemRequest(final UUID menuId, final long price, final long quantity) {
        final EatInOrderLineItem orderLineItem = new EatInOrderLineItem();
        orderLineItem.setSeq(new Random().nextLong());
        orderLineItem.setMenuId(menuId);
        orderLineItem.setPrice(BigDecimal.valueOf(price));
        orderLineItem.setQuantity(quantity);
        return orderLineItem;
    }
}
