package kitchenpos.menu.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroupRepository;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.menu.domain.DisplayName;
import kitchenpos.menu.domain.Price;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.menu.dto.MenuResponse;
import kitchenpos.product.domain.ProductRepository;
import kitchenpos.product.infra.PurgomalumClient;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuGroupRepository menuGroupRepository;
    private final ProductRepository productRepository;
    private final PurgomalumClient purgomalumClient;

    public MenuService(
        final MenuRepository menuRepository,
        final MenuGroupRepository menuGroupRepository,
        final ProductRepository productRepository,
        final PurgomalumClient purgomalumClient
    ) {
        this.menuRepository = menuRepository;
        this.menuGroupRepository = menuGroupRepository;
        this.productRepository = productRepository;
        this.purgomalumClient = purgomalumClient;
    }

    @Transactional
    public Menu create(final MenuRequest request) {
		List<MenuProduct> menuProducts = getMenuProducts(request);

		Menu menu = new Menu(
			new DisplayName(request.getName(), purgomalumClient)
			, new Price(request.getPrice())
			, menuGroupRepository.findById(request.getMenuGroupRequest().getId()).orElseThrow(NoSuchElementException::new)
			, request.isDisplayed()
			, menuProducts
		);

		return menuRepository.save(menu);
    }

	private List<MenuProduct> getMenuProducts(MenuRequest request) {
		return request.getMenuProductRequests().stream()
				.map(menuProductRequest -> new MenuProduct(productRepository.findById(menuProductRequest.getProductRequest().getId()).orElseThrow(
					NoSuchElementException::new), menuProductRequest.getQuantity())).collect(
					Collectors.toList());
	}

	@Transactional
    public MenuResponse changePrice(final UUID menuId, final MenuRequest request) {
		final Menu menu = menuRepository.findById(menuId)
			.orElseThrow(NoSuchElementException::new);

        return MenuResponse.of(menu.changePrice(new Price(request.getPrice())));
    }

    @Transactional
    public Menu display(final UUID menuId) {
        final Menu menu = menuRepository.findById(menuId)
            .orElseThrow(NoSuchElementException::new);

        return menu.display();
    }

    @Transactional
    public Menu hide(final UUID menuId) {
        final Menu menu = menuRepository.findById(menuId)
            .orElseThrow(NoSuchElementException::new);

        return menu.hide();
    }

    @Transactional(readOnly = true)
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }
}
