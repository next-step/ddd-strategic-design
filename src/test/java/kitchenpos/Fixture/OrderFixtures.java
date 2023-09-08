package kitchenpos.Fixture;

import kitchenpos.deliveryorder.domain.DeliveryOrder;
import kitchenpos.deliveryorder.domain.DeliveryOrderStatus;
import kitchenpos.eatinorder.domain.EatInOrder;
import kitchenpos.eatinorder.domain.EatInOrderStatus;
import kitchenpos.eatinorder.domain.OrderTable;
import kitchenpos.takeoutorder.domain.TakeOutOrder;
import kitchenpos.takeoutorder.domain.TakeOutOrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static kitchenpos.Fixtures.orderLineItem;


public class OrderFixtures {

    public static TakeOutOrder takeOutOrder(final TakeOutOrderStatus status) {
        final TakeOutOrder takeOutOrder = new TakeOutOrder();
        takeOutOrder.setId(UUID.randomUUID());
        takeOutOrder.setStatus(status);
        takeOutOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        takeOutOrder.setOrderLineItems(List.of(orderLineItem()));
        return takeOutOrder;
    }

    public static DeliveryOrder deliveryOrder(final DeliveryOrderStatus status) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(UUID.randomUUID());
        deliveryOrder.setStatus(status);
        deliveryOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        deliveryOrder.setOrderLineItems(List.of(orderLineItem()));
        return deliveryOrder;
    }

    public static DeliveryOrder deliveryOrder(final DeliveryOrderStatus status, final String deliveryAddress) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(UUID.randomUUID());
        deliveryOrder.setStatus(status);
        deliveryOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        deliveryOrder.setOrderLineItems(List.of(orderLineItem()));
        deliveryOrder.setDeliveryAddress(deliveryAddress);
        return deliveryOrder;
    }

    public static EatInOrder eatInOrder(final EatInOrderStatus status) {
        final EatInOrder order = new EatInOrder();
        order.setId(UUID.randomUUID());
        order.setStatus(status);
        order.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        order.setOrderLineItems(List.of(orderLineItem()));
        return order;
    }

    public static EatInOrder eatInOrder(final EatInOrderStatus status, final OrderTable orderTable) {
        final EatInOrder eatInOrder = new EatInOrder();
        eatInOrder.setId(UUID.randomUUID());
        eatInOrder.setStatus(status);
        eatInOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        eatInOrder.setOrderLineItems(List.of(orderLineItem()));
        eatInOrder.setOrderTable(orderTable);
        return eatInOrder;
    }

}
