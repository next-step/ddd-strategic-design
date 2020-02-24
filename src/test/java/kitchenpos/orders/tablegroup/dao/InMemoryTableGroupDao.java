package kitchenpos.orders.tablegroup.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import kitchenpos.orders.tablegroup.domain.TableGroup;

public class InMemoryTableGroupDao implements TableGroupDao {

    private final Map<Long, TableGroup> entities = new HashMap<>();

    @Override
    public TableGroup save(final TableGroup entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<TableGroup> findById(final Long id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<TableGroup> findAll() {
        return new ArrayList<>(entities.values());
    }
}
