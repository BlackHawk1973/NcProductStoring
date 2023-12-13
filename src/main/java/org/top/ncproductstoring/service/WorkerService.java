package org.top.ncproductstoring.service;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.Worker;

import java.util.Optional;

@Service
public interface WorkerService {
    //Получить все записи
    Iterable<Worker> findAll();

    //Получить по id
    Optional<Worker> findById(Integer id);

    //Добавить новую запись
    Optional<Worker> save(Worker worker);

    //Удалить запись
    Optional<Worker> deleteById(Integer id);

    //Редактировать запись
    Optional<Worker> update(Worker worker);
}
