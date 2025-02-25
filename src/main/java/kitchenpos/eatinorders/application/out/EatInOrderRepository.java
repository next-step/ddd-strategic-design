package kitchenpos.eatinorders.application.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.eatinorders.domain.EatInOrder;
import kitchenpos.eatinorders.domain.EatInOrderStatus;
import kitchenpos.eatinorders.domain.OrderTable;

public interface EatInOrderRepository {

    EatInOrder save(EatInOrder eatInOrder);

    Optional<EatInOrder> findById(UUID id);

    List<EatInOrder> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, EatInOrderStatus status);
}

