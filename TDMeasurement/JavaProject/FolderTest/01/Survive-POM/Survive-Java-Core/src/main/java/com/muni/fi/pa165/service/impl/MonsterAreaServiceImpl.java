package com.muni.fi.pa165.service.impl;

import com.muni.fi.pa165.dao.MonsterAreaDao;
import com.muni.fi.pa165.dto.MonsterAreaDto;
import com.muni.fi.pa165.dto.MonsterAreaPkDto;
import com.muni.fi.pa165.entities.MonsterArea;
import com.muni.fi.pa165.entities.MonsterAreaPK;
import com.muni.fi.pa165.service.MonsterAreaService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 *
 * @author irina
 */
@Service
@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
public class MonsterAreaServiceImpl implements MonsterAreaService {
    //private static final Logger logger = Logger.getLogger(MonsterServiceImpl.class.getName());

    @Inject
    private MonsterAreaDao monsterAreaDao;
    @Inject
    private Mapper mapper;

    /**
     *
     * @param monsterId
     * @param areaId
     * @return
     */
    @Override
    public MonsterAreaPkDto getCompositeKey(Long monsterId, Long areaId) {
        return new MonsterAreaPkDto(monsterId, areaId);
    }

    /**
     *
     * @param dto
     * @return
     */
    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public MonsterAreaDto save(MonsterAreaDto dto) {
        MonsterArea entity = mapper.map(dto, MonsterArea.class);
        Long monster = dto.getMonster().getId();
        Long area = dto.getArea().getId();
        MonsterAreaPkDto dtoPk = new MonsterAreaPkDto(monster, area);
        MonsterAreaPK pk = mapper.map(dtoPk, MonsterAreaPK.class);
        entity.setMonsterareaPK(pk);
        entity = monsterAreaDao.save(entity);
        return mapper.map(entity, MonsterAreaDto.class);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public MonsterAreaDto update(MonsterAreaDto dto) {

        MonsterArea entity = mapper.map(dto, MonsterArea.class);
        MonsterAreaPK pk = mapper.map(new MonsterAreaPkDto(dto.getMonster().getId(), dto.getArea().getId()), MonsterAreaPK.class);
        entity.setMonsterareaPK(pk);
        monsterAreaDao.update(entity);
        return mapper.map(entity, MonsterAreaDto.class);
    }

    /**
     *
     * @param dto
     */
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public void delete(MonsterAreaDto dto) {

        monsterAreaDao.delete(mapper.map(dto, MonsterArea.class));

    }

    /**
     *
     * @param dao
     */
    public void setDao(MonsterAreaDao dao) {
        this.monsterAreaDao = dao;
    }

    /**
     *
     * @param mapper
     */
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public List<MonsterAreaDto> findByMonsterId(Long id) {
        //     Monster monster = mapper.map(service.findById(id), Monster.class);
        List<MonsterArea> mw = monsterAreaDao.getByMonsterId(id);
        List<MonsterAreaDto> result = new ArrayList<>();
        for (MonsterArea monsterW : mw) {
            result.add(mapper.map(monsterW, MonsterAreaDto.class));
        }
        return result;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public List<MonsterAreaDto> findByAreaId(Long id) {
        List<MonsterAreaDto> result = new ArrayList<>();
        for (MonsterArea monsterW : monsterAreaDao.getByAreaId(id)) {
            result.add(mapper.map(monsterW, MonsterAreaDto.class));
        }
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public List<MonsterAreaDto> findAll() {
        List<MonsterAreaDto> dtoList = new ArrayList<>();
        for (MonsterArea o : monsterAreaDao.findAll()) {
            MonsterAreaDto dto = this.mapper.map(o, MonsterAreaDto.class);

            dtoList.add(dto);

        }

        return dtoList;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public void delete(MonsterAreaPkDto id) {
        MonsterAreaPK pk = mapper.map(id, MonsterAreaPK.class);
        monsterAreaDao.delete(pk);
    }

    /**
     *
     * @param monsterId
     * @param areaId
     * @return
     */
    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public MonsterAreaDto findById(Long monsterId, Long areaId) {

        MonsterAreaPkDto pk = new MonsterAreaPkDto(monsterId, areaId);
        MonsterArea entity = monsterAreaDao.findById(mapper.map(pk, MonsterAreaPK.class));
        return mapper.map(entity, MonsterAreaDto.class);
    }

}
