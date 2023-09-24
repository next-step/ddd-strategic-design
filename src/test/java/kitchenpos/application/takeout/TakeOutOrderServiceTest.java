package kitchenpos.application.takeout;


import kitchenpos.common.OrderStatus;
import kitchenpos.common.OrderType;
import kitchenpos.takeoutorder.application.TakeOutOrderService;
import kitchenpos.takeoutorder.domain.TakeOutMenuRepository;
import kitchenpos.takeoutorder.domain.TakeOutOrder;
import kitchenpos.takeoutorder.domain.TakeOutOrderLineItem;
import kitchenpos.takeoutorder.domain.TakeOutOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.math.BigDecimal;
import java.util.*;

import static kitchenpos.application.takeout.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TakeOutOrderServiceTest {
    private TakeOutOrderRepository orderRepository;
    private TakeOutMenuRepository menuRepository;
    private TakeOutOrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = new InMemoryTakeOutOrderRepository();
        menuRepository = new InMemoryTakeOutMenuRepository();
        orderService = new TakeOutOrderService(orderRepository, menuRepository);
    }

    @DisplayName("1개 이상의 등록된 메뉴로 포장 주문을 등록할 수 있다.")
    @Test
    void createTakeoutOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true)).getId();
        final TakeOutOrder expected = createOrderRequest(OrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, 3L));
        final TakeOutOrder actual = orderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getType()).isEqualTo(expected.getType()),
                () -> assertThat(actual.getStatus()).isEqualTo(OrderStatus.WAITING),
                () -> assertThat(actual.getOrderDateTime()).isNotNull(),
                () -> assertThat(actual.getOrderLineItems()).hasSize(1)
        );
    }

    @DisplayName("주문 유형이 올바르지 않으면 등록할 수 없다.")
    @NullSource
    @ParameterizedTest
    void create(final OrderType type) {
        final UUID menuId = menuRepository.save(menu(19_000L, true)).getId();
        final TakeOutOrder expected = createOrderRequest(type, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> orderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<TakeOutOrderLineItem> orderLineItems) {
        final TakeOutOrder expected = createOrderRequest(OrderType.TAKEOUT, orderLineItems);
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

    @DisplayName("매장 주문을 제외한 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.")
    @ValueSource(longs = -1L)
    @ParameterizedTest
    void createWithoutEatInOrder(final long quantity) {
        final UUID menuId = menuRepository.save(menu(19_000L, true)).getId();
        final TakeOutOrder expected = createOrderRequest(
                OrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertThatThrownBy(() -> orderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, false)).getId();
        final TakeOutOrder expected = createOrderRequest(OrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> orderService.create(expected))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true)).getId();
        final TakeOutOrder expected = createOrderRequest(OrderType.TAKEOUT, createOrderLineItemRequest(menuId, 16_000L, 3L));
        assertThatThrownBy(() -> orderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = orderRepository.save(order(OrderStatus.WAITING)).getId();
        final TakeOutOrder actual = orderService.accept(orderId);
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.ACCEPTED);
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final OrderStatus status) {
        final UUID orderId = orderRepository.save(order(status)).getId();
        assertThatThrownBy(() -> orderService.accept(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final UUID orderId = orderRepository.save(order(OrderStatus.ACCEPTED)).getId();
        final TakeOutOrder actual = orderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final OrderStatus status) {
        final UUID orderId = orderRepository.save(order(status)).getId();
        assertThatThrownBy(() -> orderService.serve(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final TakeOutOrder expected = orderRepository.save(order(OrderStatus.SERVED));
        final TakeOutOrder actual = orderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @DisplayName("포장 및 매장 주문의 경우 서빙된 주문만 완료할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeTakeoutAndEatInOrder(final OrderStatus status) {
        final UUID orderId = orderRepository.save(order(status)).getId();
        assertThatThrownBy(() -> orderService.complete(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        orderRepository.save(order(OrderStatus.SERVED));
        orderRepository.save(order(OrderStatus.DELIVERED));
        final List<TakeOutOrder> actual = orderService.findAll();
        assertThat(actual).hasSize(2);
    }

    private TakeOutOrder createOrderRequest(final OrderType orderType, final TakeOutOrderLineItem... orderLineItems) {
        return createOrderRequest(orderType, Arrays.asList(orderLineItems));
    }

    private TakeOutOrder createOrderRequest(final OrderType orderType, final List<TakeOutOrderLineItem> orderLineItems) {
        final TakeOutOrder order = new TakeOutOrder();
        order.setType(orderType);
        order.setOrderLineItems(orderLineItems);
        return order;
    }

    private static TakeOutOrderLineItem createOrderLineItemRequest(final UUID menuId, final long price, final long quantity) {
        final TakeOutOrderLineItem orderLineItem = new TakeOutOrderLineItem();
        orderLineItem.setSeq(new Random().nextLong());
        orderLineItem.setMenuId(menuId);
        orderLineItem.setPrice(BigDecimal.valueOf(price));
        orderLineItem.setQuantity(quantity);
        return orderLineItem;
    }
}
