package kitchenpos.deliveryorder;


import kitchenpos.deliveryorder.domain.DeliveryOrder;
import kitchenpos.deliveryorder.repository.DeliveryOrderRepository;

import java.util.*;

public class InMemoryDeliveryOrderRepository implements DeliveryOrderRepository {
    private final Map<UUID, DeliveryOrder> orders = new HashMap<>();

    @Override
    public DeliveryOrder save(final DeliveryOrder eatInOrder) {
        orders.put(eatInOrder.getId(), eatInOrder);
        return eatInOrder;
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
