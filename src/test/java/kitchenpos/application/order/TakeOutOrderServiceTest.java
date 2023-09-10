package kitchenpos.application.order;

import kitchenpos.application.InMemoryMenuRepository;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.order.takeoutorder.application.TakeOutOrderService;
import kitchenpos.order.common.domain.OrderLineItem;
import kitchenpos.order.takeoutorder.domain.TakeOutOrder;
import kitchenpos.order.takeoutorder.domain.TakeOutOrderRepository;
import kitchenpos.order.takeoutorder.domain.TakeOutOrderStatus;
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

import static kitchenpos.Fixture.OrderFixtures.takeOutOrder;
import static kitchenpos.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class TakeOutOrderServiceTest {
    private TakeOutOrderRepository orderRepository;
    private MenuRepository menuRepository;
    private TakeOutOrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = new InMemoryTakeOutOrderRepository();
        menuRepository = new InMemoryMenuRepository();
        orderService = new TakeOutOrderService(orderRepository, menuRepository);
    }

    @DisplayName("1개 이상의 등록된 메뉴로 포장 주문을 등록할 수 있다.")
    @Test
    void createTakeoutOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeOutOrder expected = createTakeOutOrderRequest(createOrderLineItemRequest(menuId, 19_000L, 3L));
        final TakeOutOrder actual = orderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getStatus()).isEqualTo(TakeOutOrderStatus.WAITING),
                () -> assertThat(actual.getOrderDateTime()).isNotNull(),
                () -> assertThat(actual.getOrderLineItems()).hasSize(1)
        );
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<OrderLineItem> orderLineItems) {
        final TakeOutOrder expected = createTakeOutOrderRequest(orderLineItems);
        assertThatThrownBy(() -> orderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static List<Arguments> orderLineItems() {
        return Arrays.asList(
                null,
                Arguments.of(Collections.emptyList()),
                Arguments.of(List.of(createOrderLineItemRequest(INVALID_ID, 19_000L, 3L)))
        );
    }

    @DisplayName("매장 주문을 제외한 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.")
    @ValueSource(longs = -1L)
    @ParameterizedTest
    void createWithoutEatInOrder(final long quantity) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeOutOrder expected = createTakeOutOrderRequest(
                createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertThatThrownBy(() -> orderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, false, menuProduct())).getId();
        final TakeOutOrder expected = createTakeOutOrderRequest(createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> orderService.create(expected))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeOutOrder expected = createTakeOutOrderRequest(createOrderLineItemRequest(menuId, 16_000L, 3L));
        assertThatThrownBy(() -> orderService.create(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = orderRepository.save(takeOutOrder(TakeOutOrderStatus.WAITING)).getId();
        final TakeOutOrder actual = orderService.accept(orderId);
        assertThat(actual.getStatus()).isEqualTo(TakeOutOrderStatus.ACCEPTED);
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = TakeOutOrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final TakeOutOrderStatus status) {
        final UUID orderId = orderRepository.save(takeOutOrder(status)).getId();
        assertThatThrownBy(() -> orderService.accept(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final UUID orderId = orderRepository.save(takeOutOrder(TakeOutOrderStatus.ACCEPTED)).getId();
        final TakeOutOrder actual = orderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(TakeOutOrderStatus.SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = TakeOutOrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final TakeOutOrderStatus status) {
        final UUID orderId = orderRepository.save(takeOutOrder(status)).getId();
        assertThatThrownBy(() -> orderService.serve(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final TakeOutOrder expected = orderRepository.save(takeOutOrder(TakeOutOrderStatus.SERVED));
        final TakeOutOrder actual = orderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(TakeOutOrderStatus.COMPLETED);
    }

    @DisplayName("포장 주문의 경우 서빙된 주문만 완료할 수 있다.")
    @EnumSource(value = TakeOutOrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeTakeoutAndEatInOrder(final TakeOutOrderStatus status) {
        final UUID orderId = orderRepository.save(takeOutOrder(status)).getId();
        assertThatThrownBy(() -> orderService.complete(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    private TakeOutOrder createTakeOutOrderRequest(final OrderLineItem... orderLineItems) {
        return createTakeOutOrderRequest(Arrays.asList(orderLineItems));
    }

    private TakeOutOrder createTakeOutOrderRequest(final List<OrderLineItem> orderLineItems) {
        final TakeOutOrder order = new TakeOutOrder();
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
