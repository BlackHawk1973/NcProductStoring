package org.top.ncproductstoring.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ncproductstoring.entity.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {
}
