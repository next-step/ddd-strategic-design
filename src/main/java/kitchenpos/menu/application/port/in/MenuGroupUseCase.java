package kitchenpos.menu.application.port.in;

import java.util.List;

import kitchenpos.menu.domain.MenuGroup;

public interface MenuGroupUseCase {
	MenuGroup create(final MenuGroup request);

	List<MenuGroup> findAll();
}
