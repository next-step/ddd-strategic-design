package kitchenpos.order.adapter.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import kitchenpos.order.domain.Order;
import kitchenpos.order.application.port.out.OrderRepository;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
