package kitchenpos.order.application.usecase;

import kitchenpos.order.domain.Order;

import java.util.List;

public interface RetrieveOrder {
    List<Order> findAll();
}
