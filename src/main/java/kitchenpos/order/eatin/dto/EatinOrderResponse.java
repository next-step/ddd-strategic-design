package kitchenpos.order.eatin.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import kitchenpos.order.eatin.EatinOrderStatus;
import kitchenpos.order.eatin.domain.EatinOrder;

public class EatinOrderResponse {
	private UUID id;

	private EatinOrderStatus status;

	private LocalDateTime orderDateTime;

	private List<OrderLineItemResponse> orderLineItemResponses;

	private UUID orderTableId;

	public EatinOrderResponse() {
	}

	public static EatinOrderResponse from(EatinOrder order) {
		EatinOrderResponse eatinOrderResponse = new EatinOrderResponse();
		eatinOrderResponse.id = order.getId();
		eatinOrderResponse.status = order.getStatus();
		eatinOrderResponse.orderDateTime = order.getOrderDateTime();
		eatinOrderResponse.orderLineItemResponses = getOrderLineItemResponses(order);
		eatinOrderResponse.orderTableId = order.getOrderTable().getId();

		return eatinOrderResponse;
	}

	private static List<OrderLineItemResponse> getOrderLineItemResponses(EatinOrder order) {
		return order.getEatinOrderLineItems().stream()
			.map(OrderLineItemResponse::from)
			.collect(Collectors.toList());
	}

	public String getId() {
		return id.toString();
	}
}
