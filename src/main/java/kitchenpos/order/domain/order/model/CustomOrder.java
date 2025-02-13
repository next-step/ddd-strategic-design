package kitchenpos.order.domain.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CustomOrder {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_type_id")
    private AbstractOrderType orderType;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void nextStatus() {
        OrderFlow orderFlow = orderType.getOrderFlow();
        this.status = orderFlow.performNextFlow(this.status);
    }
}

