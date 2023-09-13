package kitchenpos.orders.takeout_orders.application;

import kitchenpos.menus.domain.MenuRepository;
import kitchenpos.menus.infrastructure.InMemoryMenuRepository;
import kitchenpos.orders.domain.*;
import kitchenpos.orders.takeout_orders.domain.TakeoutOrder;
import kitchenpos.orders.takeout_orders.domain.TakeoutOrderLineItem;
import kitchenpos.orders.takeout_orders.domain.TakeoutOrderRepository;
import kitchenpos.orders.takeout_orders.infrastructure.InMemoryTakeoutOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.math.BigDecimal;
import java.util.*;

import static kitchenpos.orders.takeout_orders.TakeoutOrderFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class TakeoutOrderServiceTest {
    private TakeoutOrderRepository orderRepository;
    private MenuRepository menuRepository;
    private TakeoutOrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = new InMemoryTakeoutOrderRepository();
        menuRepository = new InMemoryMenuRepository();
        orderService = new TakeoutOrderService(orderRepository, menuRepository);
    }

    @DisplayName("1개 이상의 등록된 메뉴로 포장 주문을 등록할 수 있다.")
    @Test
    void createTakeoutOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeoutOrder expected = createOrderRequest(createOrderLineItemRequest(menuId, 19_000L, 3L));
        final TakeoutOrder actual = orderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getStatus()).isEqualTo(OrderStatus.WAITING),
            () -> assertThat(actual.getOrderDateTime()).isNotNull(),
            () -> assertThat(actual.getOrderLineItems()).hasSize(1)
        );
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<TakeoutOrderLineItem> orderLineItems) {
        final TakeoutOrder expected = createOrderRequest(orderLineItems);
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

    @DisplayName("포장 주문의 주문 항목 수량은 0 이상이어야 한다.")
    @ValueSource(longs = -1L)
    @ParameterizedTest
    void createWithoutEatInOrder(final long quantity) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeoutOrder expected = createOrderRequest(
            createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertThatThrownBy(() -> orderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, false, menuProduct())).getId();
        final TakeoutOrder expected = createOrderRequest(createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> orderService.create(expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeoutOrder expected = createOrderRequest(createOrderLineItemRequest(menuId, 16_000L, 3L));
        assertThatThrownBy(() -> orderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = orderRepository.save(order(OrderStatus.WAITING)).getId();
        final TakeoutOrder actual = orderService.accept(orderId);
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
        final TakeoutOrder actual = orderService.serve(orderId);
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
        final TakeoutOrder expected = orderRepository.save(order(OrderStatus.SERVED));
        final TakeoutOrder actual = orderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @DisplayName("포장 주문의 경우 서빙된 주문만 완료할 수 있다.")
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
        orderRepository.save(order(OrderStatus.COMPLETED));
        final List<TakeoutOrder> actual = orderService.findAll();
        assertThat(actual).hasSize(2);
    }

    private TakeoutOrder createOrderRequest(final TakeoutOrderLineItem... orderLineItems) {
        return createOrderRequest(Arrays.asList(orderLineItems));
    }

    private TakeoutOrder createOrderRequest(final List<TakeoutOrderLineItem> orderLineItems) {
        final TakeoutOrder order = new TakeoutOrder();
        order.setOrderLineItems(orderLineItems);
        return order;
    }

    private static TakeoutOrderLineItem createOrderLineItemRequest(final UUID menuId, final long price, final long quantity) {
        final TakeoutOrderLineItem orderLineItem = new TakeoutOrderLineItem();
        orderLineItem.setSeq(new Random().nextLong());
        orderLineItem.setMenuId(menuId);
        orderLineItem.setPrice(BigDecimal.valueOf(price));
        orderLineItem.setQuantity(quantity);
        return orderLineItem;
    }
}
