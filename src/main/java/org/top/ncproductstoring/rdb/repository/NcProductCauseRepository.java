package org.top.ncproductstoring.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ncproductstoring.entity.NcProductCause;

@Repository
public interface NcProductCauseRepository extends CrudRepository<NcProductCause, Integer> {
}
