package kitchenpos.eatinorder.ui;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import kitchenpos.eatinorder.application.OrderTableService;
import kitchenpos.eatinorder.domain.OrderTable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/eat-in-order-tables")
@RestController
public class OrderTableRestController {
    private final OrderTableService orderTableService;

    public OrderTableRestController(final OrderTableService orderTableService) {
        this.orderTableService = orderTableService;
    }

    @PostMapping
    public ResponseEntity<OrderTable> create(@RequestBody final OrderTable request) {
        final OrderTable response = orderTableService.create(request);
        return ResponseEntity.created(URI.create("/api/eat-in-order-tables/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{eatInOrderTableId}/sit")
    public ResponseEntity<OrderTable> sit(@PathVariable final UUID eatInOrderTableId) {
        return ResponseEntity.ok(orderTableService.sit(eatInOrderTableId));
    }

    @PutMapping("/{eatInOrderTableId}/clear")
    public ResponseEntity<OrderTable> clear(@PathVariable final UUID eatInOrderTableId) {
        return ResponseEntity.ok(orderTableService.clear(eatInOrderTableId));
    }

    @PutMapping("/{eatInOrderTableId}/number-of-guests")
    public ResponseEntity<OrderTable> changeNumberOfGuests(
        @PathVariable final UUID eatInOrderTableId,
        @RequestBody final OrderTable request
    ) {
        return ResponseEntity.ok(orderTableService.changeNumberOfGuests(eatInOrderTableId, request));
    }

    @GetMapping
    public ResponseEntity<List<OrderTable>> findAll() {
        return ResponseEntity.ok(orderTableService.findAll());
    }
}
