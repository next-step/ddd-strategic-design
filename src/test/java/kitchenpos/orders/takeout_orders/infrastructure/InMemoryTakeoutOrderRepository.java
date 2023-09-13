package kitchenpos.orders.takeout_orders.infrastructure;

import kitchenpos.orders.domain.Order;
import kitchenpos.orders.domain.OrderRepository;
import kitchenpos.orders.domain.OrderStatus;
import kitchenpos.orders.eatin_orders.domain.OrderTable;
import kitchenpos.orders.takeout_orders.domain.TakeoutOrder;
import kitchenpos.orders.takeout_orders.domain.TakeoutOrderRepository;

import java.util.*;

public class InMemoryTakeoutOrderRepository implements TakeoutOrderRepository {
    private final Map<UUID, TakeoutOrder> orders = new HashMap<>();

    @Override
    public TakeoutOrder save(final TakeoutOrder order) {
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Optional<TakeoutOrder> findById(final UUID id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<TakeoutOrder> findAll() {
        return new ArrayList<>(orders.values());
    }
}
