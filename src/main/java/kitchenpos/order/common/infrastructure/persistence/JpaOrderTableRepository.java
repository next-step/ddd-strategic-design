package kitchenpos.order.common.infrastructure.persistence;

import kitchenpos.order.eatin.domain.model.OrderTable;
import kitchenpos.order.common.repository.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
