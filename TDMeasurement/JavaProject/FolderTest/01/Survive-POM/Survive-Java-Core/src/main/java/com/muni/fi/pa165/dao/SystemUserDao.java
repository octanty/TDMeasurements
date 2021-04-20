package com.muni.fi.pa165.dao;

import com.muni.fi.pa165.dto.SystemUserDto;
import com.muni.fi.pa165.entities.SystemUser;
import java.util.List;

/**
 * Interface implemented by typed JpaDao objects containing type specific
 * methods.
 *
 * @author Maria
 */
public interface SystemUserDao extends GenericDao<SystemUser, Long> {

    public SystemUser findByName(String username);

    @Override
    public void delete(SystemUser dao);
    
}
