package kitchenpos.deliveryorder.repository;

import kitchenpos.deliveryorder.domain.DeliveryOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryOrderRepository {
    DeliveryOrder save(DeliveryOrder order);

    Optional<DeliveryOrder> findById(UUID id);

    List<DeliveryOrder> findAll();

}

