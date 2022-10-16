package kitchenpos.takeout_order.infra;

import java.util.UUID;
import kitchenpos.takeout_order.domain.Order;
import kitchenpos.takeout_order.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
