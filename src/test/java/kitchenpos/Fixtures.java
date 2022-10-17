package kitchenpos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import kitchenpos.delivery_order.domain.DeliveryOrder;
import kitchenpos.delivery_order.domain.DeliveryOrderLineItem;
import kitchenpos.delivery_order.domain.DeliveryOrderStatus;
import kitchenpos.delivery_order.domain.DeliveryOrderTable;
import kitchenpos.delivery_order.domain.DeliveryOrderType;
import kitchenpos.eat_in_order.domain.EatInOrder;
import kitchenpos.eat_in_order.domain.EatInOrderLineItem;
import kitchenpos.eat_in_order.domain.EatInOrderStatus;
import kitchenpos.eat_in_order.domain.EatInOrderTable;
import kitchenpos.eat_in_order.domain.EatInOrderType;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.product.domain.Product;
import kitchenpos.takeout_order.domain.TakeoutOrder;
import kitchenpos.takeout_order.domain.TakeoutOrderLineItem;
import kitchenpos.takeout_order.domain.TakeoutOrderStatus;
import kitchenpos.takeout_order.domain.TakeoutOrderTable;
import kitchenpos.takeout_order.domain.TakeoutOrderType;

public class Fixtures {
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

    public static DeliveryOrder deliveryOrder(final DeliveryOrderStatus status, final String deliveryAddress) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(UUID.randomUUID());
        deliveryOrder.setType(DeliveryOrderType.DELIVERY);
        deliveryOrder.setStatus(status);
        deliveryOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        deliveryOrder.setOrderLineItems(Arrays.asList(deliveryOrderLineItem()));
        deliveryOrder.setDeliveryAddress(deliveryAddress);
        return deliveryOrder;
    }

    public static DeliveryOrder deliveryOrder(final DeliveryOrderStatus status) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(UUID.randomUUID());
        deliveryOrder.setType(DeliveryOrderType.TAKEOUT);
        deliveryOrder.setStatus(status);
        deliveryOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        deliveryOrder.setOrderLineItems(Arrays.asList(deliveryOrderLineItem()));
        return deliveryOrder;
    }

    public static DeliveryOrder deliveryOrder(final DeliveryOrderStatus status, final DeliveryOrderTable deliveryOrderTable) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(UUID.randomUUID());
        deliveryOrder.setType(DeliveryOrderType.EAT_IN);
        deliveryOrder.setStatus(status);
        deliveryOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        deliveryOrder.setOrderLineItems(Arrays.asList(deliveryOrderLineItem()));
        deliveryOrder.setOrderTable(deliveryOrderTable);
        return deliveryOrder;
    }

    public static DeliveryOrderLineItem deliveryOrderLineItem() {
        final DeliveryOrderLineItem deliveryOrderLineItem = new DeliveryOrderLineItem();
        deliveryOrderLineItem.setSeq(new Random().nextLong());
        deliveryOrderLineItem.setMenu(menu());
        return deliveryOrderLineItem;
    }

    public static DeliveryOrderTable deliveryOrderTable() {
        return deliveryOrderTable(false, 0);
    }

    public static DeliveryOrderTable deliveryOrderTable(final boolean occupied, final int numberOfGuests) {
        final DeliveryOrderTable deliveryOrderTable = new DeliveryOrderTable();
        deliveryOrderTable.setId(UUID.randomUUID());
        deliveryOrderTable.setName("1번");
        deliveryOrderTable.setNumberOfGuests(numberOfGuests);
        deliveryOrderTable.setOccupied(occupied);
        return deliveryOrderTable;
    }

    public static EatInOrder eatInOrder(final EatInOrderStatus status, final String deliveryAddress) {
        final EatInOrder eatInOrder = new EatInOrder();
        eatInOrder.setId(UUID.randomUUID());
        eatInOrder.setType(EatInOrderType.DELIVERY);
        eatInOrder.setStatus(status);
        eatInOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        eatInOrder.setOrderLineItems(Arrays.asList(eatInOrderLineItem()));
        eatInOrder.setDeliveryAddress(deliveryAddress);
        return eatInOrder;
    }

    public static EatInOrder eatInOrder(final EatInOrderStatus status) {
        final EatInOrder eatInOrder = new EatInOrder();
        eatInOrder.setId(UUID.randomUUID());
        eatInOrder.setType(EatInOrderType.TAKEOUT);
        eatInOrder.setStatus(status);
        eatInOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        eatInOrder.setOrderLineItems(Arrays.asList(eatInOrderLineItem()));
        return eatInOrder;
    }

    public static EatInOrder eatInOrder(final EatInOrderStatus status, final EatInOrderTable eatInOrderTable) {
        final EatInOrder eatInOrder = new EatInOrder();
        eatInOrder.setId(UUID.randomUUID());
        eatInOrder.setType(EatInOrderType.EAT_IN);
        eatInOrder.setStatus(status);
        eatInOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        eatInOrder.setOrderLineItems(Arrays.asList(eatInOrderLineItem()));
        eatInOrder.setOrderTable(eatInOrderTable);
        return eatInOrder;
    }

    public static EatInOrderLineItem eatInOrderLineItem() {
        final EatInOrderLineItem eatInOrderLineItem = new EatInOrderLineItem();
        eatInOrderLineItem.setSeq(new Random().nextLong());
        eatInOrderLineItem.setMenu(menu());
        return eatInOrderLineItem;
    }

    public static EatInOrderTable eatInOrderTable() {
        return eatInOrderTable(false, 0);
    }

    public static EatInOrderTable eatInOrderTable(final boolean occupied, final int numberOfGuests) {
        final EatInOrderTable eatInOrderTable = new EatInOrderTable();
        eatInOrderTable.setId(UUID.randomUUID());
        eatInOrderTable.setName("1번");
        eatInOrderTable.setNumberOfGuests(numberOfGuests);
        eatInOrderTable.setOccupied(occupied);
        return eatInOrderTable;
    }

    public static TakeoutOrder takeoutOrder(final TakeoutOrderStatus status, final String deliveryAddress) {
        final TakeoutOrder takeoutOrder = new TakeoutOrder();
        takeoutOrder.setId(UUID.randomUUID());
        takeoutOrder.setType(TakeoutOrderType.DELIVERY);
        takeoutOrder.setStatus(status);
        takeoutOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        takeoutOrder.setOrderLineItems(Arrays.asList(takeoutOrderLineItem()));
        takeoutOrder.setDeliveryAddress(deliveryAddress);
        return takeoutOrder;
    }

    public static TakeoutOrder takeoutOrder(final TakeoutOrderStatus status) {
        final TakeoutOrder takeoutOrder = new TakeoutOrder();
        takeoutOrder.setId(UUID.randomUUID());
        takeoutOrder.setType(TakeoutOrderType.TAKEOUT);
        takeoutOrder.setStatus(status);
        takeoutOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        takeoutOrder.setOrderLineItems(Arrays.asList(takeoutOrderLineItem()));
        return takeoutOrder;
    }

    public static TakeoutOrder takeoutOrder(final TakeoutOrderStatus status, final TakeoutOrderTable takeoutOrderTable) {
        final TakeoutOrder takeoutOrder = new TakeoutOrder();
        takeoutOrder.setId(UUID.randomUUID());
        takeoutOrder.setType(TakeoutOrderType.EAT_IN);
        takeoutOrder.setStatus(status);
        takeoutOrder.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        takeoutOrder.setOrderLineItems(Arrays.asList(takeoutOrderLineItem()));
        takeoutOrder.setOrderTable(takeoutOrderTable);
        return takeoutOrder;
    }

    public static TakeoutOrderLineItem takeoutOrderLineItem() {
        final TakeoutOrderLineItem takeoutOrderLineItem = new TakeoutOrderLineItem();
        takeoutOrderLineItem.setSeq(new Random().nextLong());
        takeoutOrderLineItem.setMenu(menu());
        return takeoutOrderLineItem;
    }

    public static TakeoutOrderTable takeoutOrderTable() {
        return takeoutOrderTable(false, 0);
    }

    public static TakeoutOrderTable takeoutOrderTable(final boolean occupied, final int numberOfGuests) {
        final TakeoutOrderTable takeoutOrderTable = new TakeoutOrderTable();
        takeoutOrderTable.setId(UUID.randomUUID());
        takeoutOrderTable.setName("1번");
        takeoutOrderTable.setNumberOfGuests(numberOfGuests);
        takeoutOrderTable.setOccupied(occupied);
        return takeoutOrderTable;
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
