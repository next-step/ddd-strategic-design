package kitchenpos.order.common.application;

import kitchenpos.order.common.domain.Order;

public interface CreateOrder {

    Order create(Order order);
}
