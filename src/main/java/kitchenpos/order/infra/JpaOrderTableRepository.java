package kitchenpos.order.infra;

import kitchenpos.order.domain.ordertable.OrderTable;
import kitchenpos.order.domain.ordertable.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
