package kitchenpos.menu.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import kitchenpos.menu.domain.Menu;

public class MenuResponse {
	private UUID id;

	private String name;

	private BigDecimal price;

	private MenuGroupResonse menuGroupResonse;

	private boolean displayed;

	private List<MenuProductResponse> menuProductResponses;

	public MenuResponse() {
	}

	public MenuResponse(UUID id, String name, BigDecimal price, MenuGroupResonse menuGroupResonse, boolean displayed,  List<MenuProductResponse> menuProductResponses) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.menuGroupResonse = menuGroupResonse;
		this.displayed = displayed;
		this.menuProductResponses = menuProductResponses;
	}

	public static MenuResponse from(Menu menu) {
		return new MenuResponse(
			menu.getId()
		, menu.getDisplayName()
		, menu.getPrice()
		, MenuGroupResonse.of(menu.getMenuGroup())
		, menu.isDisplayed()
		, menu.getMenuProducts().stream()
			.map(MenuProductResponse::of)
			.collect(Collectors.toList())
		);
	}

	public BigDecimal getPrice() {
		return price;
	}
}
