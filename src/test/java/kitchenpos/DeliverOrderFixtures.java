package kitchenpos;


import kitchenpos.deliveryorder.domain.DeliveryOrder;
import kitchenpos.deliveryorder.domain.DeliveryOrderLineItem;
import kitchenpos.deliveryorder.domain.OrderStatus;
import kitchenpos.deliveryorder.domain.OrderType;
import kitchenpos.menu.menu.domain.Menu;
import kitchenpos.menu.menu.domain.MenuProduct;
import kitchenpos.menu.menugroup.domain.MenuGroup;
import kitchenpos.product.domain.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class DeliverOrderFixtures {

    public static DeliveryOrder order(final OrderStatus status, final String deliveryAddress) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(UUID.randomUUID());
        deliveryOrder.setType(OrderType.DELIVERY);
        deliveryOrder.setStatus(status);
        deliveryOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        deliveryOrder.setOrderLineItems(Arrays.asList(orderLineItem()));
        deliveryOrder.setDeliveryAddress(deliveryAddress);
        return deliveryOrder;
    }

    public static final UUID INVALID_ID = new UUID(0L, 0L);

    public static Menu menu() {
        return menu(19_000L, true, menuProduct());
    }

    public static Menu menu(final long price, final MenuProduct... menuProducts) {
        return menu(price, false, menuProducts);
    }

    public static Menu menu(final long price, final boolean displayed, final MenuProduct... menuProducts) {
        final Menu menu = new Menu();
        menu.setId(UUID.randomUUID());
        menu.setName("후라이드+후라이드");
        menu.setPrice(BigDecimal.valueOf(price));
        menu.setMenuGroup(menuGroup());
        menu.setDisplayed(displayed);
        menu.setMenuProducts(Arrays.asList(menuProducts));
        return menu;
    }

    public static MenuGroup menuGroup() {
        return menuGroup("두마리메뉴");
    }

    public static MenuGroup menuGroup(final String name) {
        final MenuGroup menuGroup = new MenuGroup();
        menuGroup.setId(UUID.randomUUID());
        menuGroup.setName(name);
        return menuGroup;
    }

    public static MenuProduct menuProduct() {
        final MenuProduct menuProduct = new MenuProduct();
        menuProduct.setSeq(new Random().nextLong());
        menuProduct.setProduct(product());
        menuProduct.setQuantity(2L);
        return menuProduct;
    }

    public static MenuProduct menuProduct(final Product product, final long quantity) {
        final MenuProduct menuProduct = new MenuProduct();
        menuProduct.setSeq(new Random().nextLong());
        menuProduct.setProduct(product);
        menuProduct.setQuantity(quantity);
        return menuProduct;
    }

    public static DeliveryOrderLineItem orderLineItem() {
        final DeliveryOrderLineItem deliveryOrderLineItem = new DeliveryOrderLineItem();
        deliveryOrderLineItem.setSeq(new Random().nextLong());
        deliveryOrderLineItem.setMenu(menu());
        return deliveryOrderLineItem;
    }

    public static Product product() {
        return product("후라이드", 16_000L);
    }

    public static Product product(final String name, final long price) {
        final Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName(name);
        product.setPrice(BigDecimal.valueOf(price));
        return product;
    }

}
