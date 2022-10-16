package kitchenpos.takeout_order.infra;

import java.util.UUID;
import kitchenpos.takeout_order.domain.OrderTable;
import kitchenpos.takeout_order.domain.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
