package org.top.ncproductstoring.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ncproductstoring.entity.TechOperation;

@Repository
public interface TechOperationRepository extends CrudRepository<TechOperation, Integer> {
}
