package kitchenpos.orderTable.service;

import kitchenpos.order.repository.InMemoryOrderRepository;
import kitchenpos.order.domain.OrderRepository;
import kitchenpos.orderTable.repository.InMemoryOrderTableRepository;
import kitchenpos.orderTable.repository.InMemoryTableGroupRepository;
import kitchenpos.orderTable.domain.OrderTableRepository;
import kitchenpos.orderTable.domain.TableGroupRepository;
import kitchenpos.orderTable.domain.model.TableGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static kitchenpos.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

class TableGroupServiceTest {
    private final OrderRepository orderRepository = new InMemoryOrderRepository();
    private final OrderTableRepository orderTableRepository = new InMemoryOrderTableRepository();
    private final TableGroupRepository tableGroupRepository = new InMemoryTableGroupRepository();

    private TableGroupService tableGroupService;

    @BeforeEach
    void setUp() {
        tableGroupService = new TableGroupService(orderRepository, orderTableRepository, tableGroupRepository);
        orderTableRepository.save(emptyTable1());
        orderTableRepository.save(emptyTable2());
    }

    @DisplayName("2 개 이상의 빈 테이블을 단체로 지정할 수 있다.")
    @Test
    void create() {
        // given
        final TableGroup expected = table1AndTable2();

        // when
        final TableGroup actual = tableGroupService.create(expected);

        // then
        assertThat(actual).isNotNull();
        assertAll(
                () -> assertThat(actual.getOrderTables())
                        .containsExactlyInAnyOrderElementsOf(expected.getOrderTables())
        );
    }

    @DisplayName("단체 지정은 중복될 수 없다.")
    @Test
    void createWithGroupedTable() {
        // given
        orderTableRepository.save(groupedTable1());

        final TableGroup expected = table1AndTable2();

        // when
        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> tableGroupService.create(expected));
    }

    @DisplayName("단체 지정을 해지할 수 있다.")
    @Test
    void ungroup() {
        // given
        final Long tableGroupId = saveTable1AndTable2().getId();

        // when
        // then
        tableGroupService.ungroup(tableGroupId);
    }

    @DisplayName("단체 지정된 테이블의 주문 상태가 조리 또는 식사인 경우 단체 지정을 해지할 수 없다.")
    @Test
    void ungroupNotCalculatedTableGroup() {
        // given
        final Long tableGroupId = saveTable1AndTable2().getId();
        orderRepository.save(orderForTable1());

        // when
        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> tableGroupService.ungroup(tableGroupId));
    }

    private TableGroup saveTable1AndTable2() {
        final TableGroup tableGroup = tableGroupRepository.save(table1AndTable2());
        orderTableRepository.save(groupedTable1());
        orderTableRepository.save(groupedTable2());
        return tableGroup;
    }
}
