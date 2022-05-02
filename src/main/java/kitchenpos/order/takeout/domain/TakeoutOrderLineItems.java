package kitchenpos.order.takeout.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Embeddable
public class TakeoutOrderLineItems {
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(
		name = "order_id",
		nullable = false,
		columnDefinition = "varbinary(16)",
		foreignKey = @ForeignKey(name = "fk_order_line_item_to_orders")
	)
	private List<TakeoutOrderLineItem> orderLineItems;

	public TakeoutOrderLineItems(List<TakeoutOrderLineItem> orderLineItems) {
		this.orderLineItems = validateOrderLineItem(orderLineItems);
	}

	private List<TakeoutOrderLineItem> validateOrderLineItem(List<TakeoutOrderLineItem> orderLineItems) {
		validateExistence(orderLineItems);
		validateQuantity(orderLineItems);

		return orderLineItems;
	}

	private void validateQuantity(List<TakeoutOrderLineItem> orderLineItems) {
		if (orderLineItems.stream().anyMatch(orderLineItem -> orderLineItem.getQuantity() < 0)) {
			throw new IllegalArgumentException();
		}
	}

	private void validateExistence(List<TakeoutOrderLineItem> orderLineItems) {
		if (Objects.isNull(orderLineItems) || orderLineItems.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	public List<TakeoutOrderLineItem> getValue() {
		return this.orderLineItems;
	}
}
