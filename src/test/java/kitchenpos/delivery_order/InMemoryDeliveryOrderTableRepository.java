package kitchenpos.delivery_order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.delivery_order.domain.DeliveryOrderTable;
import kitchenpos.delivery_order.domain.DeliveryOrderTableRepository;

public class InMemoryDeliveryOrderTableRepository implements DeliveryOrderTableRepository {
    private final Map<UUID, DeliveryOrderTable> orderTables = new HashMap<>();

    @Override
    public DeliveryOrderTable save(final DeliveryOrderTable deliveryOrderTable) {
        orderTables.put(deliveryOrderTable.getId(), deliveryOrderTable);
        return deliveryOrderTable;
    }

    @Override
    public Optional<DeliveryOrderTable> findById(final UUID id) {
        return Optional.ofNullable(orderTables.get(id));
    }

    @Override
    public List<DeliveryOrderTable> findAll() {
        return new ArrayList<>(orderTables.values());
    }
}
