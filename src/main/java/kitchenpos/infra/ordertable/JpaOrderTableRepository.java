package kitchenpos.infra.ordertable;

import kitchenpos.domain.ordertable.domain.OrderTable;
import kitchenpos.domain.ordertable.domain.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
