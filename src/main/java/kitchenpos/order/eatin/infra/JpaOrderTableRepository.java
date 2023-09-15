package kitchenpos.order.eatin.infra;

import kitchenpos.order.eatin.domain.ordertable.OrderTable;
import kitchenpos.order.eatin.domain.ordertable.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
