package kitchenpos.order.infrastructure.adaptor;

import java.math.BigDecimal;
import kitchenpos.order.domain.model.Order;
import kitchenpos.order.domain.port.RiderPort;
import kitchenpos.order.infrastructure.external.KitchenridersClient;

public class RiderAdaptor implements RiderPort {

    private KitchenridersClient kitchenridersClient;

    @Override
    public void requestRider(Order order) {
        kitchenridersClient.requestDelivery(order.getId(), BigDecimal.ONE, "address");
    }
}
