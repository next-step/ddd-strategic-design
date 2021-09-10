package kitchenpos.menugroup.application.Impl;

import kitchenpos.menugroup.domain.MenuGroup;
import kitchenpos.menugroup.infra.MenuGroupRepository;
import kitchenpos.menugroup.application.MenuGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MenuGroupServiceImpl implements MenuGroupService {

    private final MenuGroupRepository menuGroupRepository;

    public MenuGroupServiceImpl(MenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    @Transactional
    @Override
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

    @Transactional(readOnly = true)
    @Override
    public List<MenuGroup> findAll() {
        return menuGroupRepository.findAll();
    }

}
