package kitchenpos.order.takeout.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.order.eatin.domain.OrderTableRepository;
import kitchenpos.order.takeout.domain.OrderLineItem;
import kitchenpos.order.takeout.domain.TakeoutOrder;
import kitchenpos.order.takeout.domain.TakeoutOrderLineItems;
import kitchenpos.order.takeout.domain.TakeoutOrderRepository;
import kitchenpos.order.takeout.dto.TakeoutOrderRequest;
import kitchenpos.order.takeout.dto.TakeoutOrderResponse;

public class TakeoutOrderService {
	private final TakeoutOrderRepository orderRepository;
	private final MenuRepository menuRepository;
	private final OrderTableRepository orderTableRepository;

	public TakeoutOrderService(
		final TakeoutOrderRepository orderRepository,
		final MenuRepository menuRepository,
		final OrderTableRepository orderTableRepository
	) {
		this.orderRepository = orderRepository;
		this.menuRepository = menuRepository;
		this.orderTableRepository = orderTableRepository;
	}

	@Transactional
	public TakeoutOrderResponse create(final TakeoutOrderRequest request) {
		return TakeoutOrderResponse.from(orderRepository.save(TakeoutOrder.create(new TakeoutOrderLineItems(getOrderLineItems(request)))));
	}

	private List<OrderLineItem> getOrderLineItems(TakeoutOrderRequest request) {
		return request.getOrderLineItemRequests().stream()
			.map(orderLineItemRequest -> OrderLineItem.of(orderLineItemRequest.getQuantity(), orderLineItemRequest.getMenuId(), orderLineItemRequest.getPrice()))
			.collect(Collectors.toList());
	}

	@Transactional
	public TakeoutOrderResponse accept(final UUID orderId) {
		final TakeoutOrder order = findTakeoutOrder(orderId);
		return TakeoutOrderResponse.from(order.accept());
	}

	private TakeoutOrder findTakeoutOrder(UUID orderId) {
		return orderRepository.findById(orderId)
			.orElseThrow(NoSuchElementException::new);
	}

	@Transactional
	public TakeoutOrderResponse serve(final UUID orderId) {
		final TakeoutOrder order = findTakeoutOrder(orderId);
		return TakeoutOrderResponse.from(order.serve());
	}

	@Transactional
	public TakeoutOrderResponse complete(final UUID orderId) {
		final TakeoutOrder order = findTakeoutOrder(orderId);
		return TakeoutOrderResponse.from(order.complete());
	}

	@Transactional(readOnly = true)
	public List<TakeoutOrderResponse> findAll() {
		return orderRepository.findAll().stream()
			.map(TakeoutOrderResponse::from)
			.collect(Collectors.toList());
	}
}
