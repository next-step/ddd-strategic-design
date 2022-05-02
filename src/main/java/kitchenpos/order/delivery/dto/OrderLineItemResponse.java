package kitchenpos.order.delivery.dto;

import java.math.BigDecimal;
import java.util.UUID;

import kitchenpos.order.delivery.domain.DeliveryOrderLineItem;

public class OrderLineItemResponse {
	private Long seq;

	private long quantity;

	private UUID menuId;

	private BigDecimal price;

	public static OrderLineItemResponse from(DeliveryOrderLineItem orderLineItem) {
		OrderLineItemResponse orderLineItemResponse = new OrderLineItemResponse();
		orderLineItemResponse.seq = orderLineItem.getSeq();
		orderLineItemResponse.quantity = orderLineItem.getQuantity();
		orderLineItemResponse.menuId = orderLineItem.getMenuId();
		orderLineItemResponse.price = orderLineItem.getPrice();

		return orderLineItemResponse;
	}
}
