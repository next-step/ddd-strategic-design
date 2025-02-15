package kitchenpos.order.domain.delivery.service;

import kitchenpos.order.domain.delivery.model.DeliveryOrder;
import kitchenpos.order.domain.delivery.port.RiderPort;
import kitchenpos.order.domain.order.model.OrderStatus;

public class DeliveryOrderDomainService {

    private final RiderPort riderPort;
    private final DeliveryOrder deliveryOrder;

    public DeliveryOrderDomainService(RiderPort riderPort, DeliveryOrder deliveryOrder) {
        this.riderPort = riderPort;
        this.deliveryOrder = deliveryOrder;
    }

    public void doRide(OrderStatus orderStatus) {
        boolean existsNextStep = deliveryOrder.validateOrderFlowAndFindNextStep(orderStatus);
        if (existsNextStep) {
            riderPort.requestRider(deliveryOrder);
        }
    }
}
