package org.top.ncproductstoring.service;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.Team;

import java.util.Optional;

@Service
public interface TeamService {
    //Получить все записи
    Iterable<Team> findAll();

    //Получить по id
    Optional<Team> findById(Integer id);

    //Добавить новую запись
    Optional<Team> save(Team team) throws Exception;

    //Удалить запись
    Optional<Team> deleteById(Integer id);

    //Редактировать запись
    Optional<Team> update(Team team) throws Exception;
}
