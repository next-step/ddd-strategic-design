package kitchenpos.delivery_order.infra;

import java.util.UUID;
import kitchenpos.delivery_order.domain.DeliveryOrder;
import kitchenpos.delivery_order.domain.DeliveryOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDeliveryOrderRepository extends DeliveryOrderRepository, JpaRepository<DeliveryOrder, UUID> {
}
