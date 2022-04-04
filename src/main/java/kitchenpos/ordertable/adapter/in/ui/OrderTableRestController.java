package kitchenpos.ordertable.adapter.in.ui;

import kitchenpos.ordertable.application.port.in.OrderTableServicePort;
import kitchenpos.ordertable.domain.OrderTable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/order-tables")
@RestController
public class OrderTableRestController {
    private final OrderTableServicePort orderTableServicePort;

    public OrderTableRestController(final OrderTableServicePort orderTableServicePort) {
        this.orderTableServicePort = orderTableServicePort;
    }

    @PostMapping
    public ResponseEntity<OrderTable> create(@RequestBody final OrderTable request) {
        final OrderTable response = orderTableServicePort.create(request);
        return ResponseEntity.created(URI.create("/api/order-tables/" + response.getId()))
                             .body(response);
    }

    @PutMapping("/{orderTableId}/sit")
    public ResponseEntity<OrderTable> sit(@PathVariable final UUID orderTableId) {
        return ResponseEntity.ok(orderTableServicePort.sit(orderTableId));
    }

    @PutMapping("/{orderTableId}/clear")
    public ResponseEntity<OrderTable> clear(@PathVariable final UUID orderTableId) {
        return ResponseEntity.ok(orderTableServicePort.clear(orderTableId));
    }

    @PutMapping("/{orderTableId}/number-of-guests")
    public ResponseEntity<OrderTable> changeNumberOfGuests(
        @PathVariable final UUID orderTableId,
        @RequestBody final OrderTable request
    ) {
        return ResponseEntity.ok(orderTableServicePort.changeNumberOfGuests(orderTableId, request));
    }

    @GetMapping
    public ResponseEntity<List<OrderTable>> findAll() {
        return ResponseEntity.ok(orderTableServicePort.findAll());
    }
}
