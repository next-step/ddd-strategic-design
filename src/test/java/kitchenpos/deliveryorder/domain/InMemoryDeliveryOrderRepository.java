package kitchenpos.deliveryorder.domain;

import java.util.*;

public class InMemoryDeliveryOrderRepository implements DeliveryOrderRepository {
    private final Map<UUID, DeliveryOrder> orders = new HashMap<>();

    @Override
    public DeliveryOrder save(final DeliveryOrder deliveryOrder) {
        orders.put(deliveryOrder.getId(), deliveryOrder);
        return deliveryOrder;
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
