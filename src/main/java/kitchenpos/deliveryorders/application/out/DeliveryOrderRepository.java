package kitchenpos.deliveryorders.application.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.deliveryorders.domain.DeliveryOrder;

public interface DeliveryOrderRepository {

    DeliveryOrder save(DeliveryOrder order);

    Optional<DeliveryOrder> findById(UUID id);

    List<DeliveryOrder> findAll();

}

