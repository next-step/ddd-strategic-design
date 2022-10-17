package kitchenpos.delivery_order.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryOrderRepository {
    DeliveryOrder save(DeliveryOrder deliveryOrder);

    Optional<DeliveryOrder> findById(UUID id);

    List<DeliveryOrder> findAll();

    boolean existsByOrderTableAndStatusNot(DeliveryOrderTable deliveryOrderTable, DeliveryOrderStatus status);
}

