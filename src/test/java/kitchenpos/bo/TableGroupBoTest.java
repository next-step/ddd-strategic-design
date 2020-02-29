package kitchenpos.bo;

import kitchenpos.order.domain.OrderDao;
import kitchenpos.ordertable.domain.OrderTableDao;
import kitchenpos.ordertable.domain.TableGroupDao;
import kitchenpos.ordertable.domain.TableGroup;
import kitchenpos.ordertable.bo.TableGroupBo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static kitchenpos.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

class TableGroupBoTest {
    private final OrderTableDao orderTableDao = new InMemoryOrderTableDao();
    private final TableGroupDao tableGroupDao = new InMemoryTableGroupDao();

    private TableGroupBo tableGroupBo;

    @BeforeEach
    void setUp() {
        tableGroupBo = new TableGroupBo(orderTableDao, tableGroupDao);
        orderTableDao.save(emptyTable1());
        orderTableDao.save(emptyTable2());
    }

    @DisplayName("2 개 이상의 빈 테이블을 단체로 지정할 수 있다.")
    @Test
    void create() {
        // given
        final TableGroup expected = table1AndTable2();

        // when
        final TableGroup actual = tableGroupBo.create(expected);

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

        final TableGroup expected = table1AndTable2();

        // when
        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> tableGroupBo.create(expected));
    }

}
