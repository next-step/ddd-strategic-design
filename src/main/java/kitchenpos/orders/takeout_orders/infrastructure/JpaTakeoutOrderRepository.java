package kitchenpos.orders.takeout_orders.infrastructure;

import kitchenpos.orders.takeout_orders.domain.TakeoutOrder;
import kitchenpos.orders.takeout_orders.domain.TakeoutOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTakeoutOrderRepository extends TakeoutOrderRepository, JpaRepository<TakeoutOrder, UUID> {
}
