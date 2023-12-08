package org.top.ncproductstoring.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ncproductstoring.entity.NcProductType;

@Repository
public interface NcProductTypeRepository extends CrudRepository<NcProductType, Integer> {
}
