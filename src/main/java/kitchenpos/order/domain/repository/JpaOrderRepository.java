package kitchenpos.order.domain.repository;

import java.util.UUID;
import kitchenpos.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
