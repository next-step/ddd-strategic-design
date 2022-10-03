package kitchenpos.orders.delivery.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "delivery_orders")
public class DeliveryOrder {

    @Column(name = "id", columnDefinition = "binary(16)")
    @Id
    private UUID id;

}
