package kitchenpos.takeOutOrder.domain;

import kitchenpos.takeOutOrder.domain.Order;
import kitchenpos.takeOutOrder.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
