package com.muni.fi.pa165.service.impl;

import com.muni.fi.pa165.dao.MonsterWeaponDao;
import com.muni.fi.pa165.dto.MonsterWeaponDto;
import com.muni.fi.pa165.dto.MonsterWeaponPkDto;
import com.muni.fi.pa165.entities.MonsterWeapon;
import com.muni.fi.pa165.entities.MonsterWeaponPK;
import com.muni.fi.pa165.service.MonsterWeaponService;
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
public class MonsterWeaponServiceImpl implements MonsterWeaponService {

    @Inject
    private MonsterWeaponDao monsterWeaponDao;
    @Inject
    private Mapper mapper;

    /**
     *
     * @return CompositeKey of MonsterWeaponClass
     */
    @Override
    public MonsterWeaponPkDto getCompositeKey(Long monsterId, Long weaponId) {
        return new MonsterWeaponPkDto(monsterId, weaponId);
    }

    /**
     *
     * @return MonsterWeaponDto of MonsterWeaponClass
     */
    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public MonsterWeaponDto save(MonsterWeaponDto dto) {
        MonsterWeapon entity = mapper.map(dto, MonsterWeapon.class);
        MonsterWeaponPK pk = mapper.map(new MonsterWeaponPkDto(dto.getMonster().getId(), dto.getWeapon().getId()), MonsterWeaponPK.class);
        entity.setMonsterweaponPK(pk);
        entity = monsterWeaponDao.save(entity);
        return mapper.map(entity, MonsterWeaponDto.class);
    }

    @Override
   @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public MonsterWeaponDto update(MonsterWeaponDto dto) {

        MonsterWeapon entity = mapper.map(dto, MonsterWeapon.class);
        MonsterWeaponPK pk = mapper.map(new MonsterWeaponPkDto(dto.getMonster().getId(), dto.getWeapon().getId()), MonsterWeaponPK.class);
        entity.setMonsterweaponPK(pk);
        monsterWeaponDao.update(entity);
        return mapper.map(entity, MonsterWeaponDto.class);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public void delete(MonsterWeaponDto dto) {

        monsterWeaponDao.delete(mapper.map(dto, MonsterWeapon.class));

    }

    public void setDao(MonsterWeaponDao dao) {
        this.monsterWeaponDao = dao;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public List<MonsterWeaponDto> findByMonsterId(Long id) {
        List<MonsterWeapon> mw = monsterWeaponDao.getByMonsterId(id);
        List<MonsterWeaponDto> result = new ArrayList<>();
        for (MonsterWeapon monsterW : mw) {
            result.add(mapper.map(monsterW, MonsterWeaponDto.class));
        }
        return result;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public List<MonsterWeaponDto> findByWeaponId(Long id) {
        List<MonsterWeaponDto> result = new ArrayList<>();
        for (MonsterWeapon monsterW : monsterWeaponDao.getByWeaponId(id)) {
            result.add(mapper.map(monsterW, MonsterWeaponDto.class));
        }
        return result;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public List<MonsterWeaponDto> findAll() {
        List<MonsterWeaponDto> dtoList = new ArrayList<>();
        for (MonsterWeapon o : monsterWeaponDao.findAll()) {
            MonsterWeaponDto dto = this.mapper.map(o, MonsterWeaponDto.class);

            dtoList.add(dto);

        }

        return dtoList;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public void delete(MonsterWeaponPkDto id) {
        MonsterWeaponPK pk = mapper.map(id, MonsterWeaponPK.class);
        monsterWeaponDao.delete(pk);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public MonsterWeaponDto findById(Long monsterId, Long weaponId) {

        MonsterWeaponPkDto pk = new MonsterWeaponPkDto(monsterId, weaponId);
        MonsterWeapon entity = monsterWeaponDao.findById(mapper.map(pk, MonsterWeaponPK.class));
        return mapper.map(entity, MonsterWeaponDto.class);
    }

    public MonsterWeaponDto findByMonsterId(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
