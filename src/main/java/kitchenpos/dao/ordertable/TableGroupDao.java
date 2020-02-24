package kitchenpos.dao.ordertable;

import kitchenpos.model.ordertable.TableGroup;

import java.util.List;
import java.util.Optional;

public interface TableGroupDao {
    TableGroup save(TableGroup entity);

    Optional<TableGroup> findById(Long id);

    List<TableGroup> findAll();
}
