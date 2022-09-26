package kitchenpos.deliveryorder.application;

import kitchenpos.deliveryorder.domain.DeliveryOrder;
import kitchenpos.eatinorder.order.domain.EatInOrder;

import java.util.List;
import java.util.UUID;

public class DeliveryOrderService {

    public DeliveryOrder create(final EatInOrder request) {
        return new DeliveryOrder();
    }

    public DeliveryOrder accept(final UUID orderId) {
        return new DeliveryOrder();
    }

    public DeliveryOrder serve(final UUID orderId) {
        return new DeliveryOrder();
    }

    public DeliveryOrder startDelivery(final UUID orderId) {
        return new DeliveryOrder();
    }

    public DeliveryOrder completeDelivery(final UUID orderId) {
        return new DeliveryOrder();
    }

    public DeliveryOrder complete(final UUID orderId) {
        return new DeliveryOrder();
    }

    public List<DeliveryOrder> findAll() {
        return List.of(new DeliveryOrder());
    }
}
