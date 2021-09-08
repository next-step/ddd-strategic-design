package kitchenpos.ordertable.infra.jpa;

import kitchenpos.ordertable.domain.model.OrderTable;
import kitchenpos.ordertable.domain.repository.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
