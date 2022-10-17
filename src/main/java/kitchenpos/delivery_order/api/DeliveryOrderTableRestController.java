package kitchenpos.delivery_order.api;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import kitchenpos.delivery_order.application.DeliveryOrderTableService;
import kitchenpos.delivery_order.domain.DeliveryOrderTable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/order-tables")
@RestController
public class DeliveryOrderTableRestController {
    private final DeliveryOrderTableService deliveryOrderTableService;

    public DeliveryOrderTableRestController(final DeliveryOrderTableService deliveryOrderTableService) {
        this.deliveryOrderTableService = deliveryOrderTableService;
    }

    @PostMapping
    public ResponseEntity<DeliveryOrderTable> create(@RequestBody final DeliveryOrderTable request) {
        final DeliveryOrderTable response = deliveryOrderTableService.create(request);
        return ResponseEntity.created(URI.create("/api/order-tables/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{orderTableId}/sit")
    public ResponseEntity<DeliveryOrderTable> sit(@PathVariable final UUID orderTableId) {
        return ResponseEntity.ok(deliveryOrderTableService.sit(orderTableId));
    }

    @PutMapping("/{orderTableId}/clear")
    public ResponseEntity<DeliveryOrderTable> clear(@PathVariable final UUID orderTableId) {
        return ResponseEntity.ok(deliveryOrderTableService.clear(orderTableId));
    }

    @PutMapping("/{orderTableId}/number-of-guests")
    public ResponseEntity<DeliveryOrderTable> changeNumberOfGuests(
        @PathVariable final UUID orderTableId,
        @RequestBody final DeliveryOrderTable request
    ) {
        return ResponseEntity.ok(
            deliveryOrderTableService.changeNumberOfGuests(orderTableId, request));
    }

    @GetMapping
    public ResponseEntity<List<DeliveryOrderTable>> findAll() {
        return ResponseEntity.ok(deliveryOrderTableService.findAll());
    }
}
