package kitchenpos.eatinorder.adapter.out.persistence;

import kitchenpos.eatinorder.domain.OrderTable;
import kitchenpos.eatinorder.application.out.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
