package kitchenpos.menu.application.port.in;

import java.util.List;
import java.util.UUID;

import kitchenpos.menu.domain.Menu;

public interface MenuServicePort {
    Menu create(Menu request);

    Menu changePrice(UUID menuId, Menu request);

    Menu display(UUID menuId);

    Menu hide(UUID menuId);

    List<Menu> findAll();
}
