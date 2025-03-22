package kitchenpos.orders.infra.persistence;

import kitchenpos.orders.domain.Order;
import kitchenpos.orders.infra.persistence.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
