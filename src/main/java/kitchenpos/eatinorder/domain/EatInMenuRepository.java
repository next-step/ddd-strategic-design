package kitchenpos.eatinorder.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EatInMenuRepository {
    EatInMenu save(EatInMenu eatInMenu);

    Optional<EatInMenu> findById(UUID id);

    List<EatInMenu> findAll();

    List<EatInMenu> findAllByIdIn(List<UUID> ids);

    List<EatInMenu> findAllByProductId(UUID productId);
}

