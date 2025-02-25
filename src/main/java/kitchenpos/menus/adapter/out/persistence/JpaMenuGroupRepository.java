package kitchenpos.menus.adapter.out.persistence;

import java.util.UUID;
import kitchenpos.menus.application.out.MenuGroupRepository;
import kitchenpos.menus.domain.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMenuGroupRepository extends MenuGroupRepository,
    JpaRepository<MenuGroup, UUID> {

}
