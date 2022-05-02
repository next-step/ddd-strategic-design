package kitchenpos.order.takeout.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import kitchenpos.order.takeout.TakeoutOrderStatus;
import kitchenpos.order.takeout.domain.TakeoutOrder;

public class TakeoutOrderResponse {
	private UUID id;

	private TakeoutOrderStatus status;

	private LocalDateTime orderDateTime;

	private List<OrderLineItemResponse> orderLineItemResponses;

	public TakeoutOrderResponse() {
	}

	public static TakeoutOrderResponse from(TakeoutOrder order) {
		TakeoutOrderResponse deliveryOrderResponse = new TakeoutOrderResponse();
		deliveryOrderResponse.id = order.getId();
		deliveryOrderResponse.status = order.getStatus();
		deliveryOrderResponse.orderDateTime = order.getOrderDateTime();
		deliveryOrderResponse.orderLineItemResponses = getOrderLineItemResponses(order);

		return deliveryOrderResponse;
	}

	private static List<OrderLineItemResponse> getOrderLineItemResponses(TakeoutOrder order) {
		return order.getTakeoutOrderLineItems().stream()
			.map(OrderLineItemResponse::from)
			.collect(Collectors.toList());
	}

	public String getId() {
		return null;
	}
}
