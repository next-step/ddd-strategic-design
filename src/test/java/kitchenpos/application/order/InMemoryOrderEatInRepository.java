package kitchenpos.application.order;

import kitchenpos.order.orderEatIn.domain.OrderEatIn;
import kitchenpos.order.orderEatIn.domain.OrderEatInRepository;
import kitchenpos.order.orderEatIn.domain.OrderEatInStatus;
import kitchenpos.order.orderEatIn.domain.OrderTable;

import java.util.*;

public class InMemoryOrderEatInRepository implements OrderEatInRepository {
    private final Map<UUID, OrderEatIn> orders = new HashMap<>();

    @Override
    public OrderEatIn save(final OrderEatIn order) {
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Optional<OrderEatIn> findById(final UUID id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<OrderEatIn> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public boolean existsByOrderTableAndStatusNot(final OrderTable orderTable, final OrderEatInStatus status) {
        return orders.values()
            .stream()
            .anyMatch(order -> order.getOrderTable().equals(orderTable) && order.getStatus() != status);
    }
}
