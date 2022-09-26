package kitchenpos.deliveryorder.application;

import static kitchenpos.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.math.BigDecimal;
import java.util.*;
import kitchenpos.deliveryorder.domain.*;
import kitchenpos.deliveryorder.infra.FakeKitchenridersClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

class DeliveryOrderServiceTest {

    private static final UUID menuId = UUID.randomUUID();

    private DeliveryOrderRepository deliveryOrderRepository;
    private FakeKitchenridersClient kitchenridersClient;
    private DeliveryOrderService deliveryOrderService;

    @BeforeEach
    void setUp() {
        deliveryOrderRepository = new InMemoryDeliveryOrderRepository();
        kitchenridersClient = new FakeKitchenridersClient();
        deliveryOrderService = new DeliveryOrderService(deliveryOrderRepository, kitchenridersClient);
    }

    @DisplayName("1개 이상의 등록된 메뉴로 배달 주문을 등록할 수 있다.")
    @Test
    void createDeliveryOrder() {
        final DeliveryOrder expected = createOrderRequest(
            DeliveryOrderType.DELIVERY, "서울시 송파구 위례성대로 2", createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        final DeliveryOrder actual = deliveryOrderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getType()).isEqualTo(expected.getType()),
            () -> assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.WAITING),
            () -> assertThat(actual.getOrderDateTime()).isNotNull(),
            () -> assertThat(actual.getOrderLineItems()).hasSize(1),
            () -> assertThat(actual.getDeliveryAddress()).isEqualTo(expected.getDeliveryAddress())
        );
    }

    @DisplayName("1개 이상의 등록된 메뉴로 포장 주문을 등록할 수 있다.")
    @Test
    void createTakeoutOrder() {
        final DeliveryOrder expected = createOrderRequest(DeliveryOrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, 3L));
        final DeliveryOrder actual = deliveryOrderService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getType()).isEqualTo(expected.getType()),
            () -> assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.WAITING),
            () -> assertThat(actual.getOrderDateTime()).isNotNull(),
            () -> assertThat(actual.getOrderLineItems()).hasSize(1)
        );
    }

    @DisplayName("주문 유형이 올바르지 않으면 등록할 수 없다.")
    @NullSource
    @ParameterizedTest
    void create(final DeliveryOrderType type) {
        final DeliveryOrder expected = createOrderRequest(type, createOrderLineItemRequest(menuId, 19_000L, 3L));
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Disabled
    @DisplayName("메뉴가 없으면 등록할 수 없다.")
    @MethodSource("orderLineItems")
    @ParameterizedTest
    void create(final List<DeliveryOrderLineItem> eatInOrderLineItems) {
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
    void createDeliveryOrder(final long quantity) {
        final DeliveryOrder expected = createOrderRequest(
            DeliveryOrderType.EAT_IN, createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertDoesNotThrow(() -> deliveryOrderService.create(expected));
    }

    @DisplayName("매장 주문을 제외한 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.")
    @ValueSource(longs = -1L)
    @ParameterizedTest
    void createWithoutDeliveryOrder(final long quantity) {
        final DeliveryOrder expected = createOrderRequest(
            DeliveryOrderType.TAKEOUT, createOrderLineItemRequest(menuId, 19_000L, quantity)
        );
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배달 주소가 올바르지 않으면 배달 주문을 등록할 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void create(final String deliveryAddress) {
        final DeliveryOrder expected = createOrderRequest(
            DeliveryOrderType.DELIVERY, deliveryAddress, createOrderLineItemRequest(menuId, 19_000L, 3L)
        );
        assertThatThrownBy(() -> deliveryOrderService.create(expected))
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
    @EnumSource(value = DeliveryOrderStatus.class, names = "WAITING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void accept(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "집")).getId();
        assertThatThrownBy(() -> deliveryOrderService.accept(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("배달 주문을 접수되면 배달 대행사를 호출한다.")
    @Test
    void acceptDeliveryOrder() {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.WAITING, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = deliveryOrderService.accept(orderId);
        assertAll(
            () -> assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.ACCEPTED),
            () -> assertThat(kitchenridersClient.getOrderId()).isEqualTo(orderId),
            () -> assertThat(kitchenridersClient.getDeliveryAddress()).isEqualTo("서울시 송파구 위례성대로 2")
        );
    }

    @DisplayName("주문을 서빙한다.")
    @Test
    void serve() {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.ACCEPTED, "집")).getId();
        final DeliveryOrder actual = deliveryOrderService.serve(orderId);
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.SERVED);
    }

    @DisplayName("접수된 주문만 서빙할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "ACCEPTED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void serve(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "집")).getId();
        assertThatThrownBy(() -> deliveryOrderService.serve(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달한다.")
    @Test
    void startDelivery() {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.SERVED, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = deliveryOrderService.startDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.DELIVERING);
    }

    @DisplayName("서빙된 주문만 배달할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void startDelivery(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> deliveryOrderService.startDelivery(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달 완료한다.")
    @Test
    void completeDelivery() {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.DELIVERING, "서울시 송파구 위례성대로 2")).getId();
        final DeliveryOrder actual = deliveryOrderService.completeDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.DELIVERED);
    }

    @DisplayName("배달 중인 주문만 배달 완료할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "DELIVERING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDelivery(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> deliveryOrderService.completeDelivery(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 완료한다.")
    @Test
    void complete() {
        final DeliveryOrder expected = deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.DELIVERED, "서울시 송파구 위례성대로 2"));
        final DeliveryOrder actual = deliveryOrderService.complete(expected.getId());
        assertThat(actual.getStatus()).isEqualTo(DeliveryOrderStatus.COMPLETED);
    }

    @DisplayName("배달 주문의 경우 배달 완료된 주문만 완료할 수 있다.")
    @EnumSource(value = DeliveryOrderStatus.class, names = "DELIVERED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDeliveryOrder(final DeliveryOrderStatus status) {
        final UUID orderId = deliveryOrderRepository.save(deliveryOrder(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> deliveryOrderService.complete(orderId))
            .isInstanceOf(IllegalStateException.class);
    }

    private DeliveryOrder createOrderRequest(
        final DeliveryOrderType type,
        final String deliveryAddress,
        final DeliveryOrderLineItem... eatInOrderLineItems
    ) {
        final DeliveryOrder eatInOrder = new DeliveryOrder();
        eatInOrder.setType(type);
        eatInOrder.setDeliveryAddress(deliveryAddress);
        eatInOrder.setOrderLineItems(Arrays.asList(eatInOrderLineItems));
        return eatInOrder;
    }

    private DeliveryOrder createOrderRequest(final DeliveryOrderType deliveryOrderType, final DeliveryOrderLineItem... eatInOrderLineItems) {
        return createOrderRequest(deliveryOrderType, Arrays.asList(eatInOrderLineItems));
    }

    private DeliveryOrder createOrderRequest(final DeliveryOrderType deliveryOrderType, final List<DeliveryOrderLineItem> eatInOrderLineItems) {
        final DeliveryOrder eatInOrder = new DeliveryOrder();
        eatInOrder.setType(deliveryOrderType);
        eatInOrder.setOrderLineItems(eatInOrderLineItems);
        return eatInOrder;
    }

    private DeliveryOrder createOrderRequest(
        final DeliveryOrderType type,
        final UUID orderTableId,
        final DeliveryOrderLineItem... eatInOrderLineItems
    ) {
        final DeliveryOrder eatInOrder = new DeliveryOrder();
        eatInOrder.setType(type);
        eatInOrder.setOrderTableId(orderTableId);
        eatInOrder.setOrderLineItems(Arrays.asList(eatInOrderLineItems));
        return eatInOrder;
    }

    private static DeliveryOrderLineItem createOrderLineItemRequest(final UUID menuId, final long price, final long quantity) {
        final DeliveryOrderLineItem eatInOrderLineItem = new DeliveryOrderLineItem();
        eatInOrderLineItem.setSeq(new Random().nextLong());
        eatInOrderLineItem.setMenuId(menuId);
        eatInOrderLineItem.setPrice(BigDecimal.valueOf(price));
        eatInOrderLineItem.setQuantity(quantity);
        return eatInOrderLineItem;
    }
}
