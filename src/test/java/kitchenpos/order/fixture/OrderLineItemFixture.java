package kitchenpos.order.fixture;

import kitchenpos.order.common.domain.OrderLineItem;

import java.util.Random;
import java.util.UUID;

import static kitchenpos.menu.fixture.MenuFixture.menu;

public class OrderLineItemFixture {

    public static final UUID INVALID_ID = new UUID(0L, 0L);

    public static OrderLineItem orderLineItem() {
        final OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSeq(new Random().nextLong());
        orderLineItem.setMenu(menu());
        return orderLineItem;
    }
}
