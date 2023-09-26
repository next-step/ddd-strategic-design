package kitchenpos.deliveryorder.infra;

import kitchenpos.deliveryorder.domain.DeliveryMenu;
import kitchenpos.deliveryorder.domain.DeliveryMenuRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaDeliveryMenuRepository extends DeliveryMenuRepository, JpaRepository<DeliveryMenu, UUID>{

}
