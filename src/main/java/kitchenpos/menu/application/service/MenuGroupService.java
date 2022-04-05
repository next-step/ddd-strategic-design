package kitchenpos.menu.application.service;

import kitchenpos.menu.application.port.in.MenuGroupServicePort;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.application.port.out.MenuGroupRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MenuGroupService implements MenuGroupServicePort {
    private final MenuGroupRepository menuGroupRepository;

    public MenuGroupService(final MenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    @Override
    @Transactional
    public MenuGroup create(final MenuGroup request) {
        final String name = request.getName();
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final MenuGroup menuGroup = new MenuGroup();
        menuGroup.setId(UUID.randomUUID());
        menuGroup.setName(name);
        return menuGroupRepository.save(menuGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuGroup> findAll() {
        return menuGroupRepository.findAll();
    }
}
