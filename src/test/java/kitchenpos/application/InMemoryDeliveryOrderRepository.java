package kitchenpos.application;

import kitchenpos.deliveryorder.domain.DeliveryOrder;
import kitchenpos.deliveryorder.domain.DeliveryOrderRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryDeliveryOrderRepository implements DeliveryOrderRepository {
    private final Map<UUID, DeliveryOrder> deliveryOrders = new HashMap<>();

    @Override
    public DeliveryOrder save(final DeliveryOrder deliveryOrder) {
        deliveryOrders.put(deliveryOrder.getId(), deliveryOrder);
        return deliveryOrder;
    }

    @Override
    public Optional<DeliveryOrder> findById(final UUID id) {
        return Optional.ofNullable(deliveryOrders.get(id));
    }

    @Override
    public List<DeliveryOrder> findAll() {
        return new ArrayList<>(deliveryOrders.values());
    }
}
