package kitchenpos.ordertable.application;

import kitchenpos.ordertable.application.usecase.CreateOrderTable;
import kitchenpos.ordertable.application.usecase.ManipulateOrderTable;
import kitchenpos.ordertable.application.usecase.RetrieveOrderTable;

public interface OrderTableService extends CreateOrderTable, ManipulateOrderTable, RetrieveOrderTable {
}
