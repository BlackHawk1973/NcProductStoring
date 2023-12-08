package org.top.ncproductstoring.service;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.NcProductCause;

import java.util.Optional;

@Service
public interface NcProductCauseService {
    //Получить все записи
    Iterable<NcProductCause> findAll();

    //Получить по id
    Optional<NcProductCause> findById(Integer id);

    //Добавить новую запись
    Optional<NcProductCause> save(NcProductCause ncProductCause) throws Exception;

    //Удалить запись
    Optional<NcProductCause> deleteById(Integer id);

    //Редактировать запись
    Optional<NcProductCause> update(NcProductCause ncProductCause) throws Exception;
}
