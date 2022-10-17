package kitchenpos.delivery_order.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryOrderTableRepository {
    DeliveryOrderTable save(DeliveryOrderTable deliveryOrderTable);

    Optional<DeliveryOrderTable> findById(UUID id);

    List<DeliveryOrderTable> findAll();
}

