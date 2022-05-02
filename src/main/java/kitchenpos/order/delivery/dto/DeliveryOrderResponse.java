package kitchenpos.order.delivery.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import kitchenpos.order.delivery.DeliveryOrderStatus;
import kitchenpos.order.delivery.domain.DeliveryOrder;

public class DeliveryOrderResponse {
	private UUID id;

	private DeliveryOrderStatus status;

	private LocalDateTime orderDateTime;

	private List<OrderLineItemResponse> orderLineItemResponses;

	private String deliveryAddress;

	public DeliveryOrderResponse() {
	}

	public static DeliveryOrderResponse from(DeliveryOrder order) {
		DeliveryOrderResponse deliveryOrderResponse = new DeliveryOrderResponse();
		deliveryOrderResponse.id = order.getId();
		deliveryOrderResponse.status = order.getStatus();
		deliveryOrderResponse.orderDateTime = order.getOrderDateTime();
		deliveryOrderResponse.orderLineItemResponses = getOrderLineItemResponses(order);
		deliveryOrderResponse.deliveryAddress = order.getDeliveryAddress();

		return deliveryOrderResponse;
	}

	private static List<OrderLineItemResponse> getOrderLineItemResponses(DeliveryOrder order) {
		return order.getDeliveryOrderLineItems().stream()
			.map(OrderLineItemResponse::from)
			.collect(Collectors.toList());
	}

	public String getId() {
		return id.toString();
	}
}
