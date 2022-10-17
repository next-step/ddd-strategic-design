package kitchenpos.takeout_order.infra;

import java.util.UUID;
import kitchenpos.takeout_order.domain.TakeoutOrder;
import kitchenpos.takeout_order.domain.TakeoutOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTakeoutOrderRepository extends TakeoutOrderRepository, JpaRepository<TakeoutOrder, UUID> {
}
