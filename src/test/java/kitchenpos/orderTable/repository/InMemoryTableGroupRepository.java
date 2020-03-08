package kitchenpos.orderTable.repository;

import kitchenpos.orderTable.domain.TableGroupRepository;
import kitchenpos.orderTable.domain.model.TableGroup;

import java.util.*;

public class InMemoryTableGroupRepository implements TableGroupRepository {
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
