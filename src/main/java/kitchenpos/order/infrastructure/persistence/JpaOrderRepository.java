package kitchenpos.order.infrastructure.persistence;

import kitchenpos.order.domain.order.model.Order;
import kitchenpos.order.domain.order.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
