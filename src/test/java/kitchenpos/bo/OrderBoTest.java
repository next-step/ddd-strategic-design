package kitchenpos.bo;

import kitchenpos.menu.domain.MenuDao;
import kitchenpos.order.domain.OrderDao;
import kitchenpos.order.domain.OrderLineItemDao;
import kitchenpos.ordertable.domain.OrderTable;
import kitchenpos.ordertable.domain.OrderTableDao;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.order.bo.OrderBo;
import kitchenpos.ordertable.domain.TableGroup;
import kitchenpos.ordertable.domain.TableGroupDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static kitchenpos.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderBoTest {
    private final MenuDao menuDao = new InMemoryMenuDao();
    private final OrderDao orderDao = new InMemoryOrderDao();
    private final OrderLineItemDao orderLineItemDao = new InMemoryOrderLineItemDao();
    private final OrderTableDao orderTableDao = new InMemoryOrderTableDao();
    private final TableGroupDao tableGroupDao = new InMemoryTableGroupDao();


    private OrderBo orderBo;

    @BeforeEach
    void setUp() {
        orderBo = new OrderBo(menuDao, orderDao, orderLineItemDao, orderTableDao);
        menuDao.save(twoFriedChickens());
        orderTableDao.save(table1());
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
        orderTableDao.save(emptyTable1());

        final Order expected = orderForTable1();

        // when
        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> orderBo.create(expected));
    }

    @DisplayName("주문의 목록을 조회할 수 있다.")
    @Test
    void list() {
        // given
        final Order orderForTable1 = orderDao.save(orderForTable1());

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
        final Long orderId = orderDao.save(orderForTable1()).getId();

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
        final Long orderId = orderDao.save(orderForTable1).getId();

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

    @DisplayName("빈 테이블 설정 또는 해지할 수 있다.")
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void changeEmpty(final boolean empty) {
        // given
        final Long orderTableId = orderTableDao.save(table1()).getId();

        final OrderTable expected = new OrderTable();
        expected.setEmpty(empty);

        // when
        final OrderTable actual = orderBo.changeEmpty(orderTableId, expected);

        // then
        assertThat(actual).isNotNull();
        assertAll(
                () -> assertThat(actual.getId()).isEqualTo(orderTableId),
                () -> assertThat(actual.isEmpty()).isEqualTo(expected.isEmpty())
        );
    }

    @DisplayName("단체 지정을 해지할 수 있다.")
    @Test
    void ungroup() {
        // given
        final Long tableGroupId = saveTable1AndTable2().getId();

        // when
        // then
        orderBo.ungroup(tableGroupId);
    }

    @DisplayName("단체 지정된 테이블의 주문 상태가 조리 또는 식사인 경우 단체 지정을 해지할 수 없다.")
    @Test
    void ungroupNotCalculatedTableGroup() {
        // given
        final Long tableGroupId = saveTable1AndTable2().getId();
        orderDao.save(orderForTable1());

        // when
        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> orderBo.ungroup(tableGroupId));
    }

    private TableGroup saveTable1AndTable2() {
        final TableGroup tableGroup = tableGroupDao.save(table1AndTable2());
        orderTableDao.save(groupedTable1());
        orderTableDao.save(groupedTable2());
        return tableGroup;
    }

}
