package kitchenpos.orders.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import kitchenpos.orders.domain.entity.OrderLineItemDao;
import kitchenpos.orders.domain.entity.OrderLineItem;

public class InMemoryOrderLineItemDao implements OrderLineItemDao {

    private final Map<Long, OrderLineItem> entities = new HashMap<>();

    @Override
    public OrderLineItem save(final OrderLineItem entity) {
        entities.put(entity.getSeq(), entity);
        return entity;
    }

    @Override
    public Optional<OrderLineItem> findById(final Long id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<OrderLineItem> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public List<OrderLineItem> findAllByOrderId(final Long orderId) {
        return entities.values()
            .stream()
            .filter(entity -> Objects.equals(entity.getOrderId(), orderId))
            .collect(Collectors.toList())
            ;
    }
}
