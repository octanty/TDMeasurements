package com.muni.fi.pa165.service.impl;

import com.muni.fi.pa165.dao.SystemUserDao;
import com.muni.fi.pa165.dto.SystemUserDto;
import com.muni.fi.pa165.entities.SystemUser;
import com.muni.fi.pa165.enums.UserGroup;
import com.muni.fi.pa165.service.SystemUserService;
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
 * @author Maria
 */
@Service
@Transactional
public class SystemUserServiceImpl implements SystemUserService {

    private static final Logger logger = Logger.getLogger(SystemUserServiceImpl.class.getName());
    @Inject
    private SystemUserDao systemUserDao;
    @Inject
    private Mapper mapper;

 
    @Override
        public SystemUserDto save(SystemUserDto dto) {
        SystemUser entity = mapper.map(dto, SystemUser.class);
        systemUserDao.save(entity);
        return mapper.map(entity, SystemUserDto.class);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public SystemUserDto update(SystemUserDto dto) {
        SystemUser entity = mapper.map(dto, SystemUser.class);
        systemUserDao.update(entity);
        return mapper.map(entity, SystemUserDto.class);
    }
    
    
    @Override
    public SystemUserDto findById(Long id) {
        return mapper.map(systemUserDao.findById(id), SystemUserDto.class);
    }

    @Override
    public SystemUserDto findByName(String username) {
        return mapper.map(systemUserDao.findByName(username), SystemUserDto.class);
    }

  
    public void setDao(SystemUserDao dao) {
        this.systemUserDao = dao;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public List<SystemUserDto> findAll() {
        List<SystemUserDto> dtoList = new ArrayList<>();
        for (SystemUser o : systemUserDao.findAll()) {
            dtoList.add(this.mapper.map(o, SystemUserDto.class));
        }
        return dtoList;
    }


    public SystemUserDto checkAndSave(SystemUserDto dto) {
        
        SystemUserDto mapped = new SystemUserDto();      
        mapped.setUsername(dto.getUsername() != null ? dto.getUsername() : "default_user");      
        mapped.setAuthority(dto.getAuthority() != null ? dto.getAuthority() : UserGroup.ROLE_USER);
        mapped.setPassword(dto.getPassword() != null ? dto.getPassword() : "1234");
        dto = save(mapped);
        return dto;
    }


    
    
    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST')")
    public void delete(Long id)
    {
        systemUserDao.delete(id);
        
    }

 
}
