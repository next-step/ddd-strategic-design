package kitchenpos.order.domain.delivery.flow;

import java.util.Map;
import kitchenpos.order.domain.order.model.OrderFlow;
import kitchenpos.order.domain.order.model.OrderStatus;

public class DeliveryOrderFlow implements OrderFlow {

    private static final Map<OrderStatus, OrderStatus> STATUS_FLOW = Map.of(
            OrderStatus.WAITING, OrderStatus.ACCEPTED,
            OrderStatus.ACCEPTED, OrderStatus.SERVED,
            OrderStatus.SERVED, OrderStatus.DELIVERING,
            OrderStatus.DELIVERING, OrderStatus.DELIVERED,
            OrderStatus.DELIVERED, OrderStatus.COMPLETED
    );

    @Override
    public OrderStatus performNextFlow(OrderStatus currentStatus) {
        return STATUS_FLOW.getOrDefault(currentStatus, currentStatus);
    }
}

