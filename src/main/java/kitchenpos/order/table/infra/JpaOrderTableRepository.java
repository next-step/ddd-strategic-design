package kitchenpos.order.table.infra;

import kitchenpos.order.table.domain.OrderTable;
import kitchenpos.order.table.domain.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
