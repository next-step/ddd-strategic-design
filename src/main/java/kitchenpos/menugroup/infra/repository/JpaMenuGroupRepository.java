package kitchenpos.menugroup.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import kitchenpos.menugroup.domain.MenuGroup;
import kitchenpos.menugroup.domain.MenuGroupRepository;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
