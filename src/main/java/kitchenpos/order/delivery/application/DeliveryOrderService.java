package kitchenpos.order.delivery.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import kitchenpos.order.delivery.domain.DeliveryOrder;
import kitchenpos.order.delivery.domain.DeliveryOrderLineItems;
import kitchenpos.order.delivery.domain.DeliveryOrderRepository;
import kitchenpos.order.delivery.domain.OrderLineItem;
import kitchenpos.order.delivery.dto.DeliveryOrderRequest;
import kitchenpos.order.delivery.dto.DeliveryOrderResponse;
import kitchenpos.order.delivery.infra.KitchenridersClient;

public class DeliveryOrderService {
	private final DeliveryOrderRepository orderRepository;
	private final KitchenridersClient kitchenridersClient;

	public DeliveryOrderService(
		final DeliveryOrderRepository orderRepository,
		final KitchenridersClient kitchenridersClient
	) {
		this.orderRepository = orderRepository;
		this.kitchenridersClient = kitchenridersClient;
	}

	@Transactional
	public DeliveryOrderResponse create(final DeliveryOrderRequest request) {
		return DeliveryOrderResponse.from(orderRepository.save(DeliveryOrder.create(new DeliveryOrderLineItems(getOrderLineItems(request)), request.getDeliveryAddress())));
	}

	private List<OrderLineItem> getOrderLineItems(DeliveryOrderRequest request) {
		return request.getOrderLineItemRequests().stream()
				.map(orderLineItemRequest -> OrderLineItem.of(orderLineItemRequest.getQuantity(), orderLineItemRequest.getMenuId(), orderLineItemRequest.getPrice()))
				.collect(Collectors.toList());
	}

	@Transactional
	public DeliveryOrderResponse accept(final UUID orderId) {
		final DeliveryOrder order = findDeliveryOrder(orderId);
		return DeliveryOrderResponse.from(order.accept(kitchenridersClient));
	}

	private DeliveryOrder findDeliveryOrder(UUID orderId) {
		return orderRepository.findById(orderId)
			.orElseThrow(NoSuchElementException::new);
	}

	@Transactional
	public DeliveryOrderResponse serve(final UUID orderId) {
		final DeliveryOrder order = findDeliveryOrder(orderId);
		return DeliveryOrderResponse.from(order.serve());
	}

	@Transactional
	public DeliveryOrderResponse startDelivery(final UUID orderId) {
		final DeliveryOrder order = findDeliveryOrder(orderId);
		return DeliveryOrderResponse.from(order.startDelivery());
	}

	@Transactional
	public DeliveryOrderResponse completeDelivery(final UUID orderId) {
		final DeliveryOrder order = findDeliveryOrder(orderId);
		return DeliveryOrderResponse.from(order.completeDelivery());
	}

	@Transactional
	public DeliveryOrderResponse complete(final UUID orderId) {
		final DeliveryOrder order = findDeliveryOrder(orderId);
		return DeliveryOrderResponse.from(order.complete());
	}

	@Transactional(readOnly = true)
	public List<DeliveryOrderResponse> findAll() {
		return orderRepository.findAll().stream()
			.map(DeliveryOrderResponse::from)
			.collect(Collectors.toList());
	}
}
