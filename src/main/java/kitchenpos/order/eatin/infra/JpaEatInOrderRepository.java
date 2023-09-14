package kitchenpos.order.eatin.infra;

import kitchenpos.order.eatin.domain.EatInOrder;
import kitchenpos.order.eatin.domain.EatInOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaEatInOrderRepository extends EatInOrderRepository, JpaRepository<EatInOrder, UUID> {
}
