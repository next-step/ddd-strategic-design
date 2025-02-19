package kitchenpos.tobe.order.eatinorder.adaptor.out.persistance;

import kitchenpos.tobe.order.deliveryorder.application.port.out.OrderRepository;
import kitchenpos.tobe.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaEatInOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
