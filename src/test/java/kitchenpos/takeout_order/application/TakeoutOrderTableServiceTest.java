package kitchenpos.takeout_order.application;

import static kitchenpos.Fixtures.takeoutOrder;
import static kitchenpos.Fixtures.takeoutOrderTable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.UUID;
import kitchenpos.takeout_order.InMemoryTakeoutOrderRepository;
import kitchenpos.takeout_order.InMemoryTakeoutOrderTableRepository;
import kitchenpos.takeout_order.domain.TakeoutOrderRepository;
import kitchenpos.takeout_order.domain.TakeoutOrderStatus;
import kitchenpos.takeout_order.domain.TakeoutOrderTable;
import kitchenpos.takeout_order.domain.TakeoutOrderTableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TakeoutOrderTableServiceTest {
    private TakeoutOrderTableRepository takeoutOrderTableRepository;
    private TakeoutOrderRepository takeoutOrderRepository;
    private TakeoutOrderTableService takeoutOrderTableService;

    @BeforeEach
    void setUp() {
        takeoutOrderTableRepository = new InMemoryTakeoutOrderTableRepository();
        takeoutOrderRepository = new InMemoryTakeoutOrderRepository();
        takeoutOrderTableService = new TakeoutOrderTableService(takeoutOrderTableRepository,
            takeoutOrderRepository);
    }

    @DisplayName("주문 테이블을 등록할 수 있다.")
    @Test
    void create() {
        final TakeoutOrderTable expected = createOrderTableRequest("1번");
        final TakeoutOrderTable actual = takeoutOrderTableService.create(expected);
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
        final TakeoutOrderTable expected = createOrderTableRequest(name);
        assertThatThrownBy(() -> takeoutOrderTableService.create(expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈 테이블을 해지할 수 있다.")
    @Test
    void sit() {
        final UUID orderTableId = takeoutOrderTableRepository.save(takeoutOrderTable(false, 0)).getId();
        final TakeoutOrderTable actual = takeoutOrderTableService.sit(orderTableId);
        assertThat(actual.isOccupied()).isTrue();
    }

    @DisplayName("빈 테이블로 설정할 수 있다.")
    @Test
    void clear() {
        final UUID orderTableId = takeoutOrderTableRepository.save(takeoutOrderTable(true, 4)).getId();
        final TakeoutOrderTable actual = takeoutOrderTableService.clear(orderTableId);
        assertAll(
            () -> assertThat(actual.getNumberOfGuests()).isZero(),
            () -> assertThat(actual.isOccupied()).isFalse()
        );
    }

    @DisplayName("완료되지 않은 주문이 있는 주문 테이블은 빈 테이블로 설정할 수 없다.")
    @Test
    void clearWithUncompletedOrders() {
        final TakeoutOrderTable takeoutOrderTable = takeoutOrderTableRepository.save(takeoutOrderTable(true, 4));
        final UUID orderTableId = takeoutOrderTable.getId();
        takeoutOrderRepository.save(takeoutOrder(TakeoutOrderStatus.ACCEPTED, takeoutOrderTable));
        assertThatThrownBy(() -> takeoutOrderTableService.clear(orderTableId))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("방문한 손님 수를 변경할 수 있다.")
    @Test
    void changeNumberOfGuests() {
        final UUID orderTableId = takeoutOrderTableRepository.save(takeoutOrderTable(true, 0)).getId();
        final TakeoutOrderTable expected = changeNumberOfGuestsRequest(4);
        final TakeoutOrderTable actual = takeoutOrderTableService.changeNumberOfGuests(orderTableId, expected);
        assertThat(actual.getNumberOfGuests()).isEqualTo(4);
    }

    @DisplayName("방문한 손님 수가 올바르지 않으면 변경할 수 없다.")
    @ValueSource(ints = -1)
    @ParameterizedTest
    void changeNumberOfGuests(final int numberOfGuests) {
        final UUID orderTableId = takeoutOrderTableRepository.save(takeoutOrderTable(true, 0)).getId();
        final TakeoutOrderTable expected = changeNumberOfGuestsRequest(numberOfGuests);
        assertThatThrownBy(() -> takeoutOrderTableService.changeNumberOfGuests(orderTableId, expected))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈 테이블은 방문한 손님 수를 변경할 수 없다.")
    @Test
    void changeNumberOfGuestsInEmptyTable() {
        final UUID orderTableId = takeoutOrderTableRepository.save(takeoutOrderTable(false, 0)).getId();
        final TakeoutOrderTable expected = changeNumberOfGuestsRequest(4);
        assertThatThrownBy(() -> takeoutOrderTableService.changeNumberOfGuests(orderTableId, expected))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("주문 테이블의 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        takeoutOrderTableRepository.save(takeoutOrderTable());
        final List<TakeoutOrderTable> actual = takeoutOrderTableService.findAll();
        assertThat(actual).hasSize(1);
    }

    private TakeoutOrderTable createOrderTableRequest(final String name) {
        final TakeoutOrderTable takeoutOrderTable = new TakeoutOrderTable();
        takeoutOrderTable.setName(name);
        return takeoutOrderTable;
    }

    private TakeoutOrderTable changeNumberOfGuestsRequest(final int numberOfGuests) {
        final TakeoutOrderTable takeoutOrderTable = new TakeoutOrderTable();
        takeoutOrderTable.setNumberOfGuests(numberOfGuests);
        return takeoutOrderTable;
    }
}
