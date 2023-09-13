package kitchenpos.delivery_orders;

import kitchenpos.delivery_orders.domain.DeliveryOrder;
import kitchenpos.delivery_orders.domain.DeliveryOrderLineItem;
import kitchenpos.delivery_orders.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import static kitchenpos.Fixtures.menu;

public class DeliveryOrderFixtures {

    public static DeliveryOrder order(final OrderStatus status, final String deliveryAddress) {
        final DeliveryOrder order = new DeliveryOrder();
        order.setId(UUID.randomUUID());
        order.setStatus(status);
        order.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        order.setOrderLineItems(Arrays.asList(orderLineItem()));
        order.setDeliveryAddress(deliveryAddress);
        return order;
    }

    public static DeliveryOrderLineItem orderLineItem() {
        final DeliveryOrderLineItem orderLineItem = new DeliveryOrderLineItem();
        orderLineItem.setSeq(new Random().nextLong());
        orderLineItem.setMenu(menu());
        return orderLineItem;
    }
}
