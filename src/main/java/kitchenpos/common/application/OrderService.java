package kitchenpos.common.application;

import kitchenpos.delivery.application.DeliveryService;
import kitchenpos.eatin.application.EatInService;
import kitchenpos.eatin.domain.Order;
import kitchenpos.common.code.OrderType;
import kitchenpos.eatin.domain.OrderRepository;
import kitchenpos.takeout.application.TakeOutService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderService {
    private final EatInService eatInService;
    private final DeliveryService deliveryService;
    private final TakeOutService takeOutService;
    private final OrderRepository orderRepository;

    public OrderService(
            EatInService eatInService,
            DeliveryService deliveryService,
            TakeOutService takeOutService,
            OrderRepository orderRepository
        ) {
        this.eatInService = eatInService;
        this.deliveryService = deliveryService;
        this.takeOutService = takeOutService;
        this.orderRepository = orderRepository;
    }


    public Order create(Order request) {
        if (OrderType.DELIVERY.equals(request.getType())) {
            return deliveryService.create(request);
        }

        if (OrderType.EAT_IN.equals(request.getType())) {
            return eatInService.create(request);
        }

        if (OrderType.TAKEOUT.equals(request.getType())) {
            return takeOutService.create(request);
        }

        throw new IllegalArgumentException("지원하지 않는 주문 타입");
    }

    public Order accept(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);

        if (order.getType().equals(OrderType.DELIVERY)) {
            return deliveryService.accept(orderId);
        }

        if (order.getType().equals(OrderType.EAT_IN)) {
            return eatInService.accept(orderId);
        }

        if (order.getType().equals(OrderType.TAKEOUT)) {
            return takeOutService.accept(orderId);
        }

        throw new IllegalArgumentException("지원하지 않는 주문 타입");
    }

    public Order serve(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);

        if (order.getType().equals(OrderType.DELIVERY)) {
            return deliveryService.serve(orderId);
        }

        if (order.getType().equals(OrderType.EAT_IN)) {
            return eatInService.serve(orderId);
        }

        if (order.getType().equals(OrderType.TAKEOUT)) {
            return takeOutService.serve(orderId);
        }

        throw new IllegalArgumentException("지원하지 않는 주문 타입");
    }

    public Order complete(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(NoSuchElementException::new);

        if (order.getType().equals(OrderType.DELIVERY)) {
            return deliveryService.complete(orderId);
        }

        if (order.getType().equals(OrderType.EAT_IN)) {
            return eatInService.complete(orderId);
        }

        if (order.getType().equals(OrderType.TAKEOUT)) {
            return takeOutService.complete(orderId);
        }

        throw new IllegalArgumentException("지원하지 않는 주문 타입");
    }
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
