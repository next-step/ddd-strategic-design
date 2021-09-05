package kitchenpos.menu.application.usecase;

import kitchenpos.domain.Menu;

public interface CreateMenu {
    Menu create(final Menu request);
}
