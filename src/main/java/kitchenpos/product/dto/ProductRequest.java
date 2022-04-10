package kitchenpos.product.dto;

import java.math.BigDecimal;

import org.springframework.lang.Nullable;

import kitchenpos.product.domain.Product;

public class ProductRequest {
	@Nullable
	private String name;

	private BigDecimal price;

	public ProductRequest() {
	}

	public ProductRequest(BigDecimal price) {
		this(null, price);
	}

	public ProductRequest(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public Product toProduct() {
		return new Product(name, price);
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}
}
