package kitchenpos.order.deliveryorder.application;

import java.util.UUID;
import kitchenpos.order.common.domain.Order;

public interface StartDelivery {

    Order startDelivery(UUID orderId);
}
