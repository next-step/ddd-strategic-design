package kitchenpos.application;


import kitchenpos.orderTakeOut.domain.OrderTakeOut;
import kitchenpos.orderTakeOut.domain.OrderTakeOutRepository;

import java.util.*;

public class InMemoryOrderTakeOutRepository implements OrderTakeOutRepository {
    private final Map<UUID, OrderTakeOut> orders = new HashMap<>();

    @Override
    public OrderTakeOut save(final OrderTakeOut order) {
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Optional<OrderTakeOut> findById(final UUID id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<OrderTakeOut> findAll() {
        return new ArrayList<>(orders.values());
    }


}
