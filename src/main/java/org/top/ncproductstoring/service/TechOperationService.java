package org.top.ncproductstoring.service;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.TechOperation;

import java.util.Optional;

@Service
public interface TechOperationService {
    //Получить все записи
    Iterable<TechOperation> findAll();

    //Получить по id
    Optional<TechOperation> findById(Integer id);

    //Добавить новую запись
    Optional<TechOperation> save(TechOperation techOperation) throws Exception;

    //Удалить запись
    Optional<TechOperation> deleteById(Integer id);

    //Редактировать запись
    Optional<TechOperation> update(TechOperation techOperation) throws Exception;
}
