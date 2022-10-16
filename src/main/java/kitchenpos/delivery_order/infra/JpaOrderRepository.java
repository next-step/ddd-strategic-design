package kitchenpos.delivery_order.infra;

import java.util.UUID;
import kitchenpos.delivery_order.domain.Order;
import kitchenpos.delivery_order.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
