package org.top.ncproductstoring.service;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.NcProductType;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
public interface NcProductTypeService {
    //Получить все записи
    Iterable<NcProductType> findAll();

    //Получить по id
    Optional<NcProductType> findById(Integer id);

    //Добавить новую запись
    Optional<NcProductType> save(NcProductType ncProductType) throws Exception;

    //Удалить запись
    Optional<NcProductType> deleteById(Integer id);

    //Редактировать запись
    Optional<NcProductType> update(NcProductType ncProductType) throws Exception;
}
