package kitchenpos.bo;

import kitchenpos.orders.domain.OrderDao;
import kitchenpos.ordertables.domain.OrderTableDao;
import kitchenpos.ordertablegroups.domain.OrderTableGroupDao;
import kitchenpos.ordertablegroups.domain.OrderTableGroup;
import kitchenpos.ordertablegroups.application.OrderTableGroupBo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static kitchenpos.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderOrderTableGroupBoTest {
    private final OrderDao orderDao = new InMemoryOrderDao();
    private final OrderTableDao orderTableDao = new InMemoryOrderTableDao();
    private final OrderTableGroupDao orderTableGroupDao = new InMemoryOrderTableGroupDao();

    private OrderTableGroupBo orderTableGroupBo;

    @BeforeEach
    void setUp() {
        orderTableGroupBo = new OrderTableGroupBo(orderDao, orderTableDao, orderTableGroupDao);
        orderTableDao.save(emptyTable1());
        orderTableDao.save(emptyTable2());
    }

    @DisplayName("2 개 이상의 빈 테이블을 단체로 지정할 수 있다.")
    @Test
    void create() {
        // given
        final OrderTableGroup expected = table1AndTable2();

        // when
        final OrderTableGroup actual = orderTableGroupBo.create(expected);

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
        orderTableDao.save(groupedTable1());

        final OrderTableGroup expected = table1AndTable2();

        // when
        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> orderTableGroupBo.create(expected));
    }

    @DisplayName("단체 지정을 해지할 수 있다.")
    @Test
    void ungroup() {
        // given
        final Long tableGroupId = saveTable1AndTable2().getId();

        // when
        // then
        orderTableGroupBo.ungroup(tableGroupId);
    }

    @DisplayName("단체 지정된 테이블의 주문 상태가 조리 또는 식사인 경우 단체 지정을 해지할 수 없다.")
    @Test
    void ungroupNotCalculatedTableGroup() {
        // given
        final Long tableGroupId = saveTable1AndTable2().getId();
        orderDao.save(orderForTable1());

        // when
        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> orderTableGroupBo.ungroup(tableGroupId));
    }

    private OrderTableGroup saveTable1AndTable2() {
        final OrderTableGroup orderTableGroup = orderTableGroupDao.save(table1AndTable2());
        orderTableDao.save(groupedTable1());
        orderTableDao.save(groupedTable2());
        return orderTableGroup;
    }
}
