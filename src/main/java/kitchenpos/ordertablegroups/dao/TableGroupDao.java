package kitchenpos.ordertablegroups.dao;

import kitchenpos.ordertablegroups.model.TableGroup;

import java.util.List;
import java.util.Optional;

public interface TableGroupDao {
    TableGroup save(TableGroup entity);

    Optional<TableGroup> findById(Long id);

    List<TableGroup> findAll();
}
