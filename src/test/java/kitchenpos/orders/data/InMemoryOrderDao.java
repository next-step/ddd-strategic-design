package kitchenpos.orders.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import kitchenpos.orders.domain.entity.OrderDao;
import kitchenpos.orders.domain.entity.Order;

public class InMemoryOrderDao implements OrderDao {

    private final Map<Long, Order> entities = new HashMap<>();

    @Override
    public Order save(final Order entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Order> findById(final Long id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public boolean existsByOrderTableIdAndOrderStatusIn(final Long orderTableId,
        final List<String> orderStatuses) {
        return entities.values()
            .stream()
            .filter(entity -> Objects.equals(entity.getOrderTableId(), orderTableId))
            .anyMatch(entity -> orderStatuses.contains(entity.getOrderStatus()))
            ;
    }

    @Override
    public boolean existsByOrderTableIdInAndOrderStatusIn(final List<Long> orderTableIds,
        final List<String> orderStatuses) {
        return entities.values()
            .stream()
            .filter(entity -> orderTableIds.contains(entity.getOrderTableId()))
            .anyMatch(entity -> orderStatuses.contains(entity.getOrderStatus()))
            ;
    }
}
