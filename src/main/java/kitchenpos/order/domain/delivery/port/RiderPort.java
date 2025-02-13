package kitchenpos.order.domain.delivery.port;

import kitchenpos.order.domain.order.model.Order;

public interface RiderPort {

    void requestRider(Order order);
}
