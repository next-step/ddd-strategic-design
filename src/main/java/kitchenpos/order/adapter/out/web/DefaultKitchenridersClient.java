package kitchenpos.order.adapter.out.web;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

import kitchenpos.order.application.port.out.KitchenridersClient;

@Component
public class DefaultKitchenridersClient implements KitchenridersClient {
    @Override
    public void requestDelivery(final UUID orderId, final BigDecimal amount, final String deliveryAddress) {
    }
}
