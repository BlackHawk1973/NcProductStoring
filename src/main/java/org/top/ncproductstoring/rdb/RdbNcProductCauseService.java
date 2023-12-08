package org.top.ncproductstoring.rdb;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.NcProductCause;
import org.top.ncproductstoring.rdb.repository.NcProductCauseRepository;
import org.top.ncproductstoring.service.NcProductCauseService;

import java.util.Optional;

@Service
public class RdbNcProductCauseService implements NcProductCauseService {
    // репозиторий для выполнения операций
    private final NcProductCauseRepository ncProductCauseRepository;

    public RdbNcProductCauseService(NcProductCauseRepository ncProductCauseRepository) {
        this.ncProductCauseRepository = ncProductCauseRepository;
    }

    @Override
    public Iterable<NcProductCause> findAll() {
        return ncProductCauseRepository.findAll();
    }

    @Override
    public Optional<NcProductCause> findById(Integer id) {
        return ncProductCauseRepository.findById(id);
    }

    @Override
    public Optional<NcProductCause> save(NcProductCause ncProductCause) throws Exception {
        return Optional.of(ncProductCauseRepository.save(ncProductCause));
    }

    @Override
    public Optional<NcProductCause> deleteById(Integer id) {
        Optional<NcProductCause> deleted = findById(id);
        if(deleted.isPresent()) {
            ncProductCauseRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<NcProductCause> update(NcProductCause ncProductCause) throws Exception {
        // 1. проверить, есть ли объект с таким id
        Optional<NcProductCause> updated = findById(ncProductCause.getId());
        if(updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(ncProductCauseRepository.save(ncProductCause));
        }
        return updated;
    }
}
