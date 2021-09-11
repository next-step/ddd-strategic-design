package kitchenpos.menugroup.application.usecase;

import kitchenpos.menugroup.domain.MenuGroup;

import java.util.List;

public interface RetrieveMenuGroup {
    List<MenuGroup> findAll();
}
