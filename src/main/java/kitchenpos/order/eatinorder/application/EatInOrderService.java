package kitchenpos.order.eatinorder.application;

import java.util.UUID;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.order.common.application.AcceptOrder;
import kitchenpos.order.common.application.CompleteOrder;
import kitchenpos.order.common.application.CreateOrder;
import kitchenpos.order.common.application.ServeOrder;
import kitchenpos.order.common.domain.Order;
import kitchenpos.order.common.domain.OrderRepository;
import kitchenpos.order.eatinorder.domain.OrderTableRepository;
import org.springframework.stereotype.Service;

@Service
public class EatInOrderService implements CreateOrder, AcceptOrder, ServeOrder, CompleteOrder {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final OrderTableRepository orderTableRepository;

    public EatInOrderService(OrderRepository orderRepository, MenuRepository menuRepository,
        OrderTableRepository orderTableRepository) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
        this.orderTableRepository = orderTableRepository;
    }

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
