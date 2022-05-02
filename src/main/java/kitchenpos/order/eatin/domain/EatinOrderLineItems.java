package kitchenpos.order.eatin.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Embeddable
public class EatinOrderLineItems {
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(
		name = "order_id",
		nullable = false,
		columnDefinition = "varbinary(16)",
		foreignKey = @ForeignKey(name = "fk_order_line_item_to_orders")
	)
	private List<EatinOrderLineItem> orderLineItems;

	public EatinOrderLineItems(List<EatinOrderLineItem> orderLineItems) {
		this.orderLineItems = validateOrderLineItem(orderLineItems);
	}

	private List<EatinOrderLineItem> validateOrderLineItem(List<EatinOrderLineItem> orderLineItems) {
		validateExistence(orderLineItems);

		return orderLineItems;
	}

	private void validateExistence(List<EatinOrderLineItem> orderLineItems) {
		if (Objects.isNull(orderLineItems) || orderLineItems.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	public List<EatinOrderLineItem> getValue() {
		return orderLineItems;
	}
}
