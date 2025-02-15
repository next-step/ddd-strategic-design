package kitchenpos.order.delivery.infra.adaptor;

import java.math.BigDecimal;
import kitchenpos.order.delivery.domain.model.DeliveryOrder;
import kitchenpos.order.delivery.domain.port.RiderPort;
import kitchenpos.order.delivery.infra.external.KitchenridersClient;

public class RiderAdaptor implements RiderPort {

    private KitchenridersClient kitchenridersClient;

    @Override
    public void requestRider(DeliveryOrder order) {
        kitchenridersClient.requestDelivery(order.getId(), BigDecimal.ONE, order.getDeliveryAddress());
    }
}
