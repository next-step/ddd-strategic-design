package kitchenpos.menu.domain;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

import kitchenpos.product.domain.Product;

@Table(name = "menu_product")
@Entity
public class MenuProduct {
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long seq;

    // @ManyToOne(optional = false)
    // @JoinColumn(
    //     name = "product_id",
    //     columnDefinition = "varbinary(16)",
    //     foreignKey = @ForeignKey(name = "fk_menu_product_to_product")
    // )
    // private Product product;

	@Column
	private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private long quantity;

    @Transient
    private UUID productId;

    public MenuProduct() {
    }

    public MenuProduct(BigDecimal price, long quantity) {
    	this.price = price;
    	this.quantity = validateQuantity(quantity);
	}

	private long validateQuantity(long quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException();
		}

    	return quantity;
	}

    public Long getSeq() {
        return seq;
    }

    public long getQuantity() {
        return quantity;
    }

    public BigDecimal getCost() {
    	return price
			.multiply(BigDecimal.valueOf(quantity));
	}

	public BigDecimal getPrice() {
    	return price;
	}
}
