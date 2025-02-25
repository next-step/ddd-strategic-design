package kitchenpos.eatinorders.infra.persistence;

import java.util.UUID;
import kitchenpos.eatinorders.domain.OrderTableRepository;
import kitchenpos.eatinorders.domain.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderTableRepository extends OrderTableRepository,
    JpaRepository<OrderTable, UUID> {

}
