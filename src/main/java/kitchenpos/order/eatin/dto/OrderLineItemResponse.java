package kitchenpos.order.eatin.dto;

import java.math.BigDecimal;
import java.util.UUID;

import kitchenpos.order.eatin.domain.EatinOrderLineItem;

public class OrderLineItemResponse {
	private Long seq;

	private long quantity;

	private UUID menuId;

	private BigDecimal price;

	public static OrderLineItemResponse from(EatinOrderLineItem orderLineItem) {
		OrderLineItemResponse orderLineItemResponse = new OrderLineItemResponse();
		orderLineItemResponse.seq = orderLineItem.getSeq();
		orderLineItemResponse.quantity = orderLineItem.getQuantity();
		orderLineItemResponse.menuId = orderLineItem.getMenuId();
		orderLineItemResponse.price = orderLineItem.getPrice();

		return orderLineItemResponse;
	}
}
