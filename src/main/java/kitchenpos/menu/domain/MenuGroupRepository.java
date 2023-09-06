package kitchenpos.menu.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.menu.domain.MenuGroup;

public interface MenuGroupRepository {
    MenuGroup save(MenuGroup menuGroup);

    Optional<MenuGroup> findById(UUID id);

    List<MenuGroup> findAll();
}

