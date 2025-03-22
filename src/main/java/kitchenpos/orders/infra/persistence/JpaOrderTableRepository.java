package kitchenpos.orders.infra.persistence;

import kitchenpos.orders.domain.OrderTable;
import kitchenpos.orders.infra.persistence.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
