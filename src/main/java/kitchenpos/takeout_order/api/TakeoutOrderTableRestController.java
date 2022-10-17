package kitchenpos.takeout_order.api;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import kitchenpos.takeout_order.application.TakeoutOrderTableService;
import kitchenpos.takeout_order.domain.TakeoutOrderTable;
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
public class TakeoutOrderTableRestController {
    private final TakeoutOrderTableService takeoutOrderTableService;

    public TakeoutOrderTableRestController(final TakeoutOrderTableService takeoutOrderTableService) {
        this.takeoutOrderTableService = takeoutOrderTableService;
    }

    @PostMapping
    public ResponseEntity<TakeoutOrderTable> create(@RequestBody final TakeoutOrderTable request) {
        final TakeoutOrderTable response = takeoutOrderTableService.create(request);
        return ResponseEntity.created(URI.create("/api/order-tables/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{orderTableId}/sit")
    public ResponseEntity<TakeoutOrderTable> sit(@PathVariable final UUID orderTableId) {
        return ResponseEntity.ok(takeoutOrderTableService.sit(orderTableId));
    }

    @PutMapping("/{orderTableId}/clear")
    public ResponseEntity<TakeoutOrderTable> clear(@PathVariable final UUID orderTableId) {
        return ResponseEntity.ok(takeoutOrderTableService.clear(orderTableId));
    }

    @PutMapping("/{orderTableId}/number-of-guests")
    public ResponseEntity<TakeoutOrderTable> changeNumberOfGuests(
        @PathVariable final UUID orderTableId,
        @RequestBody final TakeoutOrderTable request
    ) {
        return ResponseEntity.ok(takeoutOrderTableService.changeNumberOfGuests(orderTableId, request));
    }

    @GetMapping
    public ResponseEntity<List<TakeoutOrderTable>> findAll() {
        return ResponseEntity.ok(takeoutOrderTableService.findAll());
    }
}
