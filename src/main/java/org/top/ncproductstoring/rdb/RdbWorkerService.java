package org.top.ncproductstoring.rdb;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.Worker;
import org.top.ncproductstoring.rdb.repository.WorkerRepository;
import org.top.ncproductstoring.service.WorkerService;

import java.util.Optional;

@Service
public class RdbWorkerService implements WorkerService {

    private final WorkerRepository workerRepository;

    public RdbWorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Iterable<Worker> findAll() {
        return workerRepository.findAll();
    }

    @Override
    public Optional<Worker> findById(Integer id) {
        return workerRepository.findById(id);
    }

    @Override
    public Optional<Worker> save(Worker worker) {
        return Optional.of(workerRepository.save(worker));
    }

    @Override
    public Optional<Worker> deleteById(Integer id) {
        Optional<Worker> deleted = workerRepository.findById(id);
        if (deleted.isPresent()) {
            workerRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<Worker> update(Worker worker) {
        // 1. проверить, есть ли объект с таким id
        Optional<Worker> updated = workerRepository.findById(worker.getId());
        if (updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(workerRepository.save(worker));
        }
        return updated;
    }
}