package kitchenpos.application;

import kitchenpos.orderDelivery.domain.OrderDelivery;
import kitchenpos.orderDelivery.domain.OrderDeliveryRepository;

import java.util.*;

public class InMemoryOrderDeliveryRepository implements OrderDeliveryRepository {
    private final Map<UUID, OrderDelivery> orders = new HashMap<>();

    @Override
    public OrderDelivery save(final OrderDelivery order) {
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Optional<OrderDelivery> findById(final UUID id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<OrderDelivery> findAll() {
        return new ArrayList<>(orders.values());
    }

}
