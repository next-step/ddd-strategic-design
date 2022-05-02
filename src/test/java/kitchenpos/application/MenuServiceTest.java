package kitchenpos.application;

import static kitchenpos.Fixtures.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import kitchenpos.menu.application.MenuService;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroupRepository;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.menu.domain.Price;
import kitchenpos.menu.dto.MenuGroupRequest;
import kitchenpos.menu.dto.MenuProductRequest;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.menu.dto.MenuResponse;
import kitchenpos.product.domain.Product;
import kitchenpos.product.domain.ProductRepository;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.infra.PurgomalumClient;

class MenuServiceTest {
    private MenuRepository menuRepository;
    private MenuGroupRepository menuGroupRepository;
    private ProductRepository productRepository;
    private PurgomalumClient purgomalumClient;
    private MenuService menuService;
    private UUID menuGroupId;
    private Product product;

    @BeforeEach
    void setUp() {
        menuRepository = new InMemoryMenuRepository();
        menuGroupRepository = new InMemoryMenuGroupRepository();
        productRepository = new InMemoryProductRepository();
        purgomalumClient = new FakePurgomalumClient();
        menuService = new MenuService(menuRepository, menuGroupRepository, productRepository, purgomalumClient);
        menuGroupId = menuGroupRepository.save(menuGroup()).getId();
        product = productRepository.save(product("후라이드", 16_000L));
    }

