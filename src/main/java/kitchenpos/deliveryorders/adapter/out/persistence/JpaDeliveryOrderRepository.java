package kitchenpos.deliveryorders.adapter.out.persistence;

import java.util.UUID;
import kitchenpos.deliveryorders.application.out.DeliveryOrderRepository;
import kitchenpos.deliveryorders.domain.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDeliveryOrderRepository extends DeliveryOrderRepository,
    JpaRepository<DeliveryOrder, UUID> {

}
