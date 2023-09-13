package kitchenpos.takeout_orders;

import kitchenpos.takeout_orders.domain.OrderStatus;
import kitchenpos.takeout_orders.domain.TakeoutOrder;
import kitchenpos.takeout_orders.domain.TakeoutOrderLineItem;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import static kitchenpos.Fixtures.menu;

public class TakeoutOrderFixtures {
    public static TakeoutOrder order(final OrderStatus status) {
        final TakeoutOrder order = new TakeoutOrder();
        order.setId(UUID.randomUUID());
        order.setStatus(status);
        order.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        order.setOrderLineItems(Arrays.asList(orderLineItem()));
        return order;
    }

    public static TakeoutOrderLineItem orderLineItem() {
        final TakeoutOrderLineItem orderLineItem = new TakeoutOrderLineItem();
        orderLineItem.setSeq(new Random().nextLong());
        orderLineItem.setMenu(menu());
        return orderLineItem;
    }
}
