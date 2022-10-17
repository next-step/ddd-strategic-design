package kitchenpos.takeout_order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.takeout_order.domain.TakeoutOrderTable;
import kitchenpos.takeout_order.domain.TakeoutOrderTableRepository;

public class InMemoryTakeoutOrderTableRepository implements TakeoutOrderTableRepository {
    private final Map<UUID, TakeoutOrderTable> orderTables = new HashMap<>();

    @Override
    public TakeoutOrderTable save(final TakeoutOrderTable takeoutOrderTable) {
        orderTables.put(takeoutOrderTable.getId(), takeoutOrderTable);
        return takeoutOrderTable;
    }

    @Override
    public Optional<TakeoutOrderTable> findById(final UUID id) {
        return Optional.ofNullable(orderTables.get(id));
    }

    @Override
    public List<TakeoutOrderTable> findAll() {
        return new ArrayList<>(orderTables.values());
    }
}
