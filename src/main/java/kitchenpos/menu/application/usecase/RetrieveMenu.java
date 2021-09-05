package kitchenpos.menu.application.usecase;

import kitchenpos.menu.Menu;

import java.util.List;

public interface RetrieveMenu {
    List<Menu> findAll();
}
