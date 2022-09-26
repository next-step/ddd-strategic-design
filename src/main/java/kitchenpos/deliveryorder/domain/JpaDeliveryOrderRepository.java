package kitchenpos.deliveryorder.domain;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDeliveryOrderRepository extends
    DeliveryOrderRepository, JpaRepository<DeliveryOrder, UUID> {
}
