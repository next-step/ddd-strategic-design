package kitchenpos.order.eatin.infra;

import kitchenpos.order.eatin.domain.Order;
import kitchenpos.order.eatin.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
