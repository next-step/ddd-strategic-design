package kitchenpos.order.takeout.dto;

import java.math.BigDecimal;
import java.util.UUID;

import kitchenpos.order.takeout.domain.TakeoutOrderLineItem;

public class OrderLineItemResponse {
	private Long seq;

	private long quantity;

	private UUID menuId;

	private BigDecimal price;

	public static OrderLineItemResponse from(TakeoutOrderLineItem orderLineItem) {
		OrderLineItemResponse orderLineItemResponse = new OrderLineItemResponse();
		orderLineItemResponse.seq = orderLineItem.getSeq();
		orderLineItemResponse.quantity = orderLineItem.getQuantity();
		orderLineItemResponse.menuId = orderLineItem.getMenuId();
		orderLineItemResponse.price = orderLineItem.getPrice();

		return orderLineItemResponse;
	}
}
