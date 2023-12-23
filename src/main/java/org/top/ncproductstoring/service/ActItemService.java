package org.top.ncproductstoring.service;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.ActItem;

import java.util.Optional;

@Service
public interface ActItemService {
    //Получить все записи
    Iterable<ActItem> findAll();

    //Получить по id
    Optional<ActItem> findById(Long id);

    //Добавить новую запись
    Optional<ActItem> save(ActItem actItem) throws Exception;

    //Удалить запись
    Optional<ActItem> deleteById(Long id);

    //Редактировать запись
    Optional<ActItem> update(ActItem actItem) throws Exception;
}
