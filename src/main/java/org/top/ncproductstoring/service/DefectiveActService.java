package org.top.ncproductstoring.service;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.DefectiveAct;

import java.util.Optional;

@Service
public interface DefectiveActService {
    //Получить все записи
    Iterable<DefectiveAct> findAll();

    //Получить по id
    Optional<DefectiveAct> findById(Integer id);

    //Добавить новую запись
    Optional<DefectiveAct> save(DefectiveAct defectiveAct) throws Exception;

    //Удалить запись
    Optional<DefectiveAct> deleteById(Integer id);

    //Редактировать запись
    Optional<DefectiveAct> update(DefectiveAct defectiveAct) throws Exception;
}
