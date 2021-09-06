package kitchenpos.infra.repository;

import kitchenpos.domain.menu.menugroup.MenuGroup;
import kitchenpos.domain.menu.menugroup.MenuGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
