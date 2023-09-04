package kitchenpos.eatinorder.fixture;

import kitchenpos.order.common.domain.OrderType;
import kitchenpos.order.eatinorder.domain.EatInOrder;
import kitchenpos.order.eatinorder.domain.EatInOrderStatus;
import kitchenpos.order.eatinorder.domain.RestaurantTable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class EatInOrderFixture {
    public static final UUID INVALID_ID = new UUID(0L, 0L);

    public static EatInOrder order(final EatInOrderStatus status, final RestaurantTable orderTable) {
        final EatInOrder eatInOrder = new EatInOrder();
        eatInOrder.setId(UUID.randomUUID());
        eatInOrder.setType(OrderType.EAT_IN);
        eatInOrder.setStatus(status);
        eatInOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        eatInOrder.setOrderLineItems(Arrays.asList(orderLineItem()));
        eatInOrder.setRestaurantTable(restaurantTable());
        return eatInOrder;
    }


    public static RestaurantTable restaurantTable() {
        return restaurantTable(false, 0);
    }

    public static RestaurantTable restaurantTable(final boolean occupied, final int numberOfGuests) {
        final RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setId(UUID.randomUUID());
        restaurantTable.setName("1번");
        restaurantTable.setNumberOfGuests(numberOfGuests);
        restaurantTable.setOccupied(occupied);
        return restaurantTable;
    }

}
