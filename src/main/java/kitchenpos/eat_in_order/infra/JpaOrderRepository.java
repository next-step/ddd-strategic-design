package kitchenpos.eat_in_order.infra;

import kitchenpos.eat_in_order.domain.Order;
import kitchenpos.eat_in_order.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
