package kitchenpos.takeoutorder.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TakeOutMenuRepository {
    TakeOutMenu save(TakeOutMenu takeOutMenu);

    Optional<TakeOutMenu> findById(UUID id);

    List<TakeOutMenu> findAll();

    List<TakeOutMenu> findAllByIdIn(List<UUID> ids);

}

