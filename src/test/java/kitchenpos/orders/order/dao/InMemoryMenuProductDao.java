package kitchenpos.orders.order.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import kitchenpos.menus.menu.dao.MenuProductDao;
import kitchenpos.menus.menu.domain.MenuProduct;

public class InMemoryMenuProductDao implements MenuProductDao {

    private final Map<Long, MenuProduct> entities = new HashMap<>();

    @Override
    public MenuProduct save(final MenuProduct entity) {
        entities.put(entity.getSeq(), entity);
        return entity;
    }

    @Override
    public Optional<MenuProduct> findById(final Long id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<MenuProduct> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public List<MenuProduct> findAllByMenuId(final Long menuId) {
        return entities.values()
            .stream()
            .filter(entity -> Objects.equals(entity.getMenuId(), menuId))
            .collect(Collectors.toList())
            ;
    }
}
