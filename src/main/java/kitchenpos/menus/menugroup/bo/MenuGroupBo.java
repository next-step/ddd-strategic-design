package kitchenpos.menus.menugroup.bo;

import java.util.List;
import kitchenpos.menus.menugroup.dao.MenuGroupDao;
import kitchenpos.menus.menugroup.domain.MenuGroup;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
