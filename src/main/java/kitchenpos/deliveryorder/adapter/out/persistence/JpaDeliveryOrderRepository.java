package kitchenpos.deliveryorder.adapter.out.persistence;

import java.util.UUID;
import kitchenpos.eatinorder.application.out.EatInOrderRepository;
import kitchenpos.eatinorder.domain.EatInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDeliveryOrderRepository extends EatInOrderRepository, JpaRepository<EatInOrder, UUID> {
}
