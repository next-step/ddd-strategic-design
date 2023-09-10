package kitchenpos.orderDelivery.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderDeliveryRepository extends OrderDeliveryRepository, JpaRepository<OrderDelivery, UUID> {
}
