package kitchenpos.order.takeoutorder.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import kitchenpos.order.common.domain.Order;
import kitchenpos.order.common.domain.OrderType;

@Entity
@DiscriminatorValue("TAKEOUT")
public class TakeoutOrder extends Order {

    public TakeoutOrder() {
    }

    @Override
    public OrderType getType() {
        return OrderType.TAKEOUT;
    }
}
