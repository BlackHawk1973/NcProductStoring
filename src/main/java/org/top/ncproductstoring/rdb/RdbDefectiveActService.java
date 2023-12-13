package org.top.ncproductstoring.rdb;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.DefectiveAct;
import org.top.ncproductstoring.rdb.repository.DefectiveActRepository;
import org.top.ncproductstoring.service.DefectiveActService;

import java.util.Optional;

@Service
public class RdbDefectiveActService implements DefectiveActService {

    //Внедрение зависимости DefectiveActRepository
    private final DefectiveActRepository defectiveActRepository;

    public RdbDefectiveActService(DefectiveActRepository defectiveActRepository) {
        this.defectiveActRepository = defectiveActRepository;
    }

    @Override
    public Iterable<DefectiveAct> findAll() {
        return defectiveActRepository.findAll();
    }

    @Override
    public Optional<DefectiveAct> findById(Integer id) {
        return defectiveActRepository.findById(id);
    }

    @Override
    public Optional<DefectiveAct> save(DefectiveAct defectiveAct) throws Exception {
        return Optional.of(defectiveActRepository.save(defectiveAct));
    }

    @Override
    public Optional<DefectiveAct> deleteById(Integer id) {
        Optional<DefectiveAct> deleted = defectiveActRepository.findById(id);
        if (deleted.isPresent())
            defectiveActRepository.deleteById(id);
        return deleted;
    }

    @Override
    public Optional<DefectiveAct> update(DefectiveAct defectiveAct) throws Exception {
        // 1. проверить, есть ли объект с таким id
        Optional<DefectiveAct> updated = defectiveActRepository.findById(defectiveAct.getId());
        if (updated.isPresent())
            updated = Optional.of(defectiveActRepository.save(defectiveAct));
        return updated;
    }
}
