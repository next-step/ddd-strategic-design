package kitchenpos.ordertablegroups.application;

import kitchenpos.orders.domain.OrderDao;
import kitchenpos.ordertables.domain.OrderTable;
import kitchenpos.ordertables.domain.OrderTableDao;
import kitchenpos.ordertablegroups.domain.OrderTableGroupDao;
import kitchenpos.orders.domain.OrderStatus;
import kitchenpos.ordertablegroups.domain.OrderTableGroup;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderTableGroupBo {
    private final OrderDao orderDao;
    private final OrderTableDao orderTableDao;
    private final OrderTableGroupDao orderTableGroupDao;

    public OrderTableGroupBo(final OrderDao orderDao, final OrderTableDao orderTableDao, final OrderTableGroupDao orderTableGroupDao) {
        this.orderDao = orderDao;
        this.orderTableDao = orderTableDao;
        this.orderTableGroupDao = orderTableGroupDao;
    }

    @Transactional
    public OrderTableGroup create(final OrderTableGroup orderTableGroup) {
        final List<OrderTable> orderTables = orderTableGroup.getOrderTables();

        if (CollectionUtils.isEmpty(orderTables) || orderTables.size() < 2) {
            throw new IllegalArgumentException();
        }

        final List<Long> orderTableIds = orderTables.stream()
                .map(OrderTable::getId)
                .collect(Collectors.toList());

        final List<OrderTable> savedOrderTables = orderTableDao.findAllByIdIn(orderTableIds);

        if (orderTables.size() != savedOrderTables.size()) {
            throw new IllegalArgumentException();
        }

        for (final OrderTable savedOrderTable : savedOrderTables) {
            if (!savedOrderTable.isEmpty() || Objects.nonNull(savedOrderTable.getTableGroupId())) {
                throw new IllegalArgumentException();
            }
        }

        orderTableGroup.setCreatedDate(LocalDateTime.now());

        final OrderTableGroup savedOrderTableGroup = orderTableGroupDao.save(orderTableGroup);

        final Long tableGroupId = savedOrderTableGroup.getId();
        for (final OrderTable savedOrderTable : savedOrderTables) {
            savedOrderTable.setTableGroupId(tableGroupId);
            savedOrderTable.setEmpty(false);
            orderTableDao.save(savedOrderTable);
        }
        savedOrderTableGroup.setOrderTables(savedOrderTables);

        return savedOrderTableGroup;
    }

    @Transactional
    public void ungroup(final Long tableGroupId) {
        final List<OrderTable> orderTables = orderTableDao.findAllByTableGroupId(tableGroupId);

        final List<Long> orderTableIds = orderTables.stream()
                .map(OrderTable::getId)
                .collect(Collectors.toList());

        if (orderDao.existsByOrderTableIdInAndOrderStatusIn(
                orderTableIds, Arrays.asList(OrderStatus.COOKING.name(), OrderStatus.MEAL.name()))) {
            throw new IllegalArgumentException();
        }

        for (final OrderTable orderTable : orderTables) {
            orderTable.setTableGroupId(null);
            orderTableDao.save(orderTable);
        }
    }
}
