package kitchenpos.order.deliveryorder.ui;

import kitchenpos.order.deliveryorder.application.DeliveryOrderService;
import kitchenpos.order.deliveryorder.domain.DeliveryOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/delivery-orders")
@RestController
public class DeliveryOrderRestController {
    private final DeliveryOrderService deliveryOrderService;

    public DeliveryOrderRestController(final DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
    }

    @PostMapping
    public ResponseEntity<DeliveryOrder> create(@RequestBody final DeliveryOrder request) {
        final DeliveryOrder response = deliveryOrderService.create(request);
        return ResponseEntity.created(URI.create("/api/delivery-orders/" + response.getId()))
                .body(response);
    }

    @PutMapping("/{deliveryOrderId}/accept")
    public ResponseEntity<DeliveryOrder> accept(@PathVariable final UUID deliveryOrderId) {
        return ResponseEntity.ok(deliveryOrderService.accept(deliveryOrderId));
    }

    @PutMapping("/{deliveryOrderId}/serve")
    public ResponseEntity<DeliveryOrder> serve(@PathVariable final UUID deliveryOrderId) {
        return ResponseEntity.ok(deliveryOrderService.pickup(deliveryOrderId));
    }

    @PutMapping("/{deliveryOrderId}/start-delivery")
    public ResponseEntity<DeliveryOrder> startDelivery(@PathVariable final UUID deliveryOrderId) {
        return ResponseEntity.ok(deliveryOrderService.startDelivery(deliveryOrderId));
    }

    @PutMapping("/{deliveryOrderId}/complete-delivery")
    public ResponseEntity<DeliveryOrder> completeDelivery(@PathVariable final UUID deliveryOrderId) {
        return ResponseEntity.ok(deliveryOrderService.completeDelivery(deliveryOrderId));
    }

    @PutMapping("/{deliveryOrderId}/complete")
    public ResponseEntity<DeliveryOrder> complete(@PathVariable final UUID deliveryOrderId) {
        return ResponseEntity.ok(deliveryOrderService.complete(deliveryOrderId));
    }

    @GetMapping
    public ResponseEntity<List<DeliveryOrder>> findAll() {
        return ResponseEntity.ok(deliveryOrderService.findAll());
    }
}
