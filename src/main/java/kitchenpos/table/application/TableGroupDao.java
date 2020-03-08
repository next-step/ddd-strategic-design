package kitchenpos.table.application;

import java.util.List;
import java.util.Optional;
import kitchenpos.table.domain.TableGroup;

public interface TableGroupDao {
    TableGroup save(TableGroup entity);

    Optional<TableGroup> findById(Long id);

    List<TableGroup> findAll();
}
