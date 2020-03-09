package kitchenpos.bo;

import kitchenpos.order.dao.TableGroupDao;
import kitchenpos.order.model.TableGroup;

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
