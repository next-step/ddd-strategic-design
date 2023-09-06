package kitchenpos.order.infra.common;

import kitchenpos.order.domain.common.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
