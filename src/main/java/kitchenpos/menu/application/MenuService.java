package kitchenpos.menu.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuGroupRepository;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.menu.domain.DisplayName;
import kitchenpos.menu.domain.Price;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.menu.dto.MenuResponse;
import kitchenpos.product.infra.PurgomalumClient;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuGroupRepository menuGroupRepository;
    private final PurgomalumClient purgomalumClient;

    public MenuService(
        final MenuRepository menuRepository,
        final MenuGroupRepository menuGroupRepository,
        final PurgomalumClient purgomalumClient
    ) {
        this.menuRepository = menuRepository;
        this.menuGroupRepository = menuGroupRepository;
        this.purgomalumClient = purgomalumClient;
    }

    @Transactional
    public MenuResponse create(final MenuRequest request) {
		List<MenuProduct> menuProducts = getMenuProducts(request);

		Menu menu = new Menu(
			new DisplayName(request.getName(), purgomalumClient)
			, new Price(request.getPrice())
			, findMenuGroup(request)
			, request.isDisplayed()
			, menuProducts
		);

		return MenuResponse.from(menuRepository.save(menu));
    }

	private MenuGroup findMenuGroup(MenuRequest request) {
		return menuGroupRepository.findById(request.getMenuGroupRequest().getId()).orElseThrow(NoSuchElementException::new);
	}

	private List<MenuProduct> getMenuProducts(MenuRequest request) {
		return request.getMenuProductRequests().stream()
			.map(menuProductRequest -> new MenuProduct(menuProductRequest.getPrice(), menuProductRequest.getQuantity()))
			.collect(Collectors.toList());
	}

	@Transactional
    public MenuResponse changePrice(final UUID menuId, final MenuRequest request) {
		final Menu menu = findMenu(menuId);
		return MenuResponse.from(menu.changePrice(new Price(request.getPrice())));
    }

	private Menu findMenu(UUID menuId) {
		return menuRepository.findById(menuId)
			.orElseThrow(NoSuchElementException::new);
	}

	@Transactional
    public MenuResponse display(final UUID menuId) {
		final Menu menu = findMenu(menuId);
		return MenuResponse.from(menu.display());
    }

    @Transactional
    public MenuResponse hide(final UUID menuId) {
		final Menu menu = findMenu(menuId);
		return MenuResponse.from(menu.hide());
    }

    @Transactional(readOnly = true)
    public List<MenuResponse> findAll() {
        return menuRepository.findAll().stream()
			.map(MenuResponse::from)
			.collect(Collectors.toList());
    }
}
