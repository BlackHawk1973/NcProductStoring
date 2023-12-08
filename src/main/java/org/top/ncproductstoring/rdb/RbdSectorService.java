package org.top.ncproductstoring.rdb;

import org.springframework.stereotype.Service;
import org.top.ncproductstoring.entity.Sector;
import org.top.ncproductstoring.rdb.repository.SectorRepository;
import org.top.ncproductstoring.service.SectorService;

import java.util.Optional;

@Service
public class RbdSectorService implements SectorService {
    private final SectorRepository sectorRepository;

    public RbdSectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Override
    public Iterable<Sector> findAll() {
        return sectorRepository.findAll();
    }

    @Override
    public Optional<Sector> findById(Integer id) {
        return sectorRepository.findById(id);
    }

    @Override
    public Optional<Sector> save(Sector sector) throws Exception {
        return Optional.of(sectorRepository.save(sector));
    }

    @Override
    public Optional<Sector> deleteById(Integer id) {
        Optional<Sector> deleted = sectorRepository.findById(id);
        if (deleted.isPresent()) {
            sectorRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<Sector> update(Sector sector) throws Exception {
        // 1. проверить, есть ли объект с таким id
        Optional<Sector> updated = sectorRepository.findById(sector.getId());
        if (updated.isPresent()) {
            // если есть, то обновить его
            Optional.of(sectorRepository.save(sector));
        }
        return updated;
    }
}
