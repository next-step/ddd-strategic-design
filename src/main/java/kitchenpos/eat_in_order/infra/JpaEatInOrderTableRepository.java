package kitchenpos.eat_in_order.infra;

import java.util.UUID;
import kitchenpos.eat_in_order.domain.EatInOrderTable;
import kitchenpos.eat_in_order.domain.EatInOrderTableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEatInOrderTableRepository extends EatInOrderTableRepository,
    JpaRepository<EatInOrderTable, UUID> {

}
