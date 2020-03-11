package kitchenpos.table.domain;

import java.util.List;
import java.util.Optional;

public interface TableGroupDao {
    TableGroup save(TableGroup entity);

    Optional<TableGroup> findById(Long id);

    List<TableGroup> findAll();
}
