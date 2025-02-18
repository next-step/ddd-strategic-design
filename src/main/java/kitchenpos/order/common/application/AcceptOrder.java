package kitchenpos.order.common.application;

import java.util.UUID;
import kitchenpos.order.common.domain.Order;

public interface AcceptOrder {

    Order accept(UUID orderId);
}
