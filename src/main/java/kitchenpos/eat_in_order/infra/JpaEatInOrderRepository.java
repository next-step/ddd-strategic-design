package kitchenpos.eat_in_order.infra;

import java.util.UUID;
import kitchenpos.eat_in_order.domain.EatInOrder;
import kitchenpos.eat_in_order.domain.EatInOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEatInOrderRepository extends EatInOrderRepository,
    JpaRepository<EatInOrder, UUID> {

}
