package kitchenpos.order.deliveryorder.application;

import java.util.List;
import java.util.UUID;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.order.common.application.AcceptOrder;
import kitchenpos.order.common.application.CompleteOrder;
import kitchenpos.order.common.application.ServeOrder;
import kitchenpos.order.common.domain.Order;
import kitchenpos.order.common.domain.OrderRepository;
import kitchenpos.order.deliveryorder.infra.KitchenridersClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryOrderService
    implements AcceptOrder, ServeOrder, StartDelivery, CompleteDelivery, CompleteOrder {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final KitchenridersClient kitchenridersClient;

    public DeliveryOrderService(OrderRepository orderRepository, MenuRepository menuRepository,
        KitchenridersClient kitchenridersClient) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
        this.kitchenridersClient = kitchenridersClient;
    }

    @Transactional
    public Order create(final Order request) {
        return null;
    }

    @Transactional
    @Override
    public Order accept(final UUID orderId) {
        return null;
    }

    @Transactional
    @Override
    public Order serve(final UUID orderId) {
        return null;
    }

    @Transactional
    @Override
    public Order startDelivery(final UUID orderId) {
        return null;
    }

    @Transactional
    @Override
    public Order completeDelivery(final UUID orderId) {
        return null;
    }

    @Transactional
    @Override
    public Order complete(final UUID orderId) {
        return null;
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
