package kitchenpos.ordertablegroups.ui;

import kitchenpos.ordertablegroups.application.OrderTableGroupBo;
import kitchenpos.ordertablegroups.domain.OrderTableGroup;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class OrderTableGroupRestController {
    private final OrderTableGroupBo orderTableGroupBo;

    public OrderTableGroupRestController(final OrderTableGroupBo orderTableGroupBo) {
        this.orderTableGroupBo = orderTableGroupBo;
    }

    @PostMapping("/api/table-groups")
    public ResponseEntity<OrderTableGroup> create(@RequestBody final OrderTableGroup orderTableGroup) {
        final OrderTableGroup created = orderTableGroupBo.create(orderTableGroup);
        final URI uri = URI.create("/api/table-groups/" + created.getId());
        return ResponseEntity.created(uri)
                .body(created)
                ;
    }

    @DeleteMapping("/api/table-groups/{tableGroupId}")
    public ResponseEntity<Void> ungroup(@PathVariable final Long tableGroupId) {
        orderTableGroupBo.ungroup(tableGroupId);
        return ResponseEntity.noContent()
                .build()
                ;
    }
}
