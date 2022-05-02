package kitchenpos.order.eatin.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderLineItemRequest {
	private UUID menuId;
	private long quantity;
	private BigDecimal price;

	public UUID getMenuId() {
		return this.menuId;
	}

	public long getQuantity() {
		return this.quantity;
	}

	public BigDecimal getPrice() {
		return this.price;
	}
}
