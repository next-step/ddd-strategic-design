package kitchenpos.order.common.infrastructure.persistence;

import kitchenpos.order.common.model.Order;
import kitchenpos.order.common.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
