package org.top.ncproductstoring.service;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.Production;

import java.util.Optional;

@Service
public interface ProductionService {
    //Получить все записи
    Iterable<Production> findAll();

    //Получить по id
    Optional<Production> findById(Integer id);

    //Добавить новую запись
    Optional<Production> save(Production production) throws Exception;

    //Удалить запись
    Optional<Production> deleteById(Integer id);

    //Редактировать запись
    Optional<Production> update(Production production) throws Exception;
}


