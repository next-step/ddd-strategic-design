package kitchenpos.order.eatinorder.application;

import kitchenpos.order.eatinorder.domain.EatInOrderRepository;
import kitchenpos.order.eatinorder.domain.EatInOrderStatus;
import kitchenpos.order.eatinorder.domain.RestaurantTable;
import kitchenpos.order.eatinorder.domain.RestaurantTableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class RestaurantTableService {
    private final RestaurantTableRepository restaurantTableRepository;
    private final EatInOrderRepository eatInOrderRepository;

    public RestaurantTableService(final RestaurantTableRepository restaurantTableRepository, final EatInOrderRepository eatInOrderRepository) {
        this.restaurantTableRepository = restaurantTableRepository;
        this.eatInOrderRepository = eatInOrderRepository;
    }

    @Transactional
    public RestaurantTable create(final RestaurantTable request) {
        final String name = request.getName();
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setId(UUID.randomUUID());
        restaurantTable.setName(name);
        restaurantTable.setNumberOfGuests(0);
        restaurantTable.setOccupied(false);
        return restaurantTableRepository.save(restaurantTable);
    }

    @Transactional
    public RestaurantTable sit(final UUID orderTableId) {
        final RestaurantTable restaurantTable = restaurantTableRepository.findById(orderTableId)
                .orElseThrow(NoSuchElementException::new);
        restaurantTable.setOccupied(true);
        return restaurantTable;
    }

    @Transactional
    public RestaurantTable clear(final UUID restaurantTableId) {
        final RestaurantTable restaurantTable = restaurantTableRepository.findById(restaurantTableId)
                .orElseThrow(NoSuchElementException::new);
        if (eatInOrderRepository.existsByRestaruantTableAndStatusNot(restaurantTable, EatInOrderStatus.COMPLETED)) {
            throw new IllegalStateException();
        }
        restaurantTable.setNumberOfGuests(0);
        restaurantTable.setOccupied(false);
        return restaurantTable;
    }

    @Transactional
    public RestaurantTable changeNumberOfGuests(final UUID restaurantTableId, final RestaurantTable request) {
        final int numberOfGuests = request.getNumberOfGuests();
        if (numberOfGuests < 0) {
            throw new IllegalArgumentException();
        }
        final RestaurantTable restaurantTable = restaurantTableRepository.findById(restaurantTableId)
                .orElseThrow(NoSuchElementException::new);
        if (!restaurantTable.isOccupied()) {
            throw new IllegalStateException();
        }
        restaurantTable.setNumberOfGuests(numberOfGuests);
        return restaurantTable;
    }

    @Transactional(readOnly = true)
    public List<RestaurantTable> findAll() {
        return restaurantTableRepository.findAll();
    }
}
