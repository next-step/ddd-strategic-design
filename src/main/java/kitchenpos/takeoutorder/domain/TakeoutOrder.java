package kitchenpos.takeoutorder.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

@Table(name = "eat_in_orders")
@Entity
public class TakeoutOrder {
    @Column(name = "id", columnDefinition = "binary(16)")
    @Id
    private UUID id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TakeoutOrderType type;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TakeoutOrderStatus status;

    @Column(name = "order_date_time", nullable = false)
    private LocalDateTime orderDateTime;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
        name = "order_id",
        nullable = false,
        columnDefinition = "binary(16)",
        foreignKey = @ForeignKey(name = "fk_order_line_item_to_orders")
    )
    private List<TakeoutOrderLineItem> takeoutOrderLineItems;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Transient
    private UUID orderTableId;

    public TakeoutOrder() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public TakeoutOrderType getType() {
        return type;
    }

    public void setType(final TakeoutOrderType type) {
        this.type = type;
    }

    public TakeoutOrderStatus getStatus() {
        return status;
    }

    public void setStatus(final TakeoutOrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(final LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<TakeoutOrderLineItem> getOrderLineItems() {
        return takeoutOrderLineItems;
    }

    public void setOrderLineItems(final List<TakeoutOrderLineItem> takeoutOrderLineItems) {
        this.takeoutOrderLineItems = takeoutOrderLineItems;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(final String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public UUID getOrderTableId() {
        return orderTableId;
    }

    public void setOrderTableId(final UUID orderTableId) {
        this.orderTableId = orderTableId;
    }
}
