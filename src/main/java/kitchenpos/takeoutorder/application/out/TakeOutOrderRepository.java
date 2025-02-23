package kitchenpos.takeoutorder.application.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.eatinorder.domain.EatInOrder;
import kitchenpos.eatinorder.domain.EatInOrderStatus;
import kitchenpos.eatinorder.domain.OrderTable;

public interface TakeOutOrderRepository {
    EatInOrder save(EatInOrder order);

    Optional<EatInOrder> findById(UUID id);

    List<EatInOrder> findAll();

    boolean existsByOrderTableAndStatusNot(OrderTable orderTable, EatInOrderStatus status);
}

