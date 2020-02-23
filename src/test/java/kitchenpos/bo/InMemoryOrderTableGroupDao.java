package kitchenpos.bo;

import kitchenpos.ordertablegroups.domain.OrderTableGroupDao;
import kitchenpos.ordertablegroups.domain.OrderTableGroup;

import java.util.*;

public class InMemoryOrderTableGroupDao implements OrderTableGroupDao {
    private final Map<Long, OrderTableGroup> entities = new HashMap<>();

    @Override
    public OrderTableGroup save(final OrderTableGroup entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<OrderTableGroup> findById(final Long id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<OrderTableGroup> findAll() {
        return new ArrayList<>(entities.values());
    }
}
