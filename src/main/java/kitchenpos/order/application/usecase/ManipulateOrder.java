package kitchenpos.order.application.usecase;

import kitchenpos.order.domain.Order;

import java.util.UUID;

public interface ManipulateOrder {
    Order accept(final UUID orderId);

    Order serve(final UUID orderId);

    Order startDelivery(final UUID orderId);

    Order completeDelivery(final UUID orderId);

    Order complete(final UUID orderId);
}
