package kitchenpos.order.domain.port;

import kitchenpos.order.domain.model.Order;

public interface RiderPort {

    void requestRider(Order order);
}
