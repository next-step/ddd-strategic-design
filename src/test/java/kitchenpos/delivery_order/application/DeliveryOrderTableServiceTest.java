package kitchenpos.delivery_order.application;

import static kitchenpos.Fixtures.deliveryOrder;
import static kitchenpos.Fixtures.deliveryOrderTable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.UUID;
import kitchenpos.delivery_order.InMemoryDeliveryOrderRepository;
import kitchenpos.delivery_order.InMemoryDeliveryOrderTableRepository;
import kitchenpos.delivery_order.domain.DeliveryOrderRepository;
import kitchenpos.delivery_order.domain.DeliveryOrderStatus;
import kitchenpos.delivery_order.domain.DeliveryOrderTable;
import kitchenpos.delivery_order.domain.DeliveryOrderTableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class DeliveryOrderTableServiceTest {
    private DeliveryOrderTableRepository deliveryOrderTableRepository;
    private DeliveryOrderRepository deliveryOrderRepository;
    private DeliveryOrderTableService deliveryOrderTableService;

    @BeforeEach
    void setUp() {
        deliveryOrderTableRepository = new InMemoryDeliveryOrderTableRepository();
        deliveryOrderRepository = new InMemoryDeliveryOrderRepository();
        deliveryOrderTableService = new DeliveryOrderTableService(deliveryOrderTableRepository,
            deliveryOrderRepository);
    }

    @DisplayName("주문 테이블을 등록할 수 있다.")
    @Test
    void create() {
        final DeliveryOrderTable expected = createOrderTableRequest("1번");
        final DeliveryOrderTable actual = deliveryOrderTableService.create(expected);
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
        final DeliveryOrderTable expected = createOrderTableRequest(name);
        assertThatThrownBy(() -> deliveryOrderTableService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈 테이블을 해지할 수 있다.")
    @Test
    void sit() {
        final UUID orderTableId = deliveryOrderTableRepository.save(deliveryOrderTable(false, 0)).getId();
        final DeliveryOrderTable actual = deliveryOrderTableService.sit(orderTableId);
        assertThat(actual.isOccupied()).isTrue();
    }

    @DisplayName("빈 테이블로 설정할 수 있다.")
    @Test
    void clear() {
        final UUID orderTableId = deliveryOrderTableRepository.save(deliveryOrderTable(true, 4)).getId();
        final DeliveryOrderTable actual = deliveryOrderTableService.clear(orderTableId);
        assertAll(
            () -> assertThat(actual.getNumberOfGuests()).isZero(),
            () -> assertThat(actual.isOccupied()).isFalse()
        );
    }

    @DisplayName("완료되지 않은 주문이 있는 주문 테이블은 빈 테이블로 설정할 수 없다.")
    @Test
    void clearWithUncompletedOrders() {
        final DeliveryOrderTable deliveryOrderTable = deliveryOrderTableRepository.save(deliveryOrderTable(true, 4));
        final UUID orderTableId = deliveryOrderTable.getId();
        deliveryOrderRepository.save(deliveryOrder(DeliveryOrderStatus.ACCEPTED, deliveryOrderTable));
        assertThatThrownBy(() -> deliveryOrderTableService.clear(orderTableId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("방문한 손님 수를 변경할 수 있다.")
    @Test
    void changeNumberOfGuests() {
        final UUID orderTableId = deliveryOrderTableRepository.save(deliveryOrderTable(true, 0)).getId();
        final DeliveryOrderTable expected = changeNumberOfGuestsRequest(4);
        final DeliveryOrderTable actual = deliveryOrderTableService.changeNumberOfGuests(orderTableId, expected);
        assertThat(actual.getNumberOfGuests()).isEqualTo(4);
    }

    @DisplayName("방문한 손님 수가 올바르지 않으면 변경할 수 없다.")
    @ValueSource(ints = -1)
    @ParameterizedTest
    void changeNumberOfGuests(final int numberOfGuests) {
        final UUID orderTableId = deliveryOrderTableRepository.save(deliveryOrderTable(true, 0)).getId();
        final DeliveryOrderTable expected = changeNumberOfGuestsRequest(numberOfGuests);
        assertThatThrownBy(() -> deliveryOrderTableService.changeNumberOfGuests(orderTableId, expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈 테이블은 방문한 손님 수를 변경할 수 없다.")
    @Test
    void changeNumberOfGuestsInEmptyTable() {
        final UUID orderTableId = deliveryOrderTableRepository.save(deliveryOrderTable(false, 0)).getId();
        final DeliveryOrderTable expected = changeNumberOfGuestsRequest(4);
        assertThatThrownBy(() -> deliveryOrderTableService.changeNumberOfGuests(orderTableId, expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문 테이블의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        deliveryOrderTableRepository.save(deliveryOrderTable());
        final List<DeliveryOrderTable> actual = deliveryOrderTableService.findAll();
        assertThat(actual).hasSize(1);
    }

    private DeliveryOrderTable createOrderTableRequest(final String name) {
        final DeliveryOrderTable deliveryOrderTable = new DeliveryOrderTable();
        deliveryOrderTable.setName(name);
        return deliveryOrderTable;
    }

    private DeliveryOrderTable changeNumberOfGuestsRequest(final int numberOfGuests) {
        final DeliveryOrderTable deliveryOrderTable = new DeliveryOrderTable();
        deliveryOrderTable.setNumberOfGuests(numberOfGuests);
        return deliveryOrderTable;
    }
}
