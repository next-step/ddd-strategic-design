package kitchenpos.order.order.ui;

import kitchenpos.order.order.application.KitchenridersClient;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class DefaultKitchenridersClient implements KitchenridersClient {
    @Override
    public void requestDelivery(final UUID orderId, final BigDecimal amount, final String deliveryAddress) {
    }
}
