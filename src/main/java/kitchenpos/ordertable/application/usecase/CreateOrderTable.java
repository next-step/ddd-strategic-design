package kitchenpos.ordertable.application.usecase;

import kitchenpos.ordertable.domain.OrderTable;

public interface CreateOrderTable {
    OrderTable create(final OrderTable request);
}
