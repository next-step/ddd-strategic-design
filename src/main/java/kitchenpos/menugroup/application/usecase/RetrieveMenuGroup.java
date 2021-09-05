package kitchenpos.menugroup.application.usecase;

import kitchenpos.menugroup.MenuGroup;

import java.util.List;

public interface RetrieveMenuGroup {
    List<MenuGroup> findAll();
}
