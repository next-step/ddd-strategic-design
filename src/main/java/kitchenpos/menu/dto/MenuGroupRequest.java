package kitchenpos.menu.dto;

import java.util.UUID;

public class MenuGroupRequest {
	private UUID id;

	private String name;

	public MenuGroupRequest() {
	}

	public MenuGroupRequest(UUID id, String name) {
		this.id = id;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
