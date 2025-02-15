package kitchenpos.order.delivery.domain.port;

import kitchenpos.order.delivery.domain.model.DeliveryOrder;
import org.springframework.stereotype.Component;

@Component
public interface RiderPort {

    void requestRider(DeliveryOrder order);
}
