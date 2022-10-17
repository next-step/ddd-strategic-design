package kitchenpos.takeout_order.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TakeoutOrderTableRepository {
    TakeoutOrderTable save(TakeoutOrderTable takeoutOrderTable);

    Optional<TakeoutOrderTable> findById(UUID id);

    List<TakeoutOrderTable> findAll();
}

