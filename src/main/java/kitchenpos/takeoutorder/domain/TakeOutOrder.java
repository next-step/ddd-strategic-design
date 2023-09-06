package kitchenpos.takeoutorder.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "takeoutorders")
@Entity
public class TakeOutOrder {
    @Column(name = "id", columnDefinition = "binary(16)")
    @Id
    private UUID id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TakeOutOrderStatus status;

    @Column(name = "order_date_time", nullable = false)
    private LocalDateTime orderDateTime;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "order_id",
            nullable = false,
            columnDefinition = "binary(16)",
            foreignKey = @ForeignKey(name = "fk_order_line_item_to_orders")
    )
    private List<TakeOutOrderLineItem> takeOutOrderLineItems;

    public TakeOutOrder() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public TakeOutOrderStatus getStatus() {
        return status;
    }

    public void setStatus(final TakeOutOrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(final LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<TakeOutOrderLineItem> getTakeOutOrderLineItems() {
        return takeOutOrderLineItems;
    }

    public void setTakeOutOrderLineItems(List<TakeOutOrderLineItem> takeOutOrderLineItems) {
        this.takeOutOrderLineItems = takeOutOrderLineItems;
    }
}
