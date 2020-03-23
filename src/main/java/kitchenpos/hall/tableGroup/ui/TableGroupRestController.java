package kitchenpos.hall.tableGroup.ui;

import kitchenpos.hall.tableGroup.application.TableGroupBo;
import kitchenpos.hall.tableGroup.domain.TableGroup;
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

    @DeleteMapping("/api/table-groups/{tableGroupId}")
    public ResponseEntity<Void> ungroup(@PathVariable final Long tableGroupId) {
        tableGroupBo.ungroup(tableGroupId);
        return ResponseEntity.noContent()
                .build()
                ;
    }
}
