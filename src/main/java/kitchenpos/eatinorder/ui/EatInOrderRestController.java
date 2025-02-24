package kitchenpos.eatinorder.ui;


import kitchenpos.eatinorder.application.EatInOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/eatinorder")
@RestController
public class EatInOrderRestController {
    private final EatInOrderService orderService;

    public EatInOrderRestController(final EatInOrderService orderService) {
        this.orderService = orderService;
    }

//    @PostMapping
//    public ResponseEntity<Order> create(@RequestBody final Order request) {
//        final Order response = orderService.create(request);
//        return ResponseEntity.created(URI.create("/api/orders/" + response.getId()))
//                .body(response);
//    }
//
//    @PutMapping("/{orderId}/accept")
//    public ResponseEntity<Order> accept(@PathVariable final UUID orderId) {
//        return ResponseEntity.ok(orderService.accept(orderId));
//    }
//
//    @PutMapping("/{orderId}/serve")
//    public ResponseEntity<Order> serve(@PathVariable final UUID orderId) {
//        return ResponseEntity.ok(orderService.serve(orderId));
//    }
//
//    @PutMapping("/{orderId}/start-delivery")
//    public ResponseEntity<Order> startDelivery(@PathVariable final UUID orderId) {
//        return ResponseEntity.ok(orderService.startDelivery(orderId));
//    }
//
//    @PutMapping("/{orderId}/complete-delivery")
//    public ResponseEntity<Order> completeDelivery(@PathVariable final UUID orderId) {
//        return ResponseEntity.ok(orderService.completeDelivery(orderId));
//    }
//
//    @PutMapping("/{orderId}/complete")
//    public ResponseEntity<Order> complete(@PathVariable final UUID orderId) {
//        return ResponseEntity.ok(orderService.complete(orderId));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Order>> findAll() {
//        return ResponseEntity.ok(orderService.findAll());
//    }
}
