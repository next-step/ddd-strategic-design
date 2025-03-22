package kitchenpos.menus.infra.persistence;

import kitchenpos.menus.domain.MenuGroup;
import kitchenpos.menus.infra.persistence.MenuGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
