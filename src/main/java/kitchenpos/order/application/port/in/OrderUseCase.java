package kitchenpos.order.application.port.in;

import java.util.List;
import java.util.UUID;

import kitchenpos.order.domain.Order;

/**
 * 테스트코드를 유지하기 위한 임시 클래스
 * Delivery, Eatin, Takeout으로 분할되어야 한다.
 */
public interface OrderUseCase {
	Order create(final Order request);

	Order accept(final UUID orderId);

	Order serve(final UUID orderId);

	Order startDelivery(final UUID orderId);

	Order	completeDelivery(final UUID orderId);

	Order complete(final UUID orderId);

	List<Order> findAll();
}
