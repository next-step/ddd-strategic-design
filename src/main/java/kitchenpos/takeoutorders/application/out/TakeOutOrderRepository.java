package kitchenpos.takeoutorders.application.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.takeoutorders.domain.TakeOutOrder;

public interface TakeOutOrderRepository {

    TakeOutOrder save(TakeOutOrder order);

    Optional<TakeOutOrder> findById(UUID id);

    List<TakeOutOrder> findAll();
}

