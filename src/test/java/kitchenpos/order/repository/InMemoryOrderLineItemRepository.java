package kitchenpos.order.repository;

import kitchenpos.order.domain.model.OrderLineItem;
import kitchenpos.order.domain.OrderLineItemRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryOrderLineItemRepository implements OrderLineItemRepository {
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
