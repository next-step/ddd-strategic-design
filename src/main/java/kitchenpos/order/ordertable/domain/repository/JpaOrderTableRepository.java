package kitchenpos.order.ordertable.domain.repository;

import java.util.UUID;
import kitchenpos.order.ordertable.domain.model.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
