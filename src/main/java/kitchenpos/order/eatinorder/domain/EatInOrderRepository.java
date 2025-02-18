package kitchenpos.order.eatinorder.domain;

import kitchenpos.order.common.domain.OrderStatus;

public interface EatInOrderRepository {

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status);
}

