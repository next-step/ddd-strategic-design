package kitchenpos.order.eatin.dto;

import java.util.List;
import java.util.UUID;

public class EatinOrderRequest {
	private List<OrderLineItemRequest> orderLineItemRequests;
	private UUID orderTableId;

	public List<OrderLineItemRequest> getOrderLineItemRequests() {
		return orderLineItemRequests;
	}

	public UUID getOrderTableId() {
		return orderTableId;
	}
}
