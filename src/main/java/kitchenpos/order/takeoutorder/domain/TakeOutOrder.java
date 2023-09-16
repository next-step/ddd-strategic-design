package kitchenpos.order.takeoutorder.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import kitchenpos.order.common.domain.OrderLineItem;
import kitchenpos.order.common.domain.OrderStatus;

@Table(name = "take_out_orders")
@Entity
public class TakeOutOrder {

    @Column(name = "id", columnDefinition = "binary(16)")
    @Id
    private UUID id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "order_date_time", nullable = false)
    private LocalDateTime orderDateTime;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
        name = "take_out_order_id",
        nullable = false,
        columnDefinition = "binary(16)",
        foreignKey = @ForeignKey(name = "fk_takeout_order_line_item_to_orders")
    )
    private List<OrderLineItem> orderLineItems;

    @Transient
    private UUID orderTableId;

    public TakeOutOrder() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(final OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(final LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(final List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public UUID getOrderTableId() {
        return orderTableId;
    }

    public void setOrderTableId(final UUID orderTableId) {
        this.orderTableId = orderTableId;
    }
}
