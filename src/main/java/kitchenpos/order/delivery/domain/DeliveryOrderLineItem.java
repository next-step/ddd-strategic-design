package kitchenpos.order.delivery.domain;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "delivery_order_line_item")
@Entity
public class DeliveryOrderLineItem {
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long seq;

    @Column(name = "quantity", nullable = false)
    private long quantity;

	@Column
    private UUID menuId;

    @Column
    private BigDecimal price;

    public DeliveryOrderLineItem() {
    }

    public static DeliveryOrderLineItem of(long quantity, UUID menuId, BigDecimal price) {
    	DeliveryOrderLineItem orderLineItem = new DeliveryOrderLineItem();
		orderLineItem.quantity = quantity;
		orderLineItem.menuId = menuId;
		orderLineItem.price = price;

		return orderLineItem;
	}

    public Long getSeq() {
        return seq;
    }

    public void setSeq(final Long seq) {
        this.seq = seq;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(final long quantity) {
        this.quantity = quantity;
    }

    public UUID getMenuId() {
        return menuId;
    }

    public void setMenuId(final UUID menuId) {
        this.menuId = menuId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
}
