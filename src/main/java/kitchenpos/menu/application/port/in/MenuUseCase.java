package kitchenpos.menu.application.port.in;

import java.util.List;
import java.util.UUID;

import kitchenpos.menu.domain.Menu;

public interface MenuUseCase {

	Menu create(final Menu request);

	Menu changePrice(final UUID menuId, final Menu request);

	Menu display(final UUID menuId);

	Menu hide(final UUID menuId);

	List<Menu> findAll();
}
