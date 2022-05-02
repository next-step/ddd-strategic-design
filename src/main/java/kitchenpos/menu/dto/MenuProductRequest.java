package kitchenpos.menu.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class MenuProductRequest {
	private UUID id;

	private BigDecimal price;

	private long quantity;

	public MenuProductRequest() {
	}

	public MenuProductRequest(BigDecimal price, long quantity) {
		this.price = price;
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public long getQuantity() {
		return quantity;
	}
}
