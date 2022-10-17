package kitchenpos.eat_in_order;

import kitchenpos.eat_in_order.domain.EatInOrderTable;
import kitchenpos.eat_in_order.domain.EatInOrderTableRepository;

import java.util.*;

public class InMemoryEatInOrderTableRepository implements EatInOrderTableRepository {
    private final Map<UUID, EatInOrderTable> orderTables = new HashMap<>();

    @Override
    public EatInOrderTable save(final EatInOrderTable eatInOrderTable) {
        orderTables.put(eatInOrderTable.getId(), eatInOrderTable);
        return eatInOrderTable;
    }

    @Override
    public Optional<EatInOrderTable> findById(final UUID id) {
        return Optional.ofNullable(orderTables.get(id));
    }

    @Override
    public List<EatInOrderTable> findAll() {
        return new ArrayList<>(orderTables.values());
    }
}