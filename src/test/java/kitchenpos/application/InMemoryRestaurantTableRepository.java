package kitchenpos.application;

import kitchenpos.eatinorder.domain.RestaurantTable;
import kitchenpos.eatinorder.domain.RestaurantTableRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryRestaurantTableRepository implements RestaurantTableRepository {
    private final Map<UUID, RestaurantTable> orderTables = new HashMap<>();

    @Override
    public RestaurantTable save(final RestaurantTable restaurantTable) {
        orderTables.put(restaurantTable.getId(), restaurantTable);
        return restaurantTable;
    }

    @Override
    public Optional<RestaurantTable> findById(final UUID id) {
        return Optional.ofNullable(orderTables.get(id));
    }

    @Override
    public List<RestaurantTable> findAll() {
        return new ArrayList<>(orderTables.values());
    }
}
