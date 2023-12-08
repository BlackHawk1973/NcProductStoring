package org.top.ncproductstoring.rdb;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.NcProductCause;
import org.top.ncproductstoring.entity.Production;
import org.top.ncproductstoring.rdb.repository.ProductionRepository;
import org.top.ncproductstoring.service.ProductionService;

import java.util.Optional;

@Service
public class RdbProductService implements ProductionService {
    private final ProductionRepository productionRepository;

    public RdbProductService(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    @Override
    public Iterable<Production> findAll() {
        return productionRepository.findAll();
    }

    @Override
    public Optional<Production> findById(Integer id) {
        return productionRepository.findById(id);
    }

    @Override
    public Optional<Production> save(Production production) throws Exception {
        return Optional.of(productionRepository.save(production));
    }

    @Override
    public Optional<Production> deleteById(Integer id) {
        Optional<Production> deleted = productionRepository.findById(id);
        if (deleted.isPresent()) {
            productionRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<Production> update(Production production) throws Exception {
        // 1. проверить, есть ли объект с таким id
        Optional<Production> updated = findById(production.getId());
        if(updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(productionRepository.save(production));
        }
        return updated;
    }
}
