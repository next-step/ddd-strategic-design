package kitchenpos.order.application.port.in;

import java.util.List;
import java.util.UUID;

import kitchenpos.order.domain.Order;

public interface OrderUseCase {
    Order create(Order request);

    Order accept(UUID orderId);

    Order serve(UUID orderId);

    Order startDelivery(UUID orderId);

    Order completeDelivery(UUID orderId);

    Order complete(UUID orderId);

    List<Order> findAll();
}
