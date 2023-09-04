package kitchenpos.eatinorder.domain;

import kitchenpos.domain.Order;
import kitchenpos.domain.OrderRepository;
import kitchenpos.domain.OrderStatus;
import kitchenpos.order.eatinorder.domain.EatInOrder;
import kitchenpos.order.eatinorder.domain.EatInOrderRepository;
import kitchenpos.order.eatinorder.domain.EatInOrderStatus;
import kitchenpos.order.eatinorder.domain.RestaurantTable;

import java.util.*;

public class InMemoryEatInOrderRepository implements EatInOrderRepository {
    private final Map<UUID, EatInOrder> eatInOrders = new HashMap<>();

    @Override
    public EatInOrder save(final EatInOrder eatInOrder) {
        eatInOrders.put(eatInOrder.getId(), eatInOrder);
        return eatInOrder;
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
    public boolean existsByRestaruantTableAndStatusNot(RestaurantTable restaurantTable, EatInOrderStatus status) {
        return eatInOrders.values()
                .stream()
                .anyMatch(order -> order.getRestaurantTable().equals(restaurantTable) && order.getStatus() != status);
    }

}
