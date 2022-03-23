package kitchenpos.menu.domain;

import java.util.List;
import java.util.UUID;

public interface MenuService {

    Menu create(final Menu request);

    Menu changePrice(final UUID menuId, final Menu request);

    Menu display(final UUID menuId);

    Menu hide(final UUID menuId);

    List<Menu> findAll();

    Menu find(final UUID menuId);
}
