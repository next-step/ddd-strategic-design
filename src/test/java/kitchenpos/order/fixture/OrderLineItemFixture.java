package kitchenpos.order.fixture;

import kitchenpos.domain.*;
import kitchenpos.order.common.domain.OrderLineItem;

import java.util.Random;

public class OrderLineItemFixture {

    public static OrderLineItem orderLineItem() {
        final OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSeq(new Random().nextLong());
        orderLineItem.setMenu(menu());
        return orderLineItem;
    }
}
