package kitchenpos.order.application.usecase;

import kitchenpos.order.domain.Order;

public interface CreateOrder {
    Order create(final Order request);
}
