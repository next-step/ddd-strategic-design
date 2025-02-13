package kitchenpos.order.domain.delivery.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kitchenpos.order.domain.delivery.flow.DeliveryOrderFlow;
import kitchenpos.order.domain.order.model.CustomOrderType;
import kitchenpos.order.domain.order.model.OrderFlow;

@Entity
@DiscriminatorValue("DELIVERY")
public class DeliveryOrder implements CustomOrderType {
    @Id
    @GeneratedValue
    private Long id;

    @Override
    public OrderFlow getOrderFlow() {
        return new DeliveryOrderFlow();
    }
}
