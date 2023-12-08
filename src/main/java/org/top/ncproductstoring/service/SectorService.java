package org.top.ncproductstoring.service;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.Sector;

import java.util.Optional;

@Service
public interface SectorService {
    //Получить все записи
    Iterable<Sector> findAll();

    //Получить по id
    Optional<Sector> findById(Integer id);

    //Добавить новую запись
    Optional<Sector> save(Sector sector) throws Exception;

    //Удалить запись
    Optional<Sector> deleteById(Integer id);

    //Редактировать запись
    Optional<Sector> update(Sector sector) throws Exception;
}
