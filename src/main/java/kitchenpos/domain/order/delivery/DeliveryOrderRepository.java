package kitchenpos.domain.order.delivery;

import kitchenpos.domain.order.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryOrderRepository {
    DeliveryOrder save(Order order);

    Optional<DeliveryOrder> findById(UUID id);

    List<DeliveryOrder> findAll();
}

