package kitchenpos.menu.dto;

import java.util.UUID;

import kitchenpos.menu.domain.MenuGroup;

public class MenuGroupResonse {
	private UUID id;

	private String name;

	public MenuGroupResonse(UUID id, String name) {
		this.id = id;
		this.name = name;
	}

	public static MenuGroupResonse of(MenuGroup menuGroup) {
		return new MenuGroupResonse(menuGroup.getId(), menuGroup.getName());
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
