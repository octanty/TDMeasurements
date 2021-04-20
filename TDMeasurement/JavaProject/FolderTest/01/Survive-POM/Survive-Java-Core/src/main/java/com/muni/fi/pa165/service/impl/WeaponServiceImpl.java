package com.muni.fi.pa165.service.impl;

import com.muni.fi.pa165.dao.WeaponDao;
import com.muni.fi.pa165.dto.WeaponDto;
import com.muni.fi.pa165.entities.Weapon;
import com.muni.fi.pa165.enums.WeaponClass;
import com.muni.fi.pa165.enums.WeaponType;
import com.muni.fi.pa165.service.WeaponService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michal Vinkler
 */
@Service
public class WeaponServiceImpl implements WeaponService {

    @Inject
    private WeaponDao weaponDao;
    @Inject
    private Mapper mapper;

    @Override
   @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST', 'ROLE_ANONYMOUS')")
    public WeaponDto save(WeaponDto dto) {
        Weapon entity = mapper.map(dto, Weapon.class);
        weaponDao.save(entity);
        return mapper.map(entity, WeaponDto.class);
    }

    @Override
   @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST', 'ROLE_ANONYMOUS')")
    public WeaponDto update(WeaponDto dto) {
        Weapon entity = mapper.map(dto, Weapon.class);
        weaponDao.update(entity);
        return mapper.map(entity, WeaponDto.class);
    }

    @Override
   @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST', 'ROLE_ANONYMOUS')")
    public WeaponDto findById(Long id) {
        return mapper.map(weaponDao.findById(id), WeaponDto.class);
    }

    public void setDao(WeaponDao dao) {
        this.weaponDao = dao;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
   @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST', 'ROLE_ANONYMOUS')")
    public List<WeaponDto> findAll() {
        List<WeaponDto> dtoList = new ArrayList<>();
        for (Weapon o : weaponDao.findAll()) {
            dtoList.add(this.mapper.map(o, WeaponDto.class));
        }
        return dtoList;
    }

    @Override
  @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST', 'ROLE_ANONYMOUS')")
    public void delete(Long id) {
        weaponDao.delete(id);
    }

    @Override
    public WeaponDto checkAndSave(WeaponDto dto) {

        WeaponDto mapped = new WeaponDto();
        mapped.setName(dto.getName() != null ? dto.getName() : "No name");
        mapped.setWeaponType(dto.getWeaponType() != null ? dto.getWeaponType() : WeaponType.GUN);
        mapped.setWeaponClass(dto.getWeaponClass() != null ? dto.getWeaponClass() : WeaponClass.RANGED);
        mapped.setCaliber(dto.getCaliber() != null ? dto.getCaliber() : Double.parseDouble("0"));
        mapped.setRange(dto.getRange() != null ? dto.getRange() : 0);
        mapped.setRounds(dto.getRounds() != null ? dto.getRounds() : 0);
        mapped.setDescription(dto.getDescription() != null ? dto.getDescription() : "No Description");

        dto.setId(null);
        dto = save(mapped);


        return dto;
    }
}
