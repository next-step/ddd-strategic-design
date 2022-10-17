package kitchenpos.eat_in_order.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EatInOrderTableRepository {

    EatInOrderTable save(EatInOrderTable eatInOrderTable);

    Optional<EatInOrderTable> findById(UUID id);

    List<EatInOrderTable> findAll();
}

