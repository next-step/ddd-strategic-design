package kitchenpos.eat_in_order.infra;

import kitchenpos.eat_in_order.domain.OrderTable;
import kitchenpos.eat_in_order.domain.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
