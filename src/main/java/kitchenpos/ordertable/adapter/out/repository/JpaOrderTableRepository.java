package kitchenpos.ordertable.adapter.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import kitchenpos.ordertable.domain.OrderTable;
import kitchenpos.ordertable.application.port.out.OrderTableRepository;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
