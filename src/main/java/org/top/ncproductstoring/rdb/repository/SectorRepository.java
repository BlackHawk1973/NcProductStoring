package org.top.ncproductstoring.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ncproductstoring.entity.Sector;

@Repository
public interface SectorRepository extends CrudRepository<Sector, Integer> {
}
