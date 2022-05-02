package kitchenpos.order.eatin.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import kitchenpos.order.eatin.EatinOrderStatus;
import kitchenpos.order.eatin.domain.EatinOrder;
import kitchenpos.order.eatin.domain.EatinOrderLineItems;
import kitchenpos.order.eatin.domain.EatinOrderRepository;
import kitchenpos.order.eatin.domain.EatinOrderLineItem;
import kitchenpos.order.eatin.domain.OrderTable;
import kitchenpos.order.eatin.domain.OrderTableRepository;
import kitchenpos.order.eatin.dto.EatinOrderResponse;
import kitchenpos.order.eatin.dto.EatinOrderRequest;

public class EatinOrderService {
	private final EatinOrderRepository orderRepository;
	private final OrderTableRepository orderTableRepository;

	public EatinOrderService(
		final EatinOrderRepository orderRepository,
		final OrderTableRepository orderTableRepository
	) {
		this.orderRepository = orderRepository;
		this.orderTableRepository = orderTableRepository;
	}

	@Transactional
	public EatinOrderResponse create(final EatinOrderRequest request) {
		final OrderTable orderTable = orderTableRepository.findById(request.getOrderTableId())
			.orElseThrow(NoSuchElementException::new);

		return EatinOrderResponse.from(orderRepository.save(EatinOrder.create(new EatinOrderLineItems(getOrderLineItems(request)), orderTable)));
	}

	private List<EatinOrderLineItem> getOrderLineItems(EatinOrderRequest request) {
		return request.getOrderLineItemRequests().stream()
			.map(orderLineItemRequest -> EatinOrderLineItem.of(orderLineItemRequest.getQuantity(), orderLineItemRequest.getMenuId(), orderLineItemRequest.getPrice()))
			.collect(Collectors.toList());
	}


	@Transactional
	public EatinOrderResponse accept(final UUID orderId) {
		final EatinOrder order = findEatinOrder(orderId);

		return EatinOrderResponse.from(order.accept());
	}

	private EatinOrder findEatinOrder(UUID orderId) {
		return orderRepository.findById(orderId)
			.orElseThrow(NoSuchElementException::new);
	}

	@Transactional
	public EatinOrderResponse serve(final UUID orderId) {
		final EatinOrder order = findEatinOrder(orderId);
		return EatinOrderResponse.from(order.serve());
	}

	@Transactional
	public EatinOrderResponse complete(final UUID orderId) {
		final EatinOrder order = findEatinOrder(orderId);
		final OrderTable orderTable = order.getOrderTable();
		if (!orderRepository.existsByOrderTableAndStatusNot(orderTable, EatinOrderStatus.COMPLETED)) {
			orderTable.clear(); // TODO : 여기서 해당 기능을 수행하지 말고 배치 등으로 finally consistence 만 충족하는 것도 좋지 않을지...?
		}

		return EatinOrderResponse.from(order.complete());
	}

	@Transactional(readOnly = true)
	public List<EatinOrderResponse> findAll() {
		return orderRepository.findAll().stream()
			.map(EatinOrderResponse::from)
			.collect(Collectors.toList());
	}
}
