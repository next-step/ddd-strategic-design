package kitchenpos.ordertable.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import kitchenpos.ordertable.domain.OrderTable;
import kitchenpos.ordertable.domain.OrderTableRepository;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
