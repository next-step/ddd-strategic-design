package kitchenpos.menu.application.usecase;

import kitchenpos.domain.Menu;

import java.util.List;

public interface RetrieveMenu {
    List<Menu> findAll();
}
