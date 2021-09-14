package kitchenpos.order.infra.jpa;

import kitchenpos.order.domain.model.OrderTable;
import kitchenpos.order.domain.repository.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
