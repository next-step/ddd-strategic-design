package kitchenpos.ordertable.infra;

import kitchenpos.ordertable.infra.TableGroupDao;
import kitchenpos.ordertable.domain.TableGroup;

import java.util.*;

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
