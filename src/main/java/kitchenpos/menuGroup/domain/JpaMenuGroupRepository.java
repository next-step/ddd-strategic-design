package kitchenpos.menuGroup.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import kitchenpos.menuGroup.domain.MenuGroup;
import kitchenpos.menuGroup.domain.MenuGroupRepository;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
