package kitchenpos.menugroup.application.usecase;

import kitchenpos.menugroup.domain.MenuGroup;

public interface CreateMenuGroup {
    MenuGroup create(final MenuGroup request);
}
