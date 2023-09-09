package kitchenpos.ordertable.infra;

import kitchenpos.order.domain.OrderTable;
import kitchenpos.ordertable.domain.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
