package kitchenpos.order.delivery.domain.service;

import kitchenpos.order.delivery.domain.model.DeliveryOrder;
import kitchenpos.order.delivery.domain.port.RiderPort;
import kitchenpos.order.order.model.OrderStatus;

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
            String deliveryAddress = deliveryOrder.getDeliveryAddress();
            riderPort.requestRider(deliveryOrder, deliveryAddress);
        }
    }
}
