package kitchenpos.order.domain.delivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import kitchenpos.order.domain.order.model.OrderStatus;

@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated
    private DeliveryOrderFlow orderFlow;

    @Enumerated
    private OrderStatus currentOrderStatus;

    public boolean validateOrderFlowAndFindNextStep(OrderStatus orderStatus) {
        if (orderFlow.validateOrderStatus(orderStatus)) {
            throw new IllegalArgumentException();
        }
        return orderFlow.isRiderNeccessary(orderStatus);
    }

    public UUID getId() {
        return id;
    }
}
