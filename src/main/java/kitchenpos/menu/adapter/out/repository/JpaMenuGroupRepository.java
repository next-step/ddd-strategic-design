package kitchenpos.menu.adapter.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import kitchenpos.menu.application.port.out.MenuGroupRepository;
import kitchenpos.menu.domain.MenuGroup;

public interface JpaMenuGroupRepository extends MenuGroupRepository, JpaRepository<MenuGroup, UUID> {
}
