package kitchenpos.order.takeoutorder.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import kitchenpos.order.common.domain.Order;

@Entity
@DiscriminatorValue("TAKEOUT")
public class TakeoutOrder extends Order {

    public TakeoutOrder() {
    }
}
