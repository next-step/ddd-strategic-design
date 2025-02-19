package kitchenpos.order.eatinorder.adaptor.out.persistance;

import kitchenpos.order.eatinorder.domain.OrderTable;
import kitchenpos.order.eatinorder.application.port.out.OrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
