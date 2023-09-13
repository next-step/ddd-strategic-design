package kitchenpos.eatin_orders;

import kitchenpos.eatin_orders.domain.EatInOrder;
import kitchenpos.eatin_orders.domain.EatInOrderLineItem;
import kitchenpos.eatin_orders.domain.OrderStatus;
import kitchenpos.eatin_orders.domain.OrderTable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import static kitchenpos.Fixtures.*;

public class EatInOrderFixtures {

    public static EatInOrder order(final OrderStatus status, final OrderTable orderTable) {
        final EatInOrder order = new EatInOrder();
        order.setId(UUID.randomUUID());
        order.setStatus(status);
        order.setOrderDateTime(LocalDateTime.of(2020, 1, 1, 12, 0));
        order.setOrderLineItems(Arrays.asList(eatInOrderLineItem()));
        order.setOrderTable(orderTable);
        return order;
    }

    public static EatInOrderLineItem eatInOrderLineItem() {
        final EatInOrderLineItem orderLineItem = new EatInOrderLineItem();
        orderLineItem.setSeq(new Random().nextLong());
        orderLineItem.setMenu(menu());
        return orderLineItem;
    }

    public static OrderTable orderTable() {
        return orderTable(false, 0);
    }

    public static OrderTable orderTable(final boolean occupied, final int numberOfGuests) {
        final OrderTable orderTable = new OrderTable();
        orderTable.setId(UUID.randomUUID());
        orderTable.setName("1번");
        orderTable.setNumberOfGuests(numberOfGuests);
        orderTable.setOccupied(occupied);
        return orderTable;
    }
}
