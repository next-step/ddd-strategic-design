package kitchenpos.order.order.domain.repository;

import java.util.UUID;
import kitchenpos.order.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
