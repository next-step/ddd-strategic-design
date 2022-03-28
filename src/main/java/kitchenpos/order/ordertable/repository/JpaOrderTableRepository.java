package kitchenpos.order.ordertable.repository;

import kitchenpos.order.ordertable.domain.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
