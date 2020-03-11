package kitchenpos;

import kitchenpos.menu.doamin.Menu;
import kitchenpos.menu.doamin.MenuGroup;
import kitchenpos.menu.doamin.MenuProduct;
import kitchenpos.order.doamin.Order;
import kitchenpos.order.doamin.OrderLineItem;
import kitchenpos.order.doamin.OrderStatus;
import kitchenpos.product.domain.Product;
import kitchenpos.table.domain.OrderTable;
import kitchenpos.table.domain.TableGroup;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Fixtures {
    public static final Long TWO_FRIED_CHICKENS_ID = 1L;
    public static final Long TWO_CHICKENS_ID = 1L;
    public static final Long FRIED_CHICKEN_ID = 1L;
    public static final Long SEASONED_CHICKEN_ID = 2L;
    public static final Long ORDER_FOR_TABLE1_ID = 1L;
    public static final Long TABLE1_ID = 1L;
    public static final Long TABLE2_ID = 2L;
    public static final Long TABLE1_AND_TABLE2_ID = 1L;

    public static Menu twoFriedChickens() {
        final Menu menu = new Menu();
        menu.setId(TWO_FRIED_CHICKENS_ID);
        menu.setName("후라이드+후라이드");
        menu.setPrice(BigDecimal.valueOf(19_000L));
        menu.setMenuGroupId(TWO_CHICKENS_ID);
        menu.setMenuProducts(Arrays.asList(menuProduct()));
        return menu;
    }

    public static MenuGroup twoChickens() {
        final MenuGroup menuGroup = new MenuGroup();
        menuGroup.setId(TWO_CHICKENS_ID);
        menuGroup.setName("두마리메뉴");
        return menuGroup;
    }

    public static Product friedChicken() {
        final Product product = new Product();
        product.setId(FRIED_CHICKEN_ID);
        product.setName("후라이드");
        product.setPrice(BigDecimal.valueOf(16_000L));
        return product;
    }

    public static Product seasonedChicken() {
        final Product product = new Product();
        product.setId(SEASONED_CHICKEN_ID);
        product.setName("양념치킨");
        product.setPrice(BigDecimal.valueOf(16_000L));
        return product;
    }

    public static Order orderForTable1() {
        final Order order = new Order();
        order.setId(ORDER_FOR_TABLE1_ID);
        order.setOrderTableId(TABLE1_ID);
        order.setOrderStatus(OrderStatus.COOKING.name());
        order.setOrderedTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        order.setOrderLineItems(Arrays.asList(orderLineItem()));
        return order;
    }

    public static OrderTable table1() {
        final OrderTable orderTable = new OrderTable();
        orderTable.setId(TABLE1_ID);
        orderTable.setTableGroupId(null);
        orderTable.setNumberOfGuests(0);
        orderTable.setEmpty(false);
        return orderTable;
    }

    public static OrderTable table2() {
        final OrderTable orderTable = new OrderTable();
        orderTable.setId(TABLE2_ID);
        orderTable.setTableGroupId(null);
        orderTable.setNumberOfGuests(0);
        orderTable.setEmpty(false);
        return orderTable;
    }

    public static OrderTable emptyTable1() {
        final OrderTable orderTable = table1();
        orderTable.setEmpty(true);
        return orderTable;
    }

    public static OrderTable emptyTable2() {
        final OrderTable orderTable = table2();
        orderTable.setEmpty(true);
        return orderTable;
    }

    public static OrderTable groupedTable1() {
        final OrderTable orderTable = table1();
        orderTable.setTableGroupId(TABLE1_AND_TABLE2_ID);
        return orderTable;
    }

    public static OrderTable groupedTable2() {
        final OrderTable orderTable = table2();
        orderTable.setTableGroupId(TABLE1_AND_TABLE2_ID);
        return orderTable;
    }

    public static TableGroup table1AndTable2() {
        final TableGroup tableGroup = new TableGroup();
        tableGroup.setId(TABLE1_AND_TABLE2_ID);
        tableGroup.setCreatedDate(LocalDateTime.of(2020, 1, 1, 12, 0));
        tableGroup.setOrderTables(Arrays.asList(groupedTable1(), groupedTable2()));
        return tableGroup;
    }

    private static MenuProduct menuProduct() {
        final MenuProduct menuProduct = new MenuProduct();
        menuProduct.setMenuId(TWO_FRIED_CHICKENS_ID);
        menuProduct.setProductId(FRIED_CHICKEN_ID);
        menuProduct.setQuantity(2);
        return menuProduct;
    }

    private static OrderLineItem orderLineItem() {
        final OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setOrderId(ORDER_FOR_TABLE1_ID);
        orderLineItem.setMenuId(TWO_FRIED_CHICKENS_ID);
        orderLineItem.setQuantity(2);
        return orderLineItem;
    }
}
