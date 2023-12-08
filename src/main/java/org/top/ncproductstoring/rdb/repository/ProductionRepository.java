package org.top.ncproductstoring.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ncproductstoring.entity.Production;

@Repository
public interface ProductionRepository extends CrudRepository<Production, Integer> {
}
