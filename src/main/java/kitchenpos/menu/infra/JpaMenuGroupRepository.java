package kitchenpos.menu.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuGroupRepository;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
