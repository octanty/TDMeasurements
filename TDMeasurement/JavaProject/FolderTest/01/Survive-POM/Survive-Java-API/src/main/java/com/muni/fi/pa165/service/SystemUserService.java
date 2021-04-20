package com.muni.fi.pa165.service;

import com.muni.fi.pa165.dto.SystemUserDto;
import java.util.List;

/**
 *
 * @author Auron
 */
public interface SystemUserService {

/**
     * Saves SystemUserDto
     *
     * @param dto object of type SystemUserDto
     */
    public SystemUserDto save(SystemUserDto dto);

    /**
     * Updates SystemUserDto
     *
     * @param dto object of type SystemUserDto
     */
    public SystemUserDto update(SystemUserDto dto);

    /**
     * Deletes SystemUserDto
     *
     * @param dto object of type SystemUserDto
     */
    
    public void delete(Long id);

    
   
    /**
     * Finds SystemUserDto by id
     *
     * @param id ID of the systemUser
     * @return SystemUser object of type SystemUserDto
     */
    public SystemUserDto findByName(String username);

    public List<SystemUserDto> findAll();
    
        public SystemUserDto findById(Long id);

    
}
