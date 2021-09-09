package kitchenpos.order.ordertable.infra;

import kitchenpos.order.ordertable.domain.OrderTable;
import kitchenpos.order.ordertable.domain.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
