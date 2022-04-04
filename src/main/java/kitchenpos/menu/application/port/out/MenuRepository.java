package kitchenpos.menu.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kitchenpos.menu.domain.Menu;

public interface MenuRepository {
    Menu save(Menu menu);

    Optional<Menu> findById(UUID id);

    List<Menu> findAll();

    List<Menu> findAllByIdIn(List<UUID> ids);

    List<Menu> findAllByProductId(UUID productId);
}

