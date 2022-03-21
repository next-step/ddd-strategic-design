package kitchenpos.menu.interfaces;

import kitchenpos.menu.domain.MenuCommand;

import java.util.List;
import java.util.stream.Collectors;

public class MenuDtoMapper {

    private MenuDtoMapper() {}

    public static MenuCommand.MenuRequest from(MenuDto.MenuRequest request) {
        MenuCommand.MenuGroupRequest menuGroupRequest = new MenuCommand.MenuGroupRequest(request.getMenuGroup().getId(), request.getMenuGroup().getName());
        List<MenuCommand.MenuProductRequest> menuProductRequests = request.getMenuProducts().stream().map(value -> {
            MenuCommand.ProductRequest productRequest = new MenuCommand.ProductRequest(value.getProduct().getId(), value.getProduct().getName(), value.getProduct().getPrice());
            return new MenuCommand.MenuProductRequest(value.getSeq(), productRequest, value.getQuantity(), value.getProductId());
        }).collect(Collectors.toList());

        return new MenuCommand.MenuRequest(request.getId(), request.getName(), request.getPrice(), menuGroupRequest, request.isDisplayed(), menuProductRequests, request.getMenuGroupId());
    }
}
