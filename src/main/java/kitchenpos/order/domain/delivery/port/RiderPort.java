package kitchenpos.order.domain.delivery.port;

import kitchenpos.order.domain.delivery.model.DeliveryOrder;
import org.springframework.stereotype.Component;

@Component
public interface RiderPort {

    void requestRider(DeliveryOrder order);
}
