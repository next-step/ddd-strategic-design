package kitchenpos.eatinorder.application;

import kitchenpos.Fixtures;
import kitchenpos.application.InMemoryOrderRepository;
import kitchenpos.application.InMemoryOrderTableRepository;
import kitchenpos.domain.OrderRepository;
import kitchenpos.domain.OrderStatus;
import kitchenpos.order.eatinorder.application.RestaurantTableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.UUID;

import static kitchenpos.Fixtures.eatInOrder;
import static kitchenpos.Fixtures.restaurantTable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class RestaurantTableServiceTest {
    private OrderTableRepository orderTableRepository;
    private OrderRepository orderRepository;
    private RestaurantTableService restaurantTableService;

    @BeforeEach
    void setUp() {
        orderTableRepository = new InMemoryOrderTableRepository();
        orderRepository = new InMemoryOrderRepository();
        restaurantTableService = new RestaurantTableService(orderTableRepository, orderRepository);
    }

    @DisplayName("주문 테이블을 등록할 수 있다.")
    @Test
    void create() {
        final OrderTable expected = createOrderTableRequest("1번");
        final OrderTable actual = restaurantTableService.create(expected);
        assertThat(actual).isNotNull();
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getName()).isEqualTo(expected.getName()),
            () -> assertThat(actual.getNumberOfGuests()).isZero(),
            () -> assertThat(actual.isOccupied()).isFalse()
        );
    }

    @DisplayName("주문 테이블의 이름이 올바르지 않으면 등록할 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void create(final String name) {
        final OrderTable expected = createOrderTableRequest(name);
        assertThatThrownBy(() -> restaurantTableService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈 테이블을 해지할 수 있다.")
    @Test
    void sit() {
        final UUID orderTableId = orderTableRepository.save(Fixtures.restaurantTable(false, 0)).getId();
        final OrderTable actual = restaurantTableService.sit(orderTableId);
        assertThat(actual.isOccupied()).isTrue();
    }

    @DisplayName("빈 테이블로 설정할 수 있다.")
    @Test
    void clear() {
        final UUID orderTableId = orderTableRepository.save(Fixtures.restaurantTable(true, 4)).getId();
        final OrderTable actual = restaurantTableService.clear(orderTableId);
        assertAll(
            () -> assertThat(actual.getNumberOfGuests()).isZero(),
            () -> assertThat(actual.isOccupied()).isFalse()
        );
    }

    @DisplayName("완료되지 않은 주문이 있는 주문 테이블은 빈 테이블로 설정할 수 없다.")
    @Test
    void clearWithUncompletedOrders() {
        final OrderTable orderTable = orderTableRepository.save(Fixtures.restaurantTable(true, 4));
        final UUID orderTableId = orderTable.getId();
        orderRepository.save(order(OrderStatus.ACCEPTED, orderTable));
        assertThatThrownBy(() -> restaurantTableService.clear(orderTableId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("방문한 손님 수를 변경할 수 있다.")
    @Test
    void changeNumberOfGuests() {
        final UUID orderTableId = orderTableRepository.save(Fixtures.restaurantTable(true, 0)).getId();
        final OrderTable expected = changeNumberOfGuestsRequest(4);
        final OrderTable actual = restaurantTableService.changeNumberOfGuests(orderTableId, expected);
        assertThat(actual.getNumberOfGuests()).isEqualTo(4);
    }

    @DisplayName("방문한 손님 수가 올바르지 않으면 변경할 수 없다.")
    @ValueSource(ints = -1)
    @ParameterizedTest
    void changeNumberOfGuests(final int numberOfGuests) {
        final UUID orderTableId = orderTableRepository.save(Fixtures.restaurantTable(true, 0)).getId();
        final OrderTable expected = changeNumberOfGuestsRequest(numberOfGuests);
        assertThatThrownBy(() -> restaurantTableService.changeNumberOfGuests(orderTableId, expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈 테이블은 방문한 손님 수를 변경할 수 없다.")
    @Test
    void changeNumberOfGuestsInEmptyTable() {
        final UUID orderTableId = orderTableRepository.save(Fixtures.restaurantTable(false, 0)).getId();
        final OrderTable expected = changeNumberOfGuestsRequest(4);
        assertThatThrownBy(() -> restaurantTableService.changeNumberOfGuests(orderTableId, expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문 테이블의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        orderTableRepository.save(restaurantTable());
        final List<OrderTable> actual = restaurantTableService.findAll();
        assertThat(actual).hasSize(1);
    }

    private OrderTable createOrderTableRequest(final String name) {
        final OrderTable orderTable = new OrderTable();
        orderTable.setName(name);
        return orderTable;
    }

    private OrderTable changeNumberOfGuestsRequest(final int numberOfGuests) {
        final OrderTable orderTable = new OrderTable();
        orderTable.setNumberOfGuests(numberOfGuests);
        return orderTable;
    }
}
