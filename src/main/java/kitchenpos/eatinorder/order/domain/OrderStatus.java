package kitchenpos.eatinorder.order.domain;

public enum OrderStatus {
    WAITING, ACCEPTED, SERVED, @Deprecated DELIVERING, @Deprecated DELIVERED, COMPLETED
}
