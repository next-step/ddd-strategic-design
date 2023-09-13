package kitchenpos.eatin_orders.infrastructure;

import kitchenpos.eatin_orders.domain.EatInOrder;
import kitchenpos.eatin_orders.domain.EatInOrderRepository;
import kitchenpos.eatin_orders.domain.OrderStatus;
import kitchenpos.eatin_orders.domain.OrderTable;

import java.util.*;

public class InMemoryEatInOrderRepository implements EatInOrderRepository {
    private final Map<UUID, EatInOrder> eatInOrders = new HashMap<>();

    @Override
    public EatInOrder save(final EatInOrder order) {
        eatInOrders.put(order.getId(), order);
        return order;
    }

    @Override
    public Optional<EatInOrder> findById(final UUID id) {
        return Optional.ofNullable(eatInOrders.get(id));
    }

    @Override
    public List<EatInOrder> findAll() {
        return new ArrayList<>(eatInOrders.values());
    }

    @Override
    public boolean existsByOrderTableAndStatusNot(final OrderTable orderTable, final OrderStatus status) {
        return eatInOrders.values()
                .stream()
                .anyMatch(order -> order.getOrderTable().equals(orderTable) && order.getStatus() != status);
    }
}
