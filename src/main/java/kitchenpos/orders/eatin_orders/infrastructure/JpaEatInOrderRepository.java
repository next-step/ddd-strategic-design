package kitchenpos.orders.eatin_orders.infrastructure;

import kitchenpos.orders.eatin_orders.domain.EatInOrder;
import kitchenpos.orders.eatin_orders.domain.EatInOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaEatInOrderRepository extends EatInOrderRepository, JpaRepository<EatInOrder, UUID> {
}
