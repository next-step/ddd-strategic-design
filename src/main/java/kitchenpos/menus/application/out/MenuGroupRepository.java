package kitchenpos.menus.application.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kitchenpos.menus.domain.MenuGroup;

public interface MenuGroupRepository {

    MenuGroup save(MenuGroup menuGroup);

    Optional<MenuGroup> findById(UUID id);

    List<MenuGroup> findAll();
}

