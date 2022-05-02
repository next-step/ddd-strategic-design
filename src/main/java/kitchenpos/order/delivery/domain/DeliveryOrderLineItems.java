package kitchenpos.order.delivery.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Embeddable
public class DeliveryOrderLineItems {
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(
		name = "order_id",
		nullable = false,
		columnDefinition = "varbinary(16)",
		foreignKey = @ForeignKey(name = "fk_order_line_item_to_orders")
	)
	private List<DeliveryOrderLineItem> orderLineItems;

	public DeliveryOrderLineItems(List<DeliveryOrderLineItem> orderLineItems) {
		this.orderLineItems = validateOrderLineItem(orderLineItems);
	}

	private List<DeliveryOrderLineItem> validateOrderLineItem(List<DeliveryOrderLineItem> orderLineItems) {
		validateExistence(orderLineItems);
		validateQuantity(orderLineItems);

		return orderLineItems;
	}

	private void validateQuantity(List<DeliveryOrderLineItem> orderLineItems) {
		if (orderLineItems.stream().anyMatch(orderLineItem -> orderLineItem.getQuantity() < 0)) {
			throw new IllegalArgumentException();
		}
	}

	private void validateExistence(List<DeliveryOrderLineItem> orderLineItems) {
		if (Objects.isNull(orderLineItems) || orderLineItems.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	public BigDecimal getSum() {
		BigDecimal sum = BigDecimal.ZERO;

		for (final DeliveryOrderLineItem orderLineItem : orderLineItems) {
			sum = orderLineItem	.getPrice()
				.multiply(BigDecimal.valueOf(orderLineItem.getQuantity()));
		}
		return sum;
	}

	public List<DeliveryOrderLineItem> getValue() {
		return this.orderLineItems;
	}
}
