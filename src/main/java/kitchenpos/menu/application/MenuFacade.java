package kitchenpos.menu.application;

import kitchenpos.menu.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MenuFacade {
    private final MenuService menuService;

    public MenuFacade(MenuService menuService) {
        this.menuService = menuService;
    }

    public MenuInfo.Response create(final MenuCommand.MenuRequest request) {
        Menu menu = menuService.create(request.toEntity());
        return MenuInfo.Response.from(menu);
    }

    public MenuInfo.Response changePrice(final UUID menuId, final MenuCommand.MenuRequest request) {
        Menu menu = menuService.changePrice(menuId, request.toEntity());
        return MenuInfo.Response.from(menu);
    }

    public MenuInfo.Response display(final UUID menuId) {
        Menu menu = menuService.display(menuId);
        return MenuInfo.Response.from(menu);
    }

    public MenuInfo.Response hide(final UUID menuId) {
        Menu menu = menuService.hide(menuId);
        return MenuInfo.Response.from(menu);
    }

    public List<MenuInfo.Response> findAll() {
        return menuService.findAll().stream().map(MenuInfo.Response::from).collect(Collectors.toList());
    }

}
