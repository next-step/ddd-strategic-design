package kitchenpos.order.infra;

import kitchenpos.order.domain.Order;
import kitchenpos.order.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
