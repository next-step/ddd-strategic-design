package kitchenpos.order.common.application;

import static kitchenpos.Fixtures.order;
import static kitchenpos.Fixtures.orderTable;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import kitchenpos.order.common.domain.Order;
import kitchenpos.order.common.domain.OrderLineItem;
import kitchenpos.order.common.domain.OrderRepository;
import kitchenpos.order.common.domain.OrderStatus;
import kitchenpos.order.common.domain.OrderType;
import kitchenpos.order.common.infra.InMemoryOrderRepository;
import kitchenpos.order.eatinorder.domain.OrderTable;
import kitchenpos.order.eatinorder.domain.OrderTableRepository;
import kitchenpos.order.eatinorder.infra.InMemoryOrderTableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    private OrderTableRepository orderTableRepository;
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderTableRepository = new InMemoryOrderTableRepository();
        orderRepository = new InMemoryOrderRepository();
        orderService = new OrderService(orderRepository);
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        final OrderTable orderTable = orderTableRepository.save(orderTable(true, 4));
        orderRepository.save(order(OrderStatus.SERVED, orderTable));
        orderRepository.save(order(OrderStatus.DELIVERED, "서울시 송파구 위례성대로 2"));
        final List<Order> actual = orderService.findAll();
        assertThat(actual).hasSize(2);
    }

    private Order createOrderRequest(
        final OrderType type,
        final String deliveryAddress,
        final OrderLineItem... orderLineItems
    ) {
        final Order order = new Order();
        order.setType(type);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderLineItems(Arrays.asList(orderLineItems));
        return order;
    }

    private Order createOrderRequest(final OrderType orderType, final OrderLineItem... orderLineItems) {
        return createOrderRequest(orderType, Arrays.asList(orderLineItems));
    }

    private Order createOrderRequest(final OrderType orderType, final List<OrderLineItem> orderLineItems) {
        final Order order = new Order();
        order.setType(orderType);
        order.setOrderLineItems(orderLineItems);
        return order;
    }

    private Order createOrderRequest(
        final OrderType type,
        final UUID orderTableId,
        final OrderLineItem... orderLineItems
    ) {
        final Order order = new Order();
        order.setType(type);
        order.setOrderTableId(orderTableId);
        order.setOrderLineItems(Arrays.asList(orderLineItems));
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
