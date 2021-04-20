package com.pa165.bookingmanager.service.impl;

import com.pa165.bookingmanager.convertor.impl.RoleConvertorImpl;
import com.pa165.bookingmanager.dao.RoleDao;
import com.pa165.bookingmanager.dto.RoleDto;
import com.pa165.bookingmanager.entity.RoleEntity;
import com.pa165.bookingmanager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jana Polakova, Jakub Polak, Jan Hrubes
 */
@Service("roleService")
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService
{
    @Autowired
    RoleDao roleDao;

    @Autowired
    RoleConvertorImpl roleConvertor;

    /**
     * Constructor
     */
    public RoleServiceImpl(){

    }

    /**
     * Constructor
     *
     * @param roleDao role dao
     * @param roleConvertor role convertor
     */
    public RoleServiceImpl(RoleDao roleDao, RoleConvertorImpl roleConvertor){
        if (roleDao == null){
            throw new IllegalArgumentException("RoleDao can't be null.");
        }

        if (roleConvertor == null){
            throw new IllegalArgumentException("RoleConvertor can't be null.");
        }

        this.roleDao = roleDao;
        this.roleConvertor = roleConvertor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoleDto> findAll() {
        List<RoleEntity> roleEntities = roleDao.findAll();
        List<RoleDto> roleDtos = null;

        if (roleEntities != null){
            roleDtos = roleConvertor.convertEntityListToDtoList(roleEntities);
        }

        return roleDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleDto find(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Id can't be null.");
        }

        RoleEntity roleEntity = roleDao.find(id);
        RoleDto roleDto = null;

        if (roleEntity != null){
            roleDto = roleConvertor.convertEntityToDto(roleEntity);
        }

        return roleDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void create(RoleDto roleDto) {
        if (roleDto == null){
            throw new IllegalArgumentException("RoleDto can't be null.");
        }

        roleDao.create(roleConvertor.convertDtoToEntity(roleDto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void update(RoleDto roleDto) {
        if (roleDto == null){
            throw new IllegalArgumentException("RoleDto can't be null.");
        }

        RoleEntity roleEntity = roleDao.find(roleDto.getId());

        if (roleEntity != null){
            roleConvertor.convertDtoToEntity(roleDto, roleEntity);
            roleDao.update(roleEntity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(RoleDto roleDto) {
        if (roleDto == null){
            throw new IllegalArgumentException("RoleDto can't be null.");
        }

        RoleEntity roleEntity = roleDao.find(roleDto.getId());

        if (roleEntity != null){
            roleDao.delete(roleEntity);
        }
    }
}
