package kitchenpos.application;

import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.order.common.domain.OrderLineItem;
import kitchenpos.order.common.domain.OrderType;
import kitchenpos.order.takeout.application.TakeOutOrderService;
import kitchenpos.order.takeout.domain.TakeOutOrder;
import kitchenpos.order.takeout.domain.TakeOutOrderRepository;
import kitchenpos.order.takeout.domain.TakeOutOrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.math.BigDecimal;
import java.util.*;

import static kitchenpos.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class TakeOutOrderServiceTest {
    private TakeOutOrderRepository takeOutOrderRepository;
    private MenuRepository menuRepository;
    private TakeOutOrderService takeOutOrderService;

    @BeforeEach
    void setUp() {
        takeOutOrderRepository = new InMemoryTakeOutOrderRepository();
        menuRepository = new InMemoryMenuRepository();
        takeOutOrderService = new TakeOutOrderService(takeOutOrderRepository, menuRepository);
    }

    @DisplayName("1개 이상의 등록된 메뉴로 포장 주문을 등록할 수 있다.")
    @Test
    void createTakeoutOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeOutOrder expected = createOrderRequest(OrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, 3L));
        final TakeOutOrder actual = takeOutOrderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getType()).isEqualTo(expected.getType()),
            () -> assertThat(actual.getStatus()).isEqualTo(TakeOutOrderStatus.WAITING),
            () -> assertThat(actual.getOrderDateTime()).isNotNull(),
            () -> assertThat(actual.getOrderLineItems()).hasSize(1)
        );
    }

    @DisplayName("주문 유형이 올바르지 않으면 등록할 수 없다.")
    @NullSource
    @ParameterizedTest
    void create(final OrderType type) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeOutOrder expected = createOrderRequest(type, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> takeOutOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<OrderLineItem> orderLineItems) {
        final TakeOutOrder expected = createOrderRequest(OrderType.TAKEOUT, orderLineItems);
        assertThatThrownBy(() -> takeOutOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static List<Arguments> orderLineItems() {
        return Arrays.asList(
            null,
            Arguments.of(Collections.emptyList()),
            Arguments.of(List.of(createOrderLineItemRequest(INVALID_ID, 19_000L, 3L)))
        );
    }

    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, false, menuProduct())).getId();
        final TakeOutOrder expected = createOrderRequest(OrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> takeOutOrderService.create(expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeOutOrder expected = createOrderRequest(OrderType.TAKEOUT, createOrderLineItemRequest(menuId, 16_000L, 3L));
        assertThatThrownBy(() -> takeOutOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = takeOutOrderRepository.save(order(TakeOutOrderStatus.WAITING)).getId();
        final TakeOutOrder actual = takeOutOrderService.accept(orderId);
        assertThat(actual.getStatus()).isEqualTo(TakeOutOrderStatus.ACCEPTED);
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = TakeOutOrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final TakeOutOrderStatus status) {
        final UUID orderId = takeOutOrderRepository.save(order(status)).getId();
        assertThatThrownBy(() -> takeOutOrderService.accept(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final UUID orderId = takeOutOrderRepository.save(order(TakeOutOrderStatus.ACCEPTED)).getId();
        final TakeOutOrder actual = takeOutOrderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(TakeOutOrderStatus.SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = TakeOutOrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final TakeOutOrderStatus status) {
        final UUID orderId = takeOutOrderRepository.save(order(status)).getId();
        assertThatThrownBy(() -> takeOutOrderService.serve(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final TakeOutOrder expected = takeOutOrderRepository.save(order(TakeOutOrderStatus.SERVED));
        final TakeOutOrder actual = takeOutOrderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(TakeOutOrderStatus.COMPLETED);
    }

    @DisplayName("포장 주문의 경우 서빙된 주문만 완료할 수 있다.")
    @EnumSource(value = TakeOutOrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeTakeoutAndEatInOrder(final TakeOutOrderStatus status) {
        final UUID orderId = takeOutOrderRepository.save(order(status)).getId();
        assertThatThrownBy(() -> takeOutOrderService.complete(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        takeOutOrderRepository.save(order(TakeOutOrderStatus.SERVED));
        takeOutOrderRepository.save(order(TakeOutOrderStatus.COMPLETED));
        final List<TakeOutOrder> actual = takeOutOrderService.findAll();
        assertThat(actual).hasSize(2);
    }

    private TakeOutOrder createOrderRequest(final OrderType orderType, final OrderLineItem... orderLineItems) {
        return createOrderRequest(orderType, Arrays.asList(orderLineItems));
    }

    private TakeOutOrder createOrderRequest(final OrderType orderType, final List<OrderLineItem> orderLineItems) {
        final TakeOutOrder order = new TakeOutOrder();
        order.setType(orderType);
        order.setOrderLineItems(orderLineItems);
        return order;
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
