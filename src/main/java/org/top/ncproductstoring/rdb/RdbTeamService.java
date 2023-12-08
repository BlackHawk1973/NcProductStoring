package org.top.ncproductstoring.rdb;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.Team;
import org.top.ncproductstoring.rdb.repository.TeamRepository;
import org.top.ncproductstoring.service.TeamService;

import java.util.Optional;

@Service
public class RdbTeamService implements TeamService {

    private final TeamRepository teamRepository;

    public RdbTeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Iterable<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findById(Integer id) {
        return teamRepository.findById(id);
    }

    @Override
    public Optional<Team> save(Team team) throws Exception {
        return Optional.of(teamRepository.save(team));
    }

    @Override
    public Optional<Team> deleteById(Integer id) {
        Optional<Team> deleted = teamRepository.findById(id);
        if (deleted.isPresent()) {
            teamRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<Team> update(Team team) throws Exception {
        // 1. проверить, есть ли объект с таким id
        Optional<Team> updated = teamRepository.findById(team.getId());
        if (updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(teamRepository.save(team));
        }
        return updated;
    }
}
