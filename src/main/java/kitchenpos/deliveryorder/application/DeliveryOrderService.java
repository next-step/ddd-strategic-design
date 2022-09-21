package kitchenpos.deliveryorder.application;

import kitchenpos.deliveryorder.domain.DeliveryOrder;

import java.util.UUID;

public class DeliveryOrderService {

    public DeliveryOrder startDelivery(final UUID orderId) {
        return new DeliveryOrder();
    }

    public DeliveryOrder completeDelivery(final UUID orderId) {
        return new DeliveryOrder();
    }
}
