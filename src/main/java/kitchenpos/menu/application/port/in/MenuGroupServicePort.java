package kitchenpos.menu.application.port.in;

import java.util.List;

import kitchenpos.menu.domain.MenuGroup;

public interface MenuGroupServicePort {
    MenuGroup create(MenuGroup request);

    List<MenuGroup> findAll();
}
