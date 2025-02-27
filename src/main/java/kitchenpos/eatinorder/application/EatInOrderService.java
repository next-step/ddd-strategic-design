package kitchenpos.eatinorder.application;

import kitchenpos.shared.domain.OrderType;
import kitchenpos.eatinorder.domain.*;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EatInOrderService {
    private final EatInOrderRepository eatInorderRepository;
    private final MenuRepository menuRepository;
    private final RestaurantTableRepository restaurantTableRepository;

    public EatInOrderService(
            final EatInOrderRepository eatInorderRepository,
            final MenuRepository menuRepository,
            final RestaurantTableRepository restaurantTableRepository) {
        this.eatInorderRepository = eatInorderRepository;
        this.menuRepository = menuRepository;
        this.restaurantTableRepository = restaurantTableRepository;
    }

    @Transactional
    public EatInOrder create(final EatInOrder request) {
        final OrderType type = request.getType();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }
        final List<EatInOrderLineItem> EatInorderLineItemRequests = request.getOrderLineItems();
        if (Objects.isNull(EatInorderLineItemRequests) || EatInorderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final List<Menu> menus = menuRepository.findAllByIdIn(
                EatInorderLineItemRequests.stream()
                        .map(EatInOrderLineItem::getMenuId)
                        .toList()
        );
        if (menus.size() != EatInorderLineItemRequests.size()) {
            throw new IllegalArgumentException();
        }
        final List<EatInOrderLineItem> EatInorderLineItems = new ArrayList<>();
        for (final EatInOrderLineItem EatInorderLineItemRequest : EatInorderLineItemRequests) {
            final long quantity = EatInorderLineItemRequest.getQuantity();
            if (type != OrderType.EAT_IN) {
                if (quantity < 0) {
                    throw new IllegalArgumentException();
                }
            }
            final Menu menu = menuRepository.findById(EatInorderLineItemRequest.getMenuId())
                    .orElseThrow(NoSuchElementException::new);
            if (!menu.isDisplayed()) {
                throw new IllegalStateException();
            }
            if (menu.getPrice().compareTo(EatInorderLineItemRequest.getPrice()) != 0) {
                throw new IllegalArgumentException();
            }
            final EatInOrderLineItem EatInorderLineItem = new EatInOrderLineItem();
            EatInorderLineItem.setMenu(menu);
            EatInorderLineItem.setQuantity(quantity);
            EatInorderLineItems.add(EatInorderLineItem);
        }
        EatInOrder eatInorder = new EatInOrder();
        eatInorder.setId(UUID.randomUUID());
        eatInorder.setType(type);
        eatInorder.setStatus(EatInOrderStatus.WAITING);
        eatInorder.setOrderDateTime(LocalDateTime.now());
        eatInorder.setOrderLineItems(EatInorderLineItems);
        if (type == OrderType.EAT_IN) {
            final RestaurantTable restaurantTable = restaurantTableRepository.findById(request.getOrderTableId())
                    .orElseThrow(NoSuchElementException::new);
            if (!restaurantTable.isOccupied()) {
                throw new IllegalStateException();
            }
            eatInorder.setOrderTable(restaurantTable);
        }
        return eatInorderRepository.save(eatInorder);
    }

    @Transactional
    public EatInOrder accept(final UUID eatInOrderId) {
        final EatInOrder eatInorder = eatInorderRepository.findById(eatInOrderId)
                .orElseThrow(NoSuchElementException::new);
        if (eatInorder.getStatus() != EatInOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        eatInorder.setStatus(EatInOrderStatus.ACCEPTED);
        return eatInorder;
    }

    @Transactional
    public EatInOrder serve(final UUID eatInOrderId) {
        final EatInOrder eatInorder = eatInorderRepository.findById(eatInOrderId)
                .orElseThrow(NoSuchElementException::new);
        if (eatInorder.getStatus() != EatInOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        eatInorder.setStatus(EatInOrderStatus.SERVED);
        return eatInorder;
    }

    @Transactional
    public EatInOrder complete(final UUID eatInOrderId) {
        final EatInOrder eatInorder = eatInorderRepository.findById(eatInOrderId)
                .orElseThrow(NoSuchElementException::new);
        final OrderType type = eatInorder.getType();
        final EatInOrderStatus status = eatInorder.getStatus();
        if (type == OrderType.EAT_IN) {
            if (status != EatInOrderStatus.SERVED) {
                throw new IllegalStateException();
            }
        }
        eatInorder.setStatus(EatInOrderStatus.COMPLETED);
        if (type == OrderType.EAT_IN) {
            final RestaurantTable restaurantTable = eatInorder.getOrderTable();
            if (!eatInorderRepository.existsByRestraurantTableAndStatusNot(restaurantTable, EatInOrderStatus.COMPLETED)) {
                restaurantTable.setNumberOfGuests(0);
                restaurantTable.setOccupied(false);
            }
        }
        return eatInorder;
    }

    @Transactional(readOnly = true)
    public List<EatInOrder> findAll() {
        return eatInorderRepository.findAll();
    }
}
