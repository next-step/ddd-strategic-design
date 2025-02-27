package kitchenpos.deliveryorder.domain;

import java.math.BigDecimal;
import java.util.UUID;

public interface KitchenRiders {
    void requestDelivery(UUID orderId, BigDecimal amount, String deliveryAddress);
}
