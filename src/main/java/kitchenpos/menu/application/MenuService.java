package kitchenpos.menu.application;

import kitchenpos.menu.application.usecase.CreateMenu;
import kitchenpos.menu.application.usecase.ManipulateMenu;
import kitchenpos.menu.application.usecase.RetrieveMenu;

public interface MenuService extends ManipulateMenu, CreateMenu, RetrieveMenu {
}