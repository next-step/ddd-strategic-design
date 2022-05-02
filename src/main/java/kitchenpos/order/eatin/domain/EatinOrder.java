package kitchenpos.order.eatin.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kitchenpos.order.eatin.EatinOrderStatus;

@Table(name = "eatin_orders")
@Entity
public class EatinOrder {
	@Column(name = "id", columnDefinition = "varbinary(16)")
	@Id
	private UUID id;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private EatinOrderStatus status;

	@Column(name = "order_date_time", nullable = false)
	private LocalDateTime orderDateTime;

	@Embedded
	private EatinOrderLineItems eatinOrderLineItems;

	@ManyToOne
	@JoinColumn(
		name = "order_table_id",
		columnDefinition = "varbinary(16)",
		foreignKey = @ForeignKey(name = "fk_orders_to_order_table")
	)
	private OrderTable orderTable;

	public EatinOrder() {
	}

	public static EatinOrder create(EatinOrderLineItems eatinOrderLineItems, OrderTable orderTable) {
		EatinOrder eatinOrder = new EatinOrder();
		eatinOrder.eatinOrderLineItems = eatinOrderLineItems;
		eatinOrder.status = EatinOrderStatus.WAITING;
		eatinOrder.orderDateTime = LocalDateTime.now();
		eatinOrder.id = UUID.randomUUID();

		if (orderTable.isEmpty()) {
			throw new IllegalStateException();
		}

		eatinOrder.orderTable = orderTable;

		return eatinOrder;
	}

	public EatinOrder accept() {
		if (status != EatinOrderStatus.WAITING) {
			throw new IllegalStateException();
		}

		status = EatinOrderStatus.ACCEPTED;

		return this;
	}

	public EatinOrder serve() {
		if (status != EatinOrderStatus.ACCEPTED) {
			throw new IllegalStateException();
		}

		status = EatinOrderStatus.SERVED;

		return this;
	}

	public EatinOrder complete() {
		if (status != EatinOrderStatus.SERVED) {
			throw new IllegalStateException();
		}

		status = EatinOrderStatus.COMPLETED;

		return this;
	}

	public UUID getId() {
		return this.id;
	}

	public EatinOrderStatus getStatus() {
		return this.status;
	}

	public LocalDateTime getOrderDateTime() {
		return this.orderDateTime;
	}

	public List<OrderLineItem> getEatinOrderLineItems() {
		return this.eatinOrderLineItems.getValue();
	}

	public OrderTable getOrderTable() {
		return this.orderTable;
	}
}
