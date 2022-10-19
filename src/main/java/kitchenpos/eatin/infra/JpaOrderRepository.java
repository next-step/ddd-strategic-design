package kitchenpos.eatin.infra;

import kitchenpos.eatin.domain.Order;
import kitchenpos.eatin.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
