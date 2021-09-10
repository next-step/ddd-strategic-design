package kitchenpos.order.application;

import kitchenpos.order.application.usecase.CreateOrder;
import kitchenpos.order.application.usecase.ManipulateOrder;
import kitchenpos.order.application.usecase.RetrieveOrder;

public interface OrderService extends CreateOrder, ManipulateOrder, RetrieveOrder {
}
