package kitchenpos.delivery_order.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import kitchenpos.delivery_order.domain.DeliveryOrderRepository;
import kitchenpos.delivery_order.domain.DeliveryOrderStatus;
import kitchenpos.delivery_order.domain.DeliveryOrderTable;
import kitchenpos.delivery_order.domain.DeliveryOrderTableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryOrderTableService {
    private final DeliveryOrderTableRepository deliveryOrderTableRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;

    public DeliveryOrderTableService(final DeliveryOrderTableRepository deliveryOrderTableRepository, final DeliveryOrderRepository deliveryOrderRepository) {
        this.deliveryOrderTableRepository = deliveryOrderTableRepository;
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Transactional
    public DeliveryOrderTable create(final DeliveryOrderTable request) {
        final String name = request.getName();
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final DeliveryOrderTable deliveryOrderTable = new DeliveryOrderTable();
        deliveryOrderTable.setId(UUID.randomUUID());
        deliveryOrderTable.setName(name);
        deliveryOrderTable.setNumberOfGuests(0);
        deliveryOrderTable.setOccupied(false);
        return deliveryOrderTableRepository.save(deliveryOrderTable);
    }

    @Transactional
    public DeliveryOrderTable sit(final UUID orderTableId) {
        final DeliveryOrderTable deliveryOrderTable = deliveryOrderTableRepository.findById(orderTableId)
            .orElseThrow(NoSuchElementException::new);
        deliveryOrderTable.setOccupied(true);
        return deliveryOrderTable;
    }

    @Transactional
    public DeliveryOrderTable clear(final UUID orderTableId) {
        final DeliveryOrderTable deliveryOrderTable = deliveryOrderTableRepository.findById(orderTableId)
            .orElseThrow(NoSuchElementException::new);
        if (deliveryOrderRepository.existsByOrderTableAndStatusNot(deliveryOrderTable, DeliveryOrderStatus.COMPLETED)) {
            throw new IllegalStateException();
        }
        deliveryOrderTable.setNumberOfGuests(0);
        deliveryOrderTable.setOccupied(false);
        return deliveryOrderTable;
    }

    @Transactional
    public DeliveryOrderTable changeNumberOfGuests(final UUID orderTableId, final DeliveryOrderTable request) {
        final int numberOfGuests = request.getNumberOfGuests();
        if (numberOfGuests < 0) {
            throw new IllegalArgumentException();
        }
        final DeliveryOrderTable deliveryOrderTable = deliveryOrderTableRepository.findById(orderTableId)
            .orElseThrow(NoSuchElementException::new);
        if (!deliveryOrderTable.isOccupied()) {
            throw new IllegalStateException();
        }
        deliveryOrderTable.setNumberOfGuests(numberOfGuests);
        return deliveryOrderTable;
    }

    @Transactional(readOnly = true)
    public List<DeliveryOrderTable> findAll() {
        return deliveryOrderTableRepository.findAll();
    }
}
