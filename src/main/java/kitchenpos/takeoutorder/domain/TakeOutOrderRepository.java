package kitchenpos.takeoutorder.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TakeOutOrderRepository {
    TakeOutOrder save(TakeOutOrder takeOutOrder);

    Optional<TakeOutOrder> findById(UUID id);

    List<TakeOutOrder> findAll();
}

