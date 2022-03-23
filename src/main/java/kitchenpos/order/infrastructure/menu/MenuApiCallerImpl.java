package kitchenpos.order.infrastructure.menu;

import kitchenpos.menu.application.MenuFacade;
import kitchenpos.menu.application.MenuInfo;
import kitchenpos.order.domain.menu.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MenuApiCallerImpl implements MenuApiCaller {

    //원래는 api call을 하게 될것.. 여기선 menuFacade로 호출
    private final MenuFacade menuFacade;

    public MenuApiCallerImpl(MenuFacade menuFacade) {
        this.menuFacade = menuFacade;
    }

    @Override
    public Optional<Menu> findById(UUID menuId) {
        //원래는 api call을 했따면 response도 infra-menu 아래에 있을것이고 해당 응답으로 order-domain-menu의 menu도메인 모델을 만들것
        MenuInfo.Response response = menuFacade.find(menuId);

        MenuGroup menuGroupEntity = new MenuGroup(response.getMenuGroup().getId(), response.getMenuGroup().getName());
        List<MenuProduct> menuProductEntities = response.getMenuProducts().stream().map(value -> {
            Product product = new Product(value.getProduct().getId(), value.getProduct().getName(), value.getProduct().getPrice());
            return new MenuProduct(value.getSeq(), product, value.getQuantity(), value.getProductId());
        }).collect(Collectors.toList());

        Menu menu = new Menu(response.getId(), response.getName(), response.getPrice(), menuGroupEntity, response.isDisplayed(), menuProductEntities, response.getMenuGroupId());

        return Optional.of(menu);
    }

    @Override
    public List<Menu> findAllByIdIn(List<UUID> ids) {
        List<MenuInfo.Response> responses = menuFacade.findAll();

        return responses.stream().map(response -> {
            MenuGroup menuGroupEntity = new MenuGroup(response.getMenuGroup().getId(), response.getMenuGroup().getName());
            List<MenuProduct> menuProductEntities = response.getMenuProducts().stream().map(value -> {
                Product product = new Product(value.getProduct().getId(), value.getProduct().getName(), value.getProduct().getPrice());
                return new MenuProduct(value.getSeq(), product, value.getQuantity(), value.getProductId());
            }).collect(Collectors.toList());

            return new Menu(response.getId(), response.getName(), response.getPrice(), menuGroupEntity, response.isDisplayed(), menuProductEntities, response.getMenuGroupId());
        }).collect(Collectors.toList());
    }

}
