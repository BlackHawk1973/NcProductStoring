package org.top.ncproductstoring.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ncproductstoring.entity.ActItem;
import org.top.ncproductstoring.entity.DefectiveAct;

@Repository
public interface ActItemRepository extends CrudRepository<ActItem, Long> {
    Iterable<ActItem> findByDefectiveAct(DefectiveAct defectiveAct);
}
