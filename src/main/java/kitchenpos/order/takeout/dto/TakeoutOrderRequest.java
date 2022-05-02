package kitchenpos.order.takeout.dto;

import java.util.List;

public class TakeoutOrderRequest {
	private List<OrderLineItemRequest> orderLineItemRequests;

	public List<OrderLineItemRequest> getOrderLineItemRequests() {
		return orderLineItemRequests;
	}
}
