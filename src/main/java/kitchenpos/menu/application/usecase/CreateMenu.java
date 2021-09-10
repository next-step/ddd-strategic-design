package kitchenpos.menu.application.usecase;

import kitchenpos.menu.domain.Menu;

public interface CreateMenu {
    Menu create(final Menu request);
}
