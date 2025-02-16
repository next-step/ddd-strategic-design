package kitchenpos.order.common.infra.persistence;

import kitchenpos.order.eatin.domain.model.OrderTable;
import kitchenpos.order.eatin.domain.repository.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
