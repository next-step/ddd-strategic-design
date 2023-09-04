package kitchenpos.deliveryorder.fixture;

import kitchenpos.order.common.domain.OrderType;
import kitchenpos.order.deliveryorder.domain.DeliveryOrder;
import kitchenpos.order.deliveryorder.domain.DeliveryOrderStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class DeliveryOrderFixture {

    public static DeliveryOrder deliveryOrder(final DeliveryOrderStatus status, final String deliveryAddress) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(UUID.randomUUID());
        deliveryOrder.setType(OrderType.DELIVERY);
        deliveryOrder.setStatus(status);
        deliveryOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        deliveryOrder.setOrderLineItems(Arrays.asList(orderLineItem()));
        deliveryOrder.setDeliveryAddress(deliveryAddress);
        return deliveryOrder;
    }

}
