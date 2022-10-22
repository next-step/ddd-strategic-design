package kitchenpos.delivery.application;

import kitchenpos.common.infra.FakeKitchenridersClient;
import kitchenpos.menu.infra.InMemoryMenuRepository;
import kitchenpos.common.infra.InMemoryOrderRepository;
import kitchenpos.eatin.domain.Order;
import kitchenpos.eatin.domain.OrderRepository;
import kitchenpos.eatin.domain.OrderStatus;
import kitchenpos.menu.domain.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.UUID;

import static kitchenpos.Fixtures.order;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeliveryServiceTest {
    private OrderRepository orderRepository;
    private MenuRepository menuRepository;
    private FakeKitchenridersClient kitchenridersClient;
    private DeliveryService deliveryService;

    @BeforeEach
    void setUp() {
        orderRepository = new InMemoryOrderRepository();
        menuRepository = new InMemoryMenuRepository();
        kitchenridersClient = new FakeKitchenridersClient();
        deliveryService = new DeliveryService(orderRepository, menuRepository, kitchenridersClient);
    }

    @DisplayName("주문을 배달한다.")
    @Test
    void startDelivery() {
        final UUID orderId = orderRepository.save(order(OrderStatus.SERVED, "서울시 송파구 위례성대로 2")).getId();
        final Order actual = deliveryService.startDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.DELIVERING);
    }

    @DisplayName("배달 주문만 배달할 수 있다.")
    @Test
    void startDeliveryWithoutDeliveryOrder() {
        final UUID orderId = orderRepository.save(order(OrderStatus.SERVED)).getId();
        assertThatThrownBy(() -> deliveryService.startDelivery(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("서빙된 주문만 배달할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "SERVED", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void startDelivery(final OrderStatus status) {
        final UUID orderId = orderRepository.save(order(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> deliveryService.startDelivery(orderId))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문을 배달 완료한다.")
    @Test
    void completeDelivery() {
        final UUID orderId = orderRepository.save(order(OrderStatus.DELIVERING, "서울시 송파구 위례성대로 2")).getId();
        final Order actual = deliveryService.completeDelivery(orderId);
        assertThat(actual.getStatus()).isEqualTo(OrderStatus.DELIVERED);
    }

    @DisplayName("배달 중인 주문만 배달 완료할 수 있다.")
    @EnumSource(value = OrderStatus.class, names = "DELIVERING", mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest
    void completeDelivery(final OrderStatus status) {
        final UUID orderId = orderRepository.save(order(status, "서울시 송파구 위례성대로 2")).getId();
        assertThatThrownBy(() -> deliveryService.completeDelivery(orderId))
                .isInstanceOf(IllegalStateException.class);
    }
}