    @DisplayName("1개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.")
    @Test
    void create() {
        final MenuRequest expected = createMenuRequest(
            "후라이드+후라이드", new BigDecimal(19_000L), new MenuGroupRequest(UUID.randomUUID(), "메뉴"), true, Arrays.asList(createMenuProductRequest("후라이드", new BigDecimal(1000), 2L)));
        final Menu actual = menuService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getDisplayName()).isEqualTo(expected.getDisplayName()),
            () -> assertThat(actual.getPrice()).isEqualTo(expected.getPrice()),
            () -> assertThat(actual.getMenuGroup().getId()).isEqualTo(expected.getMenuGroupId()),
            () -> assertThat(actual.isDisplayed()).isEqualTo(expected.isDisplayed()),
            () -> assertThat(actual.getMenuProducts()).hasSize(1)
        );
    }

    @DisplayName("상품이 없으면 등록할 수 없다.")
    @MethodSource("menuProducts")
    @ParameterizedTest
    void create(final List<MenuProduct> menuProducts) {
        final MenuRequest expected = createMenuRequest("후라이드+후라이드", new BigDecimal(19000), new MenuGroupRequest(UUID.randomUUID(), "메뉴"), true, Arrays.asList(createMenuProductRequest("후라이드", new BigDecimal(1000), 2L)));
        assertThatThrownBy(() -> menuService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static List<Arguments> menuProducts() {
        return Arrays.asList(
            null,
            Arguments.of(Collections.emptyList()),
            Arguments.of(Arrays.asList(createMenuProductRequest(null, new BigDecimal(19000), 2L)))
        );
    }

    @DisplayName("메뉴에 속한 상품의 수량은 0개 이상이어야 한다.")
    @Test
    void createNegativeQuantity() {
        final MenuRequest expected = createMenuRequest(
            "후라이드+후라이드"
			, new BigDecimal(19_000L)
			, createMenuGroupRequest()
			, true
			, Arrays.asList(createMenuProductRequest("후라이드", new BigDecimal(19000), -1L))
        );
        assertThatThrownBy(() -> menuService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 가격이 올바르지 않으면 등록할 수 없다.")
    @ValueSource(strings = "-1000")
    @NullSource
    @ParameterizedTest
    void create(final BigDecimal price) {
        final MenuRequest expected = createMenuRequest(
            "후라이드+후라이드", price,
			createMenuGroupRequest(), true, Arrays.asList(createMenuProductRequest("후라이드", new BigDecimal(19000), 2L))
        );
        assertThatThrownBy(() -> menuService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.")
    @Test
    void createExpensiveMenu() {
        final MenuRequest expected = createMenuRequest(
            "후라이드+후라이드", new BigDecimal(33_000L),
			createMenuGroupRequest(), true, Arrays.asList(createMenuProductRequest("후라이드", new BigDecimal(19000), 2L))
        );
        assertThatThrownBy(() -> menuService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

	private MenuGroupRequest createMenuGroupRequest() {
		return new MenuGroupRequest(UUID.randomUUID(), "메뉴 그룹");
	}

	@DisplayName("메뉴는 특정 메뉴 그룹에 속해야 한다.")
    @NullSource
    @ParameterizedTest
    void create(UUID menuGroupId) {
        final MenuRequest expected = createMenuRequest(
            "후라이드+후라이드", new BigDecimal(19_000L), new MenuGroupRequest(menuGroupId, "메뉴 그룹"), true, Arrays.asList(createMenuProductRequest("후라이드", new BigDecimal(19000), 2L))
        );
        assertThatThrownBy(() -> menuService.create(expected))
            .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("메뉴의 이름이 올바르지 않으면 등록할 수 없다.")
    @ValueSource(strings = {"비속어", "욕설이 포함된 이름"})
    @NullSource
    @ParameterizedTest
    void create(final String name) {
        final MenuRequest expected = createMenuRequest(
            name, new BigDecimal(19_000L), new MenuGroupRequest(menuGroupId, "메뉴 그룹"), true, Arrays.asList(createMenuProductRequest("후라이드", new BigDecimal(19000), 2L))
        );
        assertThatThrownBy(() -> menuService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 가격을 변경할 수 있다.")
    @Test
    void changePrice() {
        final UUID menuId = menuRepository.save(menu(19_000L, menuProduct(product, 2L))).getId();
        final MenuRequest expected = changePriceRequest(16_000L);
        final MenuResponse actual = menuService.changePrice(menuId, expected);
        assertThat(actual.getPrice()).isEqualTo(expected.getPrice());
    }

    @DisplayName("메뉴의 가격이 올바르지 않으면 변경할 수 없다.")
    @ValueSource(strings = "-1000")
    @NullSource
    @ParameterizedTest
    void changePrice(final BigDecimal price) {
        final UUID menuId = menuRepository.save(menu(19_000L, menuProduct(product, 2L))).getId();
        final MenuRequest expected = changePriceRequest(price);
        assertThatThrownBy(() -> menuService.changePrice(menuId, expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.")
    @Test
    void changePriceToExpensive() {
        final UUID menuId = menuRepository.save(menu(19_000L, menuProduct(product, 2L))).getId();
        final MenuRequest expected = changePriceRequest(33_000L);
        assertThatThrownBy(() -> menuService.changePrice(menuId, expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴를 노출할 수 있다.")
    @Test
    void display() {
        final UUID menuId = menuRepository.save(menu(19_000L, false, menuProduct(product, 2L))).getId();
        final Menu actual = menuService.display(menuId);
        assertThat(actual.isDisplayed()).isTrue();
    }

    @DisplayName("메뉴의 가격이 메뉴에 속한 상품 금액의 합보다 높을 경우 메뉴를 노출할 수 없다.")
    @Test
    void displayExpensiveMenu() {
        final UUID menuId = menuRepository.save(menu(33_000L, false, menuProduct(product, 2L))).getId();
        assertThatThrownBy(() -> menuService.display(menuId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("메뉴를 숨길 수 있다.")
    @Test
    void hide() {
        final UUID menuId = menuRepository.save(menu(19_000L, true, menuProduct(product, 2L))).getId();
        final Menu actual = menuService.hide(menuId);
        assertThat(actual.isDisplayed()).isFalse();
    }

    @DisplayName("메뉴의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        menuRepository.save(menu(19_000L, true, menuProduct(product, 2L)));
        final List<Menu> actual = menuService.findAll();
        assertThat(actual).hasSize(1);
    }

    private MenuRequest createMenuRequest(
        final String name,
        final BigDecimal price,
        final MenuGroupRequest menuGroupRequest,
        final boolean displayed,
        final List<MenuProductRequest> menuProductRequests
    ) {
        final MenuRequest menu = new MenuRequest(
        	name
			, price
			, menuGroupRequest
			, displayed
			, menuProductRequests);

        return menu;
    }

    private static MenuProductRequest createMenuProductRequest(final String name, BigDecimal price, final long quantity) {
        MenuProductRequest menuProductRequest = new MenuProductRequest(new ProductRequest(name, price), quantity);

        return menuProductRequest;
    }

    private MenuRequest changePriceRequest(final long price) {
        return changePriceRequest(BigDecimal.valueOf(price));
    }

    private MenuRequest changePriceRequest(final BigDecimal price) {
    	return new MenuRequest(price);
    }
}
