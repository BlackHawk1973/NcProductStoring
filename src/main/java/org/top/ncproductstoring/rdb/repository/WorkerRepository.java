package org.top.ncproductstoring.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ncproductstoring.entity.Worker;

@Repository
public interface WorkerRepository extends CrudRepository<Worker, Integer> {
}
