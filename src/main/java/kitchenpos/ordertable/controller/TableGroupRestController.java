package kitchenpos.ordertable.controller;

import kitchenpos.ordertable.bo.TableGroupBo;
import kitchenpos.ordertable.domain.TableGroup;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class TableGroupRestController {
    private final TableGroupBo tableGroupBo;

    public TableGroupRestController(final TableGroupBo tableGroupBo) {
        this.tableGroupBo = tableGroupBo;
    }

    @PostMapping("/api/table-groups")
    public ResponseEntity<TableGroup> create(@RequestBody final TableGroup tableGroup) {
        final TableGroup created = tableGroupBo.create(tableGroup);
        final URI uri = URI.create("/api/table-groups/" + created.getId());
        return ResponseEntity.created(uri)
                .body(created)
                ;
    }

}
