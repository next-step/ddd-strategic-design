package kitchenpos.order.delivery.dto;

import java.util.List;

public class DeliveryOrderRequest {
	private List<OrderLineItemRequest> orderLineItemRequests;
	private String deliveryAddress;

	public List<OrderLineItemRequest> getOrderLineItemRequests() {
		return orderLineItemRequests;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}
}
