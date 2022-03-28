package kitchenpos.order.order.infra;

import kitchenpos.order.order.domain.Order;
import kitchenpos.order.order.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
