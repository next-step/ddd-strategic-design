package kitchenpos.order.takeoutorder.adaptor.out.persistance;

import kitchenpos.order.eatinorder.application.port.out.OrderTableRepository;
import kitchenpos.order.eatinorder.domain.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTakeOutOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
