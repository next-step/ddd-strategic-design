package kitchenpos.order.order.infrastructure.persistence;

import kitchenpos.order.eatin.model.OrderTable;
import kitchenpos.order.order.repository.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
