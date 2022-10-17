package kitchenpos.takeout_order.infra;

import java.util.UUID;
import kitchenpos.takeout_order.domain.TakeoutOrderTable;
import kitchenpos.takeout_order.domain.TakeoutOrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTakeoutOrderTableRepository extends TakeoutOrderTableRepository, JpaRepository<TakeoutOrderTable, UUID> {
}
