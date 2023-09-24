package kitchenpos.eatinorders.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kitchenpos.eatinorders.domain.order.Order;
import kitchenpos.eatinorders.domain.order.OrderRepository;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
