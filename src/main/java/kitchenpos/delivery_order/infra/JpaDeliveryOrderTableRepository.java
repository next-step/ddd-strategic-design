package kitchenpos.delivery_order.infra;

import java.util.UUID;
import kitchenpos.delivery_order.domain.DeliveryOrderTable;
import kitchenpos.delivery_order.domain.DeliveryOrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDeliveryOrderTableRepository extends DeliveryOrderTableRepository, JpaRepository<DeliveryOrderTable, UUID> {
}
