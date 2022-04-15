package kitchenpos.menu.dto;

import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.product.dto.ProductResponse;

public class MenuProductResponse {
	private Long seq;

	private ProductResponse productResponse;

	private long quantity;

	public MenuProductResponse(Long seq, ProductResponse productResponse, long quantity) {
		this.seq = seq;
		this.productResponse = productResponse;
		this.quantity = quantity;
	}

	public static MenuProductResponse of(MenuProduct menuProduct) {
		return new MenuProductResponse(menuProduct.getSeq(), ProductResponse.of(menuProduct.getProduct()), menuProduct.getQuantity());
	}
}
