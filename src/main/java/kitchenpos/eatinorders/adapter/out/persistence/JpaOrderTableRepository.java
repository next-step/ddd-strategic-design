package kitchenpos.eatinorders.adapter.out.persistence;

import java.util.UUID;
import kitchenpos.eatinorders.application.out.OrderTableRepository;
import kitchenpos.eatinorders.domain.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderTableRepository extends OrderTableRepository,
    JpaRepository<OrderTable, UUID> {

}
