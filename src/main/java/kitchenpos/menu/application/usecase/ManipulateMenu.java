package kitchenpos.menu.application.usecase;

import kitchenpos.domain.Menu;

import java.util.UUID;

public interface ManipulateMenu {
    Menu changePrice(final UUID menuId, final Menu request);

    Menu display(final UUID menuId);

    Menu hide(final UUID menuId);
}
