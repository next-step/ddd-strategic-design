package kitchenpos.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import kitchenpos.order.domain.OrderRepository;
import kitchenpos.order.domain.Order;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
