package kitchenpos.orders.takeout.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "takeout_orders")
public class TakeoutOrder {

    @Column(name = "id", columnDefinition = "binary(16)")
    @Id
    private UUID id;

}
