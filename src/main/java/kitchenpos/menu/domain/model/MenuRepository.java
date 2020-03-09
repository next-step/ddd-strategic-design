package kitchenpos.menu.domain.model;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    Menu save(Menu entity);

    Optional<Menu> findById(Long id);

    List<Menu> findAll();

    long countByIdIn(List<Long> ids);
}
