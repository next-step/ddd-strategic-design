package tobe.kitchenpos.order.domain.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import tobe.kitchenpos.order.domain.common.Order;

import java.util.UUID;

public interface JpaDeliveryOrderRepository extends DeliveryOrderRepository, JpaRepository<Order, UUID> {
}
