package com.muni.fi.pa165.service.impl;

import com.muni.fi.pa165.dao.AreaDao;
import com.muni.fi.pa165.dto.AreaDto;
import com.muni.fi.pa165.entities.Area;
import com.muni.fi.pa165.enums.TerrainType;
import com.muni.fi.pa165.service.AreaService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Auron
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService {

    private static final Logger logger = Logger.getLogger(AreaServiceImpl.class.getName());
    @Inject
    private AreaDao areaDao;
    @Inject
    private Mapper mapper;

    /**
     *
     * @param dto
     * @return
     */
    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST', 'ROLE_ANONYMOUS')")
    public AreaDto save(AreaDto dto) {

        Area entity = mapper.map(dto, Area.class);
        areaDao.save(entity);
        return mapper.map(entity, AreaDto.class);

    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST', 'ROLE_ANONYMOUS')")
    public AreaDto update(AreaDto dto) {

        Area entity = mapper.map(dto, Area.class);
        areaDao.update(entity);
        return mapper.map(entity, AreaDto.class);

    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST', 'ROLE_ANONYMOUS')")
    public AreaDto findById(Long id) {

        return mapper.map(areaDao.findById(id), AreaDto.class);

    }

    /**
     *
     * @param dao
     */
    public void setDao(AreaDao dao) {
        this.areaDao = dao;
    }

    /**
     *
     * @param mapper
     */
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST', 'ROLE_ANONYMOUS')")
    public void delete(Long id) {
        areaDao.delete(id);
    }

    /**
     *
     * @return
     */
    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST', 'ROLE_ANONYMOUS')")
    public List<AreaDto> findAll() {
        List<AreaDto> dtoList = new ArrayList<>();
        for (Area o : areaDao.findAll()) {
            dtoList.add(this.mapper.map(o, AreaDto.class));
        }
        return dtoList;
    }

    @Override
    public AreaDto checkAndSave(AreaDto dto) {

        AreaDto mapped = new AreaDto();
        mapped.setName(dto.getName() != null ? dto.getName() : "No name");
        mapped.setTerrain(dto.getTerrain() != null ? dto.getTerrain() : TerrainType.DESERT);
        mapped.setDescription(dto.getDescription() != null ? dto.getDescription() : "No Description");

        dto.setId(null);
        dto = save(mapped);

        return dto;

    }
}
