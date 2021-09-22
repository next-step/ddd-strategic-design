package tobe.kitchenpos.order.delivery.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import tobe.kitchenpos.order.common.domain.Order;

import java.util.UUID;

public interface JpaDeliveryOrderRepository extends DeliveryOrderRepository, JpaRepository<Order, UUID> {
}
