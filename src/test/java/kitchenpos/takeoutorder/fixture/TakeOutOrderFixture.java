package kitchenpos.takeoutorder.fixture;

import kitchenpos.order.common.domain.OrderType;
import kitchenpos.order.takeout.domain.TakeOutOrder;
import kitchenpos.order.takeout.domain.TakeOutOrderStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static kitchenpos.order.fixture.OrderLineItemFixture.orderLineItem;

public class TakeOutOrderFixture {
    public static TakeOutOrder takeOutOrder(final TakeOutOrderStatus status) {
        final TakeOutOrder takeOutOrder = new TakeOutOrder();
        takeOutOrder.setId(UUID.randomUUID());
        takeOutOrder.setType(OrderType.TAKEOUT);
        takeOutOrder.setStatus(status);
        takeOutOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        takeOutOrder.setOrderLineItems(Arrays.asList(orderLineItem()));
        return takeOutOrder;
    }

}
