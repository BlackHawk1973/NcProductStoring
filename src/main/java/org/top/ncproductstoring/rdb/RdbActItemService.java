package org.top.ncproductstoring.rdb;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.ActItem;
import org.top.ncproductstoring.rdb.repository.ActItemRepository;
import org.top.ncproductstoring.service.ActItemService;

import java.util.Optional;

@Service
public class RdbActItemService implements ActItemService {

    //Внедрение зависимости ActItemRepository
    private final ActItemRepository actItemRepository;

    public RdbActItemService(ActItemRepository actItemRepository) {
        this.actItemRepository = actItemRepository;
    }

    @Override
    public Iterable<ActItem> findAll() {
        return actItemRepository.findAll();
    }

    @Override
    public Optional<ActItem> findById(Long id) {
        return actItemRepository.findById(id);
    }


    @Override
    public Optional<ActItem> save(ActItem actItem) throws Exception {
        return Optional.of(actItemRepository.save(actItem));
    }

    @Override
    public Optional<ActItem> deleteById(Long id) {
        Optional<ActItem> deleted = actItemRepository.findById(id);
        if (deleted.isPresent())
            actItemRepository.deleteById(id);
        return deleted;
    }

    @Override
    public Optional<ActItem> update(ActItem actItem) throws Exception {
        // 1. проверить, есть ли объект с таким id
        Optional<ActItem> updated = actItemRepository.findById(actItem.getId());
        if (updated.isPresent())
            updated = Optional.of(actItemRepository.save(actItem));
        return updated;
    }
}
