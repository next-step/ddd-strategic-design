package kitchenpos.infra.repository;

import kitchenpos.domain.order.order.Order;
import kitchenpos.domain.order.order.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
