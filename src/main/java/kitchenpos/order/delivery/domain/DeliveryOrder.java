package kitchenpos.order.delivery.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import kitchenpos.order.delivery.DeliveryOrderStatus;
import kitchenpos.order.delivery.infra.KitchenridersClient;

@Table(name = "delivery_orders")
@Entity
public class DeliveryOrder {
	@Column(name = "id", columnDefinition = "varbinary(16)")
	@Id
	private UUID id;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private DeliveryOrderStatus status;

	@Column(name = "order_date_time", nullable = false)
	private LocalDateTime orderDateTime;

	@Embedded
	private DeliveryOrderLineItems deliveryOrderLineItems;

	@Column(name = "delivery_address")
	private String deliveryAddress;

	public DeliveryOrder() {
	}

	public static DeliveryOrder create(DeliveryOrderLineItems deliveryOrderLineItems, String deliveryAddress) {
		DeliveryOrder deliveryOrder = new DeliveryOrder();
		deliveryOrder.deliveryOrderLineItems = deliveryOrderLineItems;
		deliveryOrder.deliveryAddress = validateDeliveryAddress(deliveryAddress);
		deliveryOrder.status = DeliveryOrderStatus.WAITING;
		deliveryOrder.orderDateTime = LocalDateTime.now();
		deliveryOrder.id = UUID.randomUUID();

		return deliveryOrder;
	}

	private static String validateDeliveryAddress(String deliveryAddress) {
		if (Objects.isNull(deliveryAddress) || deliveryAddress.isEmpty()) {
			throw new IllegalArgumentException();
		}

		return deliveryAddress;
	}

	public DeliveryOrder accept(KitchenridersClient kitchenridersClient) {
		if (status != DeliveryOrderStatus.WAITING) {
			throw new IllegalStateException();
		}

		kitchenridersClient.requestDelivery(id, getSum(), deliveryAddress);
		status = DeliveryOrderStatus.ACCEPTED;

		return this;
	}

	public DeliveryOrder serve() {
		if (status != DeliveryOrderStatus.ACCEPTED) {
			throw new IllegalStateException();
		}

		status = DeliveryOrderStatus.SERVED;

		return this;
	}

	public DeliveryOrder startDelivery() {
		if (status != DeliveryOrderStatus.SERVED) {
			throw new IllegalStateException();
		}

		status = DeliveryOrderStatus.DELIVERING;

		return this;
	}

	public DeliveryOrder completeDelivery() {
		if (status != DeliveryOrderStatus.DELIVERING) {
			throw new IllegalStateException();
		}

		status = DeliveryOrderStatus.DELIVERED;

		return this;
	}

	public DeliveryOrder complete() {
		if (status != DeliveryOrderStatus.DELIVERED) {
			throw new IllegalStateException();
		}

		status = DeliveryOrderStatus.COMPLETED;

		return this;
	}

	private BigDecimal getSum() {
		return deliveryOrderLineItems.getSum();
	}

	public UUID getId() {
		return this.id;
	}

	public DeliveryOrderStatus getStatus() {
		return this.status;
	}

	public LocalDateTime getOrderDateTime() {
		return this.orderDateTime;
	}

	public List<OrderLineItem> getDeliveryOrderLineItems() {
		return this.deliveryOrderLineItems.getValue();
	}

	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}
}
