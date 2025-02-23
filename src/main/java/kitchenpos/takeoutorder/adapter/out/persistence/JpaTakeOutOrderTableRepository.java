package kitchenpos.takeoutorder.adapter.out.persistence;

import java.util.UUID;
import kitchenpos.eatinorder.application.out.OrderTableRepository;
import kitchenpos.eatinorder.domain.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTakeOutOrderTableRepository extends OrderTableRepository, JpaRepository<OrderTable, UUID> {
}
