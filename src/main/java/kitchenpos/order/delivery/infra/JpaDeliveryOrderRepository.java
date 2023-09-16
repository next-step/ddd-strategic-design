package kitchenpos.order.delivery.infra;

import kitchenpos.order.delivery.domain.DeliveryOrder;
import kitchenpos.order.delivery.domain.DeliveryOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaDeliveryOrderRepository extends DeliveryOrderRepository, JpaRepository<DeliveryOrder, UUID> {
}
