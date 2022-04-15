package kitchenpos.menu.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MenuRequest {
	private String name;

	private BigDecimal price;

	private MenuGroupRequest menuGroupRequest;

	private boolean displayed;

	private List<MenuProductRequest> menuProductRequests;

	public MenuRequest() {
	}

	public MenuRequest(BigDecimal price) {
		this.price = price;
	}

	public MenuRequest(String name, BigDecimal price, MenuGroupRequest menuGroupRequest, boolean displayed, List<MenuProductRequest> menuProductRequests) {
		this.name = name;
		this.price = price;
		this.menuGroupRequest = menuGroupRequest;
		this.displayed = displayed;
		this.menuProductRequests = menuProductRequests;
	}

	public String getDisplayName() {
		return name;
	}

	public UUID getMenuGroupId() {
		return menuGroupRequest.getId();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public boolean isDisplayed() {
		return displayed;
	}

	public List<MenuProductRequest> getMenuProductRequests() {
		return menuProductRequests;
	}

	public MenuGroupRequest getMenuGroupRequest() {
		return menuGroupRequest;
	}

	public String getName() {
		return name;
	}
}
