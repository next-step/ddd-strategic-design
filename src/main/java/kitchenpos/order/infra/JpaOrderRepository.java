package kitchenpos.order.infra;

import kitchenpos.order.domain.repository.OrderRepository;
import kitchenpos.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
