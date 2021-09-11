package kitchenpos.ordertable.application.usecase;

import kitchenpos.ordertable.domain.OrderTable;

import java.util.List;

public interface RetrieveOrderTable {
    List<OrderTable> findAll();
}
