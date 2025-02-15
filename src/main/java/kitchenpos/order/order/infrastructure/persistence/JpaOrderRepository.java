package kitchenpos.order.order.infrastructure.persistence;

import kitchenpos.order.order.model.Order;
import kitchenpos.order.order.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
