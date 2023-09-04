package kitchenpos.menu.fixture;

import kitchenpos.menu.domain.MenuGroup;

import java.util.UUID;

public class MenuGroupFixture {

    public static MenuGroup menuGroup() {
        return menuGroup("두마리메뉴");
    }

    public static MenuGroup menuGroup(final String name) {
        final MenuGroup menuGroup = new MenuGroup();
        menuGroup.setId(UUID.randomUUID());
        menuGroup.setName(name);
        return menuGroup;
    }


}
