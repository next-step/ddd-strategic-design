package kitchenpos.order.domain.repository;

import java.util.UUID;
import kitchenpos.order.domain.model.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
