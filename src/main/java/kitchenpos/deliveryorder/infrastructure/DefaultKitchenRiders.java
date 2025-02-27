package kitchenpos.deliveryorder.infrastructure;

import kitchenpos.deliveryorder.domain.KitchenRiders;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class DefaultKitchenRiders implements KitchenRiders {
    @Override
    public void requestDelivery(final UUID orderId, final BigDecimal amount, final String deliveryAddress) {
    }
}
