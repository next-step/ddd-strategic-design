package kitchenpos.eatinorder.domain;

import kitchenpos.order.eatinorder.domain.RestaurantTable;
import kitchenpos.order.eatinorder.domain.RestaurantTableRepository;

import java.util.*;

public class InMemoryRestaurantTableRepository implements RestaurantTableRepository {
    private final Map<UUID, RestaurantTable> restaurantTables = new HashMap<>();

    @Override
    public RestaurantTable save(final RestaurantTable restaurantTable) {
        restaurantTables.put(restaurantTable.getId(), restaurantTable);
        return restaurantTable;
    }

    @Override
    public Optional<RestaurantTable> findById(final UUID id) {
        return Optional.ofNullable(restaurantTables.get(id));
    }

    @Override
    public List<RestaurantTable> findAll() {
        return new ArrayList<>(restaurantTables.values());
    }
}
