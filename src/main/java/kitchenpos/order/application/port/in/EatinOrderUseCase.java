package kitchenpos.order.application.port.in;

import java.util.List;
import java.util.UUID;

import kitchenpos.order.domain.Order;

public interface EatinOrderUseCase {
	Order create(final Order request);

	Order accept(final UUID orderId);

	Order serve(final UUID orderId);

	Order startDelivery(final UUID orderId);

	Order	completeDelivery(final UUID orderId);

	Order complete(final UUID orderId);

	List<Order> findAll();
}
