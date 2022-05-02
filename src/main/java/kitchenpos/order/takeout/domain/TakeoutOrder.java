package kitchenpos.order.takeout.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import kitchenpos.order.takeout.TakeoutOrderStatus;

@Table(name = "takeout_orders")
@Entity
public class TakeoutOrder {
	@Column(name = "id", columnDefinition = "varbinary(16)")
	@Id
	private UUID id;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private TakeoutOrderStatus status;

	@Column(name = "order_date_time", nullable = false)
	private LocalDateTime orderDateTime;

	@Embedded
	private TakeoutOrderLineItems orderLineItems;

	public TakeoutOrder() {
	}

	public static TakeoutOrder create(TakeoutOrderLineItems orderLineItems) {
		TakeoutOrder takeoutOrder = new TakeoutOrder();
		takeoutOrder.orderLineItems = orderLineItems;
		takeoutOrder.status = TakeoutOrderStatus.WAITING;
		takeoutOrder.orderDateTime = LocalDateTime.now();
		takeoutOrder.id = UUID.randomUUID();

		return takeoutOrder;
	}

	public TakeoutOrder accept() {
		if (status != TakeoutOrderStatus.WAITING) {
			throw new IllegalStateException();
		}

		status = TakeoutOrderStatus.ACCEPTED;

		return this;
	}

	public TakeoutOrder serve() {
		if (status != TakeoutOrderStatus.ACCEPTED) {
			throw new IllegalStateException();
		}

		status = TakeoutOrderStatus.SERVED;

		return this;
	}

	public TakeoutOrder complete() {
		if (status != TakeoutOrderStatus.SERVED) {
			throw new IllegalStateException();
		}

		status = TakeoutOrderStatus.COMPLETED;

		return this;
	}

	public UUID getId() {
		return this.id;
	}

	public TakeoutOrderStatus getStatus() {
		return this.status;
	}

	public LocalDateTime getOrderDateTime() {
		return this.orderDateTime;
	}

	public List<OrderLineItem> getTakeoutOrderLineItems() {
		return orderLineItems.getValue();
	}
}
