package kitchenpos.order.common.application;

import java.util.UUID;
import kitchenpos.order.common.domain.Order;

public interface ServeOrder {

    Order serve(UUID orderId);
}
