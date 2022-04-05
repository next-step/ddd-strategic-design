package kitchenpos.ordertable.application.service;

import kitchenpos.order.application.port.out.OrderRepository;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.ordertable.application.port.in.OrderTableServicePort;
import kitchenpos.ordertable.domain.OrderTable;
import kitchenpos.ordertable.application.port.out.OrderTableRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class OrderTableService implements OrderTableServicePort {
    private final OrderTableRepository orderTableRepository;
    private final OrderRepository orderRepository;

    public OrderTableService(final OrderTableRepository orderTableRepository, final OrderRepository orderRepository) {
        this.orderTableRepository = orderTableRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public OrderTable create(final OrderTable request) {
        final String name = request.getName();
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final OrderTable orderTable = new OrderTable();
        orderTable.setId(UUID.randomUUID());
        orderTable.setName(name);
        orderTable.setNumberOfGuests(0);
        orderTable.setEmpty(true);
        return orderTableRepository.save(orderTable);
    }

    @Override
    @Transactional
    public OrderTable sit(final UUID orderTableId) {
        final OrderTable orderTable = orderTableRepository.findById(orderTableId)
                                                          .orElseThrow(NoSuchElementException::new);
        orderTable.setEmpty(false);
        return orderTable;
    }

    @Override
    @Transactional
    public OrderTable clear(final UUID orderTableId) {
        final OrderTable orderTable = orderTableRepository.findById(orderTableId)
                                                          .orElseThrow(NoSuchElementException::new);
        if (orderRepository.existsByOrderTableAndStatusNot(orderTable, OrderStatus.COMPLETED)) {
            throw new IllegalStateException();
        }
        orderTable.setNumberOfGuests(0);
        orderTable.setEmpty(true);
        return orderTable;
    }

    @Override
    @Transactional
    public OrderTable changeNumberOfGuests(final UUID orderTableId, final OrderTable request) {
        final int numberOfGuests = request.getNumberOfGuests();
        if (numberOfGuests < 0) {
            throw new IllegalArgumentException();
        }
        final OrderTable orderTable = orderTableRepository.findById(orderTableId)
                                                          .orElseThrow(NoSuchElementException::new);
        if (orderTable.isEmpty()) {
            throw new IllegalStateException();
        }
        orderTable.setNumberOfGuests(numberOfGuests);
        return orderTable;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderTable> findAll() {
        return orderTableRepository.findAll();
    }
}
