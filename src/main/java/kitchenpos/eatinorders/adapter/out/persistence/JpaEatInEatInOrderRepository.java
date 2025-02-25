package kitchenpos.eatinorders.adapter.out.persistence;

import java.util.UUID;
import kitchenpos.eatinorders.application.out.EatInOrderRepository;
import kitchenpos.eatinorders.domain.EatInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEatInEatInOrderRepository extends EatInOrderRepository, JpaRepository<EatInOrder, UUID> {

}
