package kitchenpos.menu.domain;

import java.math.BigDecimal;
import java.util.List;

public class Menus {
	private List<Menu> menus;

	public Menus(List<Menu> menus) {
		hideMenus(menus);
		this.menus = menus;
	}

	private void hideMenus(List<Menu> menus) {
		for (final Menu menu : menus) {
			BigDecimal sum = BigDecimal.ZERO;
			for (final MenuProduct menuProduct : menu.getMenuProducts()) {
				sum = menuProduct.getProduct()
					.getPrice()
					.multiply(BigDecimal.valueOf(menuProduct.getQuantity()));
			}
			if (menu.getPrice().compareTo(sum) > 0) {
				menu.setDisplayed(false);
			}
		}
	}
}
