package kitchenpos.order.eatinorder.application;

import java.util.UUID;
import kitchenpos.order.common.application.AcceptOrder;
import kitchenpos.order.common.application.CompleteOrder;
import kitchenpos.order.common.application.CreateOrder;
import kitchenpos.order.common.application.ServeOrder;
import kitchenpos.order.common.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class EatInOrderService implements CreateOrder, AcceptOrder, ServeOrder, CompleteOrder {

    @Override
    public Order create(Order order) {
        return null;
    }

    @Override
    public Order accept(UUID orderId) {
        return null;
    }

    @Override
    public Order serve(UUID orderId) {
        return null;
    }

    @Override
    public Order complete(UUID orderId) {
        return null;
    }

}
