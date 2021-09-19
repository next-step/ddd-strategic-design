package tobe.kitchenpos.order.domain.eatin;

import org.springframework.data.jpa.repository.JpaRepository;
import tobe.kitchenpos.order.domain.common.Order;

import java.util.UUID;

public interface JpaEatInOrderRepository extends EatInOrderRepository, JpaRepository<Order, UUID> {
}
