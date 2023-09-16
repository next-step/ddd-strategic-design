package kitchenpos.order.infra;

import java.util.UUID;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {

}
