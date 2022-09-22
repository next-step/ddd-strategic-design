package kitchenpos.menu.menugroup.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.menu.menugroup.domain.model.MenuGroup;

public interface MenuGroupRepository {
    MenuGroup save(MenuGroup menuGroup);

    Optional<MenuGroup> findById(UUID id);

    List<MenuGroup> findAll();
}

