package kitchenpos.order;

import kitchenpos.menu.InMemoryMenuDao;
import kitchenpos.menu.repository.MenuRepository;
import kitchenpos.order.repository.OrderRepository;
import kitchenpos.order.repository.OrderLineItemRepository;
import kitchenpos.order.repository.OrderTableRepository;
import kitchenpos.order.model.Order;
import kitchenpos.order.model.OrderStatus;
import kitchenpos.order.bo.OrderBo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.List;

import static kitchenpos.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderBoTest {
    private final MenuRepository menuRepository = new InMemoryMenuDao();
    private final OrderRepository orderRepository = new InMemoryOrderDao();
    private final OrderLineItemRepository orderLineItemRepository = new InMemoryOrderLineItemDao();
    private final OrderTableRepository orderTableRepository = new InMemoryOrderTableDao();

    private OrderBo orderBo;

    @BeforeEach
    void setUp() {
        orderBo = new OrderBo(menuRepository, orderRepository, orderLineItemRepository, orderTableRepository);
        menuRepository.save(twoFriedChickens());
        orderTableRepository.save(table1());
    }

    @DisplayName("1 개 이상의 등록된 메뉴로 주문을 등록할 수 있다.")
    @Test
    void create() {
        // given
        final Order expected = orderForTable1();

        // when
        final Order actual = orderBo.create(expected);

        // then
        assertOrder(expected, actual);
    }

    @DisplayName("빈 테이블에는 주문을 등록할 수 없다.")
    @Test
    void createFromEmptyTable() {
        // given
        orderTableRepository.save(emptyTable1());

        final Order expected = orderForTable1();

        // when
        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> orderBo.create(expected));
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void list() {
        // given
        final Order orderForTable1 = orderRepository.save(orderForTable1());

        // when
        final List<Order> actual = orderBo.list();

        // then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(Arrays.asList(orderForTable1));
    }

    @DisplayName("주문 상태를 변경할 수 있다.")
    @ParameterizedTest
    @EnumSource(value = OrderStatus.class)
    void changeOrderStatus(final OrderStatus orderStatus) {
        // given
        final Long orderId = orderRepository.save(orderForTable1()).getId();

        final Order expected = new Order();
        expected.setOrderStatus(orderStatus.name());

        // when
        final Order actual = orderBo.changeOrderStatus(orderId, expected);

        // then
        assertThat(actual).isNotNull();
        assertAll(
                () -> assertThat(actual.getId()).isEqualTo(orderId),
                () -> assertThat(actual.getOrderStatus()).isEqualTo(orderStatus.name())
        );
    }

    @DisplayName("주문 상태가 계산 완료인 경우 변경할 수 없다.")
    @ParameterizedTest
    @EnumSource(value = OrderStatus.class)
    void changeOrderStatusOfCalculatedOrder(final OrderStatus orderStatus) {
        // given
        final Order orderForTable1 = orderForTable1();
        orderForTable1.setOrderStatus(OrderStatus.COMPLETION.name());
        final Long orderId = orderRepository.save(orderForTable1).getId();

        final Order expected = new Order();
        expected.setOrderStatus(orderStatus.name());

        // when
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> orderBo.changeOrderStatus(orderId, expected))
        ;
    }

    private void assertOrder(final Order expected, final Order actual) {
        assertThat(actual).isNotNull();
        assertAll(
                () -> assertThat(actual.getOrderTableId()).isEqualTo(expected.getOrderTableId()),
                () -> assertThat(actual.getOrderStatus()).isEqualTo(expected.getOrderStatus()),
                () -> assertThat(actual.getOrderLineItems())
                        .containsExactlyInAnyOrderElementsOf(expected.getOrderLineItems())
        );
    }
}
