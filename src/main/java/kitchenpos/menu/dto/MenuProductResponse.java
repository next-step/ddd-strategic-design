package kitchenpos.menu.dto;

import java.math.BigDecimal;

import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.product.dto.ProductResponse;

public class MenuProductResponse {
	private Long seq;

	private BigDecimal price;

	private long quantity;

	public MenuProductResponse(Long seq, BigDecimal price, long quantity) {
		this.seq = seq;
		this.price = price;
		this.quantity = quantity;
	}

	public static MenuProductResponse of(MenuProduct menuProduct) {
		return new MenuProductResponse(menuProduct.getSeq(), menuProduct.getPrice(), menuProduct.getQuantity());
	}
}
