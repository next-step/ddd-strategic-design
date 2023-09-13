package kitchenpos.takeout_orders.infrastructure;

import kitchenpos.takeout_orders.domain.TakeoutOrder;
import kitchenpos.takeout_orders.domain.TakeoutOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTakeoutOrderRepository extends TakeoutOrderRepository, JpaRepository<TakeoutOrder, UUID> {
}
