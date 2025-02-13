package kitchenpos.order.infrastructure.persistence;

import kitchenpos.order.domain.eatin.model.OrderTable;
import kitchenpos.order.domain.order.repository.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
