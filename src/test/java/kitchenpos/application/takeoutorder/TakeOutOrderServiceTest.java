package kitchenpos.application.takeoutorder;

import kitchenpos.application.menu.InMemoryMenuRepository;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.takeoutorder.application.TakeOutOrderService;
import kitchenpos.takeoutorder.domain.TakeOutOrder;
import kitchenpos.takeoutorder.domain.TakeOutOrderLineItem;
import kitchenpos.takeoutorder.domain.TakeOutOrderRepository;
import kitchenpos.takeoutorder.domain.TakeOutOrderStatus;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class TakeOutOrderServiceTest {
    private TakeOutOrderRepository takeOutOrderRepository;
    private MenuRepository menuRepository;
    private TakeOutOrderService takeOutOrderService;

    private static TakeOutOrderLineItem createTakeOutOrderLineItemRequest(final UUID menuId, final long price, final long quantity) {
        final TakeOutOrderLineItem orderLineItem = new TakeOutOrderLineItem();
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
                Arguments.of(Arrays.asList(createTakeOutOrderLineItemRequest(INVALID_ID, 19_000L, 3L)))
        );
    }

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
        final TakeOutOrder expected = createTakeOutOrderRequest(createTakeOutOrderLineItemRequest(menuId, 19_000L, 3L));
        final TakeOutOrder actual = takeOutOrderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getStatus()).isEqualTo(TakeOutOrderStatus.WAITING),
                () -> assertThat(actual.getOrderDateTime()).isNotNull(),
                () -> assertThat(actual.getTakeOutOrderLineItems()).hasSize(1)
        );
    }

    @DisplayName("매장 주문을 제외한 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.")
    @ValueSource(longs = -1L)
    @ParameterizedTest
    void createWithoutEatInOrder(final long quantity) {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeOutOrder expected = createTakeOutOrderRequest(createTakeOutOrderLineItemRequest(menuId, 19_000L, quantity));
        assertThatThrownBy(() -> takeOutOrderService.create(expected)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, false, menuProduct())).getId();
        final TakeOutOrder expected = createTakeOutOrderRequest(createTakeOutOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> takeOutOrderService.create(expected)).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct())).getId();
        final TakeOutOrder expected = createTakeOutOrderRequest(createTakeOutOrderLineItemRequest(menuId, 16_000L, 3L));
        assertThatThrownBy(() -> takeOutOrderService.create(expected)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문을 접수한다.")
    @Test
    void accept() {
        final UUID orderId = takeOutOrderRepository.save(takeOutOrder(TakeOutOrderStatus.WAITING)).getId();
        final TakeOutOrder actual = takeOutOrderService.accept(orderId);
        assertThat(actual.getStatus()).isEqualTo(TakeOutOrderStatus.ACCEPTED);
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = TakeOutOrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final TakeOutOrderStatus status) {
        final UUID orderId = takeOutOrderRepository.save(takeOutOrder(status)).getId();
        assertThatThrownBy(() -> takeOutOrderService.accept(orderId)).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final UUID orderId = takeOutOrderRepository.save(takeOutOrder(TakeOutOrderStatus.ACCEPTED)).getId();
        final TakeOutOrder actual = takeOutOrderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(TakeOutOrderStatus.SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = TakeOutOrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final TakeOutOrderStatus status) {
        final UUID orderId = takeOutOrderRepository.save(takeOutOrder(status)).getId();
        assertThatThrownBy(() -> takeOutOrderService.serve(orderId)).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final TakeOutOrder expected = takeOutOrderRepository.save(takeOutOrder(TakeOutOrderStatus.SERVED));
        final TakeOutOrder actual = takeOutOrderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(TakeOutOrderStatus.COMPLETED);
    }

    @DisplayName("포장 및 매장 주문의 경우 서빙된 주문만 완료할 수 있다.")
    @EnumSource(value = TakeOutOrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeTakeoutAndEatInOrder(final TakeOutOrderStatus status) {
        final UUID orderId = takeOutOrderRepository.save(takeOutOrder(status)).getId();
        assertThatThrownBy(() -> takeOutOrderService.complete(orderId)).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        takeOutOrderRepository.save(takeOutOrder(TakeOutOrderStatus.SERVED));
        takeOutOrderRepository.save(takeOutOrder(TakeOutOrderStatus.ACCEPTED));
        final List<TakeOutOrder> actual = takeOutOrderService.findAll();
        assertThat(actual).hasSize(2);
    }

    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<TakeOutOrderLineItem> orderLineItems) {
        final TakeOutOrder expected = createTakeOutOrderRequest(orderLineItems);
        assertThatThrownBy(() -> takeOutOrderService.create(expected)).isInstanceOf(IllegalArgumentException.class);
    }

    private TakeOutOrder createTakeOutOrderRequest(final TakeOutOrderLineItem... orderLineItems) {
        final TakeOutOrder order = new TakeOutOrder();
        order.setTakeOutOrderLineItems(Arrays.asList(orderLineItems));
        return order;
    }

    private TakeOutOrder createTakeOutOrderRequest(final List<TakeOutOrderLineItem> orderLineItems) {
        final TakeOutOrder order = new TakeOutOrder();
        order.setTakeOutOrderLineItems(orderLineItems);
        return order;
    }
}
