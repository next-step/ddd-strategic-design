package kitchenpos.infra.order;

import kitchenpos.domain.order.domain.Order;
import kitchenpos.domain.order.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
