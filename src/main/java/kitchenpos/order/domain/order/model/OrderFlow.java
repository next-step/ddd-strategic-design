package kitchenpos.order.domain.order.model;

public interface OrderFlow {
    OrderStatus performNextFlow(OrderStatus currentStatus);
}
