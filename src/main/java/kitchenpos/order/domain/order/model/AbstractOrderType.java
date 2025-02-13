package kitchenpos.order.domain.order.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "order_type")
public class AbstractOrderType implements CustomOrderType {

    @Id
    @GeneratedValue
    private Long id;

    @Override
    public OrderFlow getOrderFlow() {
        return null;
    }
}
