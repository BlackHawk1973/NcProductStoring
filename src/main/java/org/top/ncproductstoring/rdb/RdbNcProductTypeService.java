package org.top.ncproductstoring.rdb;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.NcProductType;
import org.top.ncproductstoring.rdb.repository.NcProductTypeRepository;
import org.top.ncproductstoring.service.NcProductTypeService;
import java.util.Optional;

@Service
public class RdbNcProductTypeService implements NcProductTypeService {
    // репозиторий для выполнения операций
    private final NcProductTypeRepository ncProductTypeRepository;

    public RdbNcProductTypeService(NcProductTypeRepository ncProductTypeRepository) {
        this.ncProductTypeRepository = ncProductTypeRepository;
    }

    @Override
    public Iterable<NcProductType> findAll() {
        return ncProductTypeRepository.findAll();
    }

    @Override
    public Optional<NcProductType> findById(Integer id) {
        return ncProductTypeRepository.findById(id);
    }

    @Override
    public Optional<NcProductType> save(NcProductType ncProductType) throws Exception {
        return Optional.of(ncProductTypeRepository.save(ncProductType));
    }

    @Override
    public Optional<NcProductType> deleteById(Integer id) {
        Optional<NcProductType> deleted = findById(id);
        if(deleted.isPresent()) {
            ncProductTypeRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<NcProductType> update(NcProductType ncProductType) throws Exception {
        // 1. проверить, есть ли объект с таким id
        Optional<NcProductType> updated = findById(ncProductType.getId());
        if (updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(ncProductTypeRepository.save(ncProductType));
        }
        return updated;
    }
}
