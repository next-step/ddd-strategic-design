package kitchenpos.eatinorder.infra;

import kitchenpos.eatinorder.domain.Order;
import kitchenpos.eatinorder.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
