package kitchenpos.dao.menu;

import kitchenpos.model.menu.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuDao {
    Menu save(Menu entity);

    Optional<Menu> findById(Long id);

    List<Menu> findAll();

    long countByIdIn(List<Long> ids);
}
