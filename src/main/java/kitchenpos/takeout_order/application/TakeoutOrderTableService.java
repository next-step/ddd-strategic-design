package kitchenpos.takeout_order.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import kitchenpos.takeout_order.domain.TakeoutOrderRepository;
import kitchenpos.takeout_order.domain.TakeoutOrderStatus;
import kitchenpos.takeout_order.domain.TakeoutOrderTable;
import kitchenpos.takeout_order.domain.TakeoutOrderTableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TakeoutOrderTableService {
    private final TakeoutOrderTableRepository takeoutOrderTableRepository;
    private final TakeoutOrderRepository takeoutOrderRepository;

    public TakeoutOrderTableService(final TakeoutOrderTableRepository takeoutOrderTableRepository, final TakeoutOrderRepository takeoutOrderRepository) {
        this.takeoutOrderTableRepository = takeoutOrderTableRepository;
        this.takeoutOrderRepository = takeoutOrderRepository;
    }

    @Transactional
    public TakeoutOrderTable create(final TakeoutOrderTable request) {
        final String name = request.getName();
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final TakeoutOrderTable takeoutOrderTable = new TakeoutOrderTable();
        takeoutOrderTable.setId(UUID.randomUUID());
        takeoutOrderTable.setName(name);
        takeoutOrderTable.setNumberOfGuests(0);
        takeoutOrderTable.setOccupied(false);
        return takeoutOrderTableRepository.save(takeoutOrderTable);
    }

    @Transactional
    public TakeoutOrderTable sit(final UUID orderTableId) {
        final TakeoutOrderTable takeoutOrderTable = takeoutOrderTableRepository.findById(orderTableId)
            .orElseThrow(NoSuchElementException::new);
        takeoutOrderTable.setOccupied(true);
        return takeoutOrderTable;
    }

    @Transactional
    public TakeoutOrderTable clear(final UUID orderTableId) {
        final TakeoutOrderTable takeoutOrderTable = takeoutOrderTableRepository.findById(orderTableId)
            .orElseThrow(NoSuchElementException::new);
        if (takeoutOrderRepository.existsByOrderTableAndStatusNot(takeoutOrderTable, TakeoutOrderStatus.COMPLETED)) {
            throw new IllegalStateException();
        }
        takeoutOrderTable.setNumberOfGuests(0);
        takeoutOrderTable.setOccupied(false);
        return takeoutOrderTable;
    }

    @Transactional
    public TakeoutOrderTable changeNumberOfGuests(final UUID orderTableId, final TakeoutOrderTable request) {
        final int numberOfGuests = request.getNumberOfGuests();
        if (numberOfGuests < 0) {
            throw new IllegalArgumentException();
        }
        final TakeoutOrderTable takeoutOrderTable = takeoutOrderTableRepository.findById(orderTableId)
            .orElseThrow(NoSuchElementException::new);
        if (!takeoutOrderTable.isOccupied()) {
            throw new IllegalStateException();
        }
        takeoutOrderTable.setNumberOfGuests(numberOfGuests);
        return takeoutOrderTable;
    }

    @Transactional(readOnly = true)
    public List<TakeoutOrderTable> findAll() {
        return takeoutOrderTableRepository.findAll();
    }
}
