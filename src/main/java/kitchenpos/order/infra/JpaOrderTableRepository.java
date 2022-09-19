package kitchenpos.order.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import kitchenpos.order.domain.OrderTableRepository;
import kitchenpos.order.domain.OrderTable;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
