package org.top.ncproductstoring.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ncproductstoring.entity.DefectiveAct;

@Repository
public interface DefectiveActRepository extends CrudRepository<DefectiveAct, Integer> {
}
