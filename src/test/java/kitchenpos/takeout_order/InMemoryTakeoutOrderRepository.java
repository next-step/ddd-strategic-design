package kitchenpos.takeout_order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.takeout_order.domain.TakeoutOrder;
import kitchenpos.takeout_order.domain.TakeoutOrderRepository;
import kitchenpos.takeout_order.domain.TakeoutOrderStatus;
import kitchenpos.takeout_order.domain.TakeoutOrderTable;

public class InMemoryTakeoutOrderRepository implements TakeoutOrderRepository {
    private final Map<UUID, TakeoutOrder> orders = new HashMap<>();

    @Override
    public TakeoutOrder save(final TakeoutOrder takeoutOrder) {
        orders.put(takeoutOrder.getId(), takeoutOrder);
        return takeoutOrder;
    }

    @Override
    public Optional<TakeoutOrder> findById(final UUID id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<TakeoutOrder> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public boolean existsByOrderTableAndStatusNot(final TakeoutOrderTable takeoutOrderTable, final TakeoutOrderStatus status) {
        return orders.values()
            .stream()
            .anyMatch(order -> order.getOrderTable().equals(takeoutOrderTable) && order.getStatus() != status);
    }
}
