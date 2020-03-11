package kitchenpos.menu.doamin;

import kitchenpos.menu.doamin.MenuProduct;

import java.util.List;
import java.util.Optional;

public interface MenuProductDao {
    MenuProduct save(MenuProduct entity);

    Optional<MenuProduct> findById(Long id);

    List<MenuProduct> findAll();

    List<MenuProduct> findAllByMenuId(Long menuId);
}
