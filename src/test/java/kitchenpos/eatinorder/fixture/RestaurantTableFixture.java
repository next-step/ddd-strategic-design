package kitchenpos.eatinorder.fixture;

import kitchenpos.order.eatinorder.domain.RestaurantTable;

import java.util.UUID;

public class RestaurantTableFixture {

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
