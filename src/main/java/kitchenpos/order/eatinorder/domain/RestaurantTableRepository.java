package kitchenpos.order.eatinorder.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantTableRepository {
    RestaurantTable save(RestaurantTable restaurantTable);

    Optional<RestaurantTable> findById(UUID id);

    List<RestaurantTable> findAll();
}

