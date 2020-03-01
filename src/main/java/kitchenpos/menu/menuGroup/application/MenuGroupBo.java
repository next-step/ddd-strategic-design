package kitchenpos.menu.menuGroup.application;

import kitchenpos.menu.menuGroup.domain.MenuGroupDao;
import kitchenpos.menu.menuGroup.domain.MenuGroup;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class MenuGroupBo {
    private final MenuGroupDao menuGroupDao;

    public MenuGroupBo(final MenuGroupDao menuGroupDao) {
        this.menuGroupDao = menuGroupDao;
    }

    @Transactional
    public MenuGroup create(final MenuGroup menuGroup) {
        return menuGroupDao.save(menuGroup);
    }

    public List<MenuGroup> list() {
        return menuGroupDao.findAll();
    }
}
