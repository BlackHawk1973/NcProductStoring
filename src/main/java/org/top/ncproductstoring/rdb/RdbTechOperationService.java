package org.top.ncproductstoring.rdb;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.TechOperation;
import org.top.ncproductstoring.rdb.repository.TechOperationRepository;
import org.top.ncproductstoring.service.TechOperationService;

import java.util.Optional;

@Service
public class RdbTechOperationService implements TechOperationService {
    private final TechOperationRepository techOperationRepository;

    public RdbTechOperationService(TechOperationRepository techOperationRepository) {
        this.techOperationRepository = techOperationRepository;
    }

    @Override
    public Iterable<TechOperation> findAll() {
        return techOperationRepository.findAll();
    }

    @Override
    public Optional<TechOperation> findById(Integer id) {
        return techOperationRepository.findById(id);
    }

    @Override
    public Optional<TechOperation> save(TechOperation techOperation) throws Exception {
        return Optional.of(techOperationRepository.save(techOperation));
    }

    @Override
    public Optional<TechOperation> deleteById(Integer id) {
        Optional<TechOperation> deleted = techOperationRepository.findById(id);
        if(deleted.isPresent()) {
            techOperationRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<TechOperation> update(TechOperation techOperation) throws Exception {
        Optional<TechOperation> updated = techOperationRepository.findById(techOperation.getId());
        // 1. проверить, есть ли объект с таким id
        if (updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(techOperationRepository.save(techOperation));
        }
        return updated;
    }
}
