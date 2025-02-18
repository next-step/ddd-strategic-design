package kitchenpos.order.common.application;

import java.util.UUID;
import kitchenpos.order.common.domain.Order;

public interface CompleteOrder {

    Order complete(UUID orderId);
}
