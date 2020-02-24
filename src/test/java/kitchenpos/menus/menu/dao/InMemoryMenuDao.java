package kitchenpos.menus.menu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import kitchenpos.menus.menu.domain.Menu;

public class InMemoryMenuDao implements MenuDao {

    private final Map<Long, Menu> entities = new HashMap<>();

    @Override
    public Menu save(final Menu entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Menu> findById(final Long id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<Menu> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public long countByIdIn(final List<Long> ids) {
        return entities.values()
            .stream()
            .filter(entity -> ids.contains(entity.getId()))
            .count()
            ;
    }
}
