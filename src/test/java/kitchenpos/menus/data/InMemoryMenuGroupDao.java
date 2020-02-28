package kitchenpos.menus.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import kitchenpos.menus.domain.entity.MenuGroup;
import kitchenpos.menus.domain.entity.MenuGroupDao;

public class InMemoryMenuGroupDao implements MenuGroupDao {

    private final Map<Long, MenuGroup> entities = new HashMap<>();

    @Override
    public MenuGroup save(final MenuGroup entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<MenuGroup> findById(final Long id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<MenuGroup> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public boolean existsById(final Long id) {
        return entities.containsKey(id);
    }
}
