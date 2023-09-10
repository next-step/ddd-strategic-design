package kitchenpos.application.order;

import kitchenpos.order.deliveryorder.domain.DeliveryOrder;
import kitchenpos.order.deliveryorder.domain.DeliveryOrderRepository;

import java.util.*;

public class InMemoryDeliveryOrderRepository implements DeliveryOrderRepository {
    private final Map<UUID, DeliveryOrder> orders = new HashMap<>();

    @Override
    public DeliveryOrder save(final DeliveryOrder order) {
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Optional<DeliveryOrder> findById(final UUID id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<DeliveryOrder> findAll() {
        return new ArrayList<>(orders.values());
    }

}
