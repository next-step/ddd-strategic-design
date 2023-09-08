package kitchenpos.application.order.takeoutorder;

import kitchenpos.application.exhibit.menu.InMemoryMenuRepository;
import kitchenpos.exhibit.menu.domain.MenuRepository;
import kitchenpos.order.shared.domain.OrderLineItem;
import kitchenpos.order.shared.domain.OrderStatus;
import kitchenpos.order.takeoutorder.application.TakeOutOrderService;
import kitchenpos.order.takeoutorder.domain.TakeOutOrder;
import kitchenpos.order.takeoutorder.domain.TakeOutOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.*;

import static kitchenpos.Fixtures.*;
import static kitchenpos.order.shared.domain.OrderStatus.*;
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
        final TakeOutOrder expected = createTakeOutOrderRequest(createOrderLineItemRequest(menuId, 19_000L, 3L));
        final TakeOutOrder actual = takeOutOrderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getStatus()).isEqualTo(WAITING),
                () -> assertThat(actual.getOrderDateTime()).isNotNull(),
                () -> assertThat(actual.getOrderLineItems()).hasSize(1)
        );
    }

    @DisplayName("매장 주문을 제외한 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.")
    @ValueSource(longs = -1L)
    @ParameterizedTest
    void createWithoutEatInOrder(final long quantity) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeOutOrder expected = createTakeOutOrderRequest(createOrderLineItemRequest(menuId, 19_000L, quantity));
        assertThatThrownBy(() -> takeOutOrderService.create(expected)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, false, menuProduct())).getId();
        final TakeOutOrder expected = createTakeOutOrderRequest(createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> takeOutOrderService.create(expected)).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeOutOrder expected = createTakeOutOrderRequest(createOrderLineItemRequest(menuId, 16_000L, 3L));
        assertThatThrownBy(() -> takeOutOrderService.create(expected)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = takeOutOrderRepository.save(takeOutOrder(WAITING)).getId();
        final TakeOutOrder actual = takeOutOrderService.accept(orderId);
        assertThat(actual.getStatus()).isEqualTo(ACCEPTED);
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final OrderStatus status) {
        final UUID orderId = takeOutOrderRepository.save(takeOutOrder(status)).getId();
        assertThatThrownBy(() -> takeOutOrderService.accept(orderId)).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final UUID orderId = takeOutOrderRepository.save(takeOutOrder(ACCEPTED)).getId();
        final TakeOutOrder actual = takeOutOrderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final OrderStatus status) {
        final UUID orderId = takeOutOrderRepository.save(takeOutOrder(status)).getId();
        assertThatThrownBy(() -> takeOutOrderService.serve(orderId)).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final TakeOutOrder expected = takeOutOrderRepository.save(takeOutOrder(SERVED));
        final TakeOutOrder actual = takeOutOrderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(COMPLETED);
    }

    @DisplayName("포장 및 매장 주문의 경우 서빙된 주문만 완료할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeTakeoutAndEatInOrder(final OrderStatus status) {
        final UUID orderId = takeOutOrderRepository.save(takeOutOrder(status)).getId();
        assertThatThrownBy(() -> takeOutOrderService.complete(orderId)).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        takeOutOrderRepository.save(takeOutOrder(SERVED));
        takeOutOrderRepository.save(takeOutOrder(ACCEPTED));
        final List<TakeOutOrder> actual = takeOutOrderService.findAll();
        assertThat(actual).hasSize(2);
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<OrderLineItem> orderLineItems) {
        final TakeOutOrder expected = createTakeOutOrderRequest(orderLineItems);
        assertThatThrownBy(() -> takeOutOrderService.create(expected)).isInstanceOf(IllegalArgumentException.class);
    }

    private static OrderLineItem createOrderLineItemRequest(final UUID menuId, final long price, final long quantity) {
        final OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSeq(new Random().nextLong());
        orderLineItem.setMenuId(menuId);
        orderLineItem.setPrice(BigDecimal.valueOf(price));
        orderLineItem.setQuantity(quantity);
        return orderLineItem;
    }

    private static List<Arguments> orderLineItems() {
        return Arrays.asList(
                null,
                Arguments.of(Collections.emptyList()),
                Arguments.of(Arrays.asList(createOrderLineItemRequest(INVALID_ID, 19_000L, 3L)))
        );
    }

    private TakeOutOrder createTakeOutOrderRequest(final OrderLineItem... orderLineItems) {
        final TakeOutOrder order = new TakeOutOrder();
        order.setOrderLineItems(Arrays.asList(orderLineItems));
        return order;
    }

    private TakeOutOrder createTakeOutOrderRequest(final List<OrderLineItem> orderLineItems) {
        final TakeOutOrder order = new TakeOutOrder();
        order.setOrderLineItems(orderLineItems);
        return order;
    }
}
