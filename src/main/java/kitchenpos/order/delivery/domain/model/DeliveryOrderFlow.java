package kitchenpos.order.delivery.domain.model;

import java.util.Arrays;
import kitchenpos.order.order.model.OrderStatus;

public enum DeliveryOrderFlow {
    ACCEPTED(OrderStatus.ACCEPTED, OrderStatus.WAITING),
    SERVED(OrderStatus.SERVED, OrderStatus.ACCEPTED),
    DELIVERING(OrderStatus.DELIVERING, OrderStatus.SERVED),
    DELIVERED(OrderStatus.DELIVERED, OrderStatus.DELIVERING),
    COMPLETED(OrderStatus.COMPLETED, OrderStatus.DELIVERED);

    private final OrderStatus nextStatus;
    private final OrderStatus previousStatus;

    DeliveryOrderFlow(OrderStatus nextStatus, OrderStatus previousStatus) {
        this.nextStatus = nextStatus;
        this.previousStatus = previousStatus;
    }

    public static DeliveryOrderFlow from(OrderStatus status) {
        return valueOf(status.name());
    }

    public boolean validateOrderStatus(OrderStatus nextOrderStatus) {
        DeliveryOrderFlow nextStatus = Arrays.stream(values())
                .filter(v -> v.nextStatus == nextOrderStatus)
                .findFirst()
                .orElseThrow();
        return nextStatus.previousStatus != (this.nextStatus);
    }

    public boolean isRiderNecessary(OrderStatus orderStatus) {
        return orderStatus == OrderStatus.SERVED;
    }
}
