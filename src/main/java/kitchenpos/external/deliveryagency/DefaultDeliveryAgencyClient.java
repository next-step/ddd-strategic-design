package kitchenpos.external.deliveryagency;

import kitchenpos.external.deliveryagency.DeliveryAgencyClient;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class DefaultDeliveryAgencyClient implements DeliveryAgencyClient {
    @Override
    public void requestDelivery(final UUID orderId, final BigDecimal amount, final String deliveryAddress) {
    }
}
