package kitchenpos.takeoutorder.application;

import kitchenpos.eatinorder.order.domain.EatInOrder;
import kitchenpos.takeoutorder.domain.TakeoutOrder;

import java.util.List;
import java.util.UUID;

public class TakeoutOrderService {

    public TakeoutOrder create(final EatInOrder request) {
        return new TakeoutOrder();
    }

    public TakeoutOrder accept(final UUID orderId) {
        return new TakeoutOrder();
    }

    public TakeoutOrder serve(final UUID orderId) {
        return new TakeoutOrder();
    }

    public TakeoutOrder complete(final UUID orderId) {
        return new TakeoutOrder();
    }

    public List<TakeoutOrder> findAll() {
        return List.of(new TakeoutOrder());
    }
}
