package kitchenpos.infra.repository;

import kitchenpos.domain.order.ordertable.OrderTable;
import kitchenpos.domain.order.ordertable.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
