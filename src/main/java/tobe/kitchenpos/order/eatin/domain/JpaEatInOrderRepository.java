package tobe.kitchenpos.order.eatin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import tobe.kitchenpos.order.common.domain.Order;

import java.util.UUID;

public interface JpaEatInOrderRepository extends EatInOrderRepository, JpaRepository<Order, UUID> {
}
