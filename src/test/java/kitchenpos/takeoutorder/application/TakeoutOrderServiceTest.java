package kitchenpos.takeoutorder.application;

import static kitchenpos.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.math.BigDecimal;
import java.util.*;
import kitchenpos.takeoutorder.domain.TakeoutOrder;
import kitchenpos.takeoutorder.domain.TakeoutOrderLineItem;
import kitchenpos.takeoutorder.domain.TakeoutOrderStatus;
import kitchenpos.takeoutorder.domain.TakeoutOrderType;
import kitchenpos.takeoutorder.domain.InMemoryTakeoutOrderRepository;
import kitchenpos.takeoutorder.domain.TakeoutOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

class TakeoutOrderServiceTest {

    private static final UUID menuId = UUID.randomUUID();

    private TakeoutOrderRepository takeoutOrderRepository;
    private TakeoutOrderService takeoutOrderService;

    @BeforeEach
    void setUp() {
        takeoutOrderRepository = new InMemoryTakeoutOrderRepository();
        takeoutOrderService = new TakeoutOrderService(takeoutOrderRepository);
    }

    @DisplayName("1개 이상의 등록된 메뉴로 포장 주문을 등록할 수 있다.")
    @Test
    void createTakeoutOrder() {
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

    @DisplayName("주문 유형이 올바르지 않으면 등록할 수 없다.")
    @NullSource
    @ParameterizedTest
    void create(final TakeoutOrderType type) {
        final TakeoutOrder expected = createOrderRequest(type, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> takeoutOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Disabled
    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<TakeoutOrderLineItem> eatInOrderLineItems) {
        // 메뉴 실존여부 검증
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
        final TakeoutOrder expected = createOrderRequest(
            TakeoutOrderType.EAT_IN, createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertDoesNotThrow(() -> takeoutOrderService.create(expected));
    }

    @DisplayName("매장 주문을 제외한 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.")
    @ValueSource(longs = -1L)
    @ParameterizedTest
    void createWithoutTakeoutOrder(final long quantity) {
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
        final TakeoutOrder expected = createOrderRequest(
            TakeoutOrderType.DELIVERY, deliveryAddress, createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        assertThatThrownBy(() -> takeoutOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Disabled
    @DisplayName("숨겨진 메뉴는 주문할 수 없다.")
    @Test
    void createNotDisplayedMenuOrder() {
        // 숨겨진 메뉴 검증
    }

    @Disabled
    @DisplayName("주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.")
    @Test
    void createNotMatchedMenuPriceOrder() {
        // 메뉴 가격와 주문 금액 일치 검증
    }

    @DisplayName("접수 대기 중인 주문만 접수할 수 있다.")
    @EnumSource(value = TakeoutOrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final TakeoutOrderStatus status) {
        final UUID orderId = takeoutOrderRepository.save(takeoutOrder(status)).getId();
        assertThatThrownBy(() -> takeoutOrderService.accept(orderId))
            .isInstanceOf(IllegalStateException.class);
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

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final TakeoutOrder expected = takeoutOrderRepository.save(takeoutOrder(TakeoutOrderStatus.SERVED));
        final TakeoutOrder actual = takeoutOrderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(TakeoutOrderStatus.COMPLETED);
    }

    private TakeoutOrder createOrderRequest(
        final TakeoutOrderType type,
        final String deliveryAddress,
        final TakeoutOrderLineItem... eatInOrderLineItems
    ) {
        final TakeoutOrder eatInOrder = new TakeoutOrder();
        eatInOrder.setType(type);
        eatInOrder.setDeliveryAddress(deliveryAddress);
        eatInOrder.setOrderLineItems(Arrays.asList(eatInOrderLineItems));
        return eatInOrder;
    }

    private TakeoutOrder createOrderRequest(final TakeoutOrderType deliveryOrderType, final TakeoutOrderLineItem... eatInOrderLineItems) {
        return createOrderRequest(deliveryOrderType, Arrays.asList(eatInOrderLineItems));
    }

    private TakeoutOrder createOrderRequest(final TakeoutOrderType deliveryOrderType, final List<TakeoutOrderLineItem> eatInOrderLineItems) {
        final TakeoutOrder eatInOrder = new TakeoutOrder();
        eatInOrder.setType(deliveryOrderType);
        eatInOrder.setOrderLineItems(eatInOrderLineItems);
        return eatInOrder;
    }

    private TakeoutOrder createOrderRequest(
        final TakeoutOrderType type,
        final UUID orderTableId,
        final TakeoutOrderLineItem... eatInOrderLineItems
    ) {
        final TakeoutOrder eatInOrder = new TakeoutOrder();
        eatInOrder.setType(type);
        eatInOrder.setOrderTableId(orderTableId);
        eatInOrder.setOrderLineItems(Arrays.asList(eatInOrderLineItems));
        return eatInOrder;
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
