package kitchenpos.eatinorder.fixture;

import kitchenpos.order.common.domain.OrderType;
import kitchenpos.order.eatinorder.domain.EatInOrder;
import kitchenpos.order.eatinorder.domain.EatInOrderStatus;
import kitchenpos.order.eatinorder.domain.RestaurantTable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static kitchenpos.eatinorder.fixture.RestaurantTableFixture.restaurantTable;
import static kitchenpos.order.fixture.OrderLineItemFixture.orderLineItem;

public class EatInOrderFixture {

    public static EatInOrder eatInOrder(final EatInOrderStatus status, final RestaurantTable orderTable) {
        final EatInOrder eatInOrder = new EatInOrder();
        eatInOrder.setId(UUID.randomUUID());
        eatInOrder.setType(OrderType.EAT_IN);
        eatInOrder.setStatus(status);
        eatInOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        eatInOrder.setOrderLineItems(Arrays.asList(orderLineItem()));
        eatInOrder.setRestaurantTable(restaurantTable());
        return eatInOrder;
    }

}
