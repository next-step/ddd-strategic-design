package kitchenpos.tobe.order.deliveryorder.out.persistance;

import kitchenpos.tobe.order.deliveryorder.application.port.out.OrderRepository;
import kitchenpos.tobe.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaDeliveryOrderRepository extends OrderRepository, JpaRepository<Order, UUID> {
}
