package kitchenpos.delivery_order.infra;

import java.util.UUID;
import kitchenpos.delivery_order.domain.OrderTable;
import kitchenpos.delivery_order.domain.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
