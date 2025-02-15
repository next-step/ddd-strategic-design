package kitchenpos.order.infrastructure.adaptor;

import java.math.BigDecimal;
import kitchenpos.order.domain.delivery.model.DeliveryOrder;
import kitchenpos.order.domain.delivery.port.RiderPort;
import kitchenpos.order.infrastructure.external.KitchenridersClient;

public class RiderAdaptor implements RiderPort {

    private KitchenridersClient kitchenridersClient;

    @Override
    public void requestRider(DeliveryOrder order) {
        kitchenridersClient.requestDelivery(order.getId(), BigDecimal.ONE, "address");
    }
}
