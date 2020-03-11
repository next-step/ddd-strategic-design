package kitchenpos.order.doamin;

import kitchenpos.order.doamin.OrderLineItemDao;
import kitchenpos.order.doamin.OrderLineItem;

import java.util.*;
import java.util.stream.Collectors;

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
