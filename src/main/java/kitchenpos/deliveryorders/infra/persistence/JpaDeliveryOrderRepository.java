package kitchenpos.deliveryorders.infra.persistence;

import java.util.UUID;
import kitchenpos.deliveryorders.domain.DeliveryOrderRepository;
import kitchenpos.deliveryorders.domain.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDeliveryOrderRepository extends DeliveryOrderRepository,
    JpaRepository<DeliveryOrder, UUID> {

}
