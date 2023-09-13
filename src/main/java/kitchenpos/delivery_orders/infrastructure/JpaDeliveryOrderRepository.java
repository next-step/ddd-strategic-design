package kitchenpos.delivery_orders.infrastructure;

import kitchenpos.delivery_orders.domain.DeliveryOrder;
import kitchenpos.delivery_orders.domain.DeliveryOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaDeliveryOrderRepository extends DeliveryOrderRepository, JpaRepository<DeliveryOrder, UUID> {
}
