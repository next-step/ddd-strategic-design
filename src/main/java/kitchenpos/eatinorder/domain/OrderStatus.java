package kitchenpos.eatinorder.domain;

public enum OrderStatus {
    WAITING, ACCEPTED, SERVED, @Deprecated DELIVERING, @Deprecated DELIVERED, COMPLETED
}
