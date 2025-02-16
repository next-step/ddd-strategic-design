package kitchenpos.deliveryorder.domain;

import kitchenpos.eatinorder.domain.EatInOrder;
import kitchenpos.eatinorder.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderRepository extends DeliveryOrderRepository, JpaRepository<EatInOrder, UUID> {
}
