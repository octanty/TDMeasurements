package com.muni.fi.pa165.dao.service.impl;

import com.muni.fi.pa165.dao.SystemUserDao;
import com.muni.fi.pa165.dto.SystemUserDto;
import com.muni.fi.pa165.entities.SystemUser;
import com.muni.fi.pa165.enums.UserGroup;
import com.muni.fi.pa165.enums.UserStatus;
import com.muni.fi.pa165.service.AbstractServiceIntegrationTest;
import com.muni.fi.pa165.service.impl.SystemUserServiceImpl;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 *
 * @author Auron
 */
public class SystemUserServiceImplTest extends AbstractServiceIntegrationTest {

    @Inject
    private SystemUserDao mockDAO;
    private SystemUserServiceImpl service;
    @Inject
    private Mapper mapper;

    /**
     *
     */
    @Before
    public void setUp() {
        service = new SystemUserServiceImpl();
        mockDAO = mock(SystemUserDao.class);
        service.setDao(mockDAO);
        service.setMapper(mapper);
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     *
     */
    @Test
    public void testSave() {
        SystemUserDto dto = new SystemUserDto();
       
        dto.setUsername("user");
        dto.setPassword("1234");
        dto.setEnabled(UserStatus.ENABLED);
        dto.setAuthority(UserGroup.ROLE_USER);
      

        SystemUser entity = mapper.map(dto, SystemUser.class);
        when(mockDAO.save(any(SystemUser.class))).thenReturn(entity);
        SystemUserDto returned = service.save(dto);
        assertEquals(returned, dto);
    }

    /**
     * Test of update method, of class SystemUserServiceImpl.
     */
    @Test
    public void testUpdate() {
//        System.out.println("Testing update");

     SystemUserDto dto = new SystemUserDto();
       
        dto.setUsername("user");
        dto.setPassword("1234");
        dto.setEnabled(UserStatus.ENABLED);
        dto.setAuthority(UserGroup.ROLE_USER);

        SystemUser entity = mapper.map(dto, SystemUser.class);
        when(mockDAO.update(any(SystemUser.class))).thenReturn(entity);
        SystemUserDto returned = service.update(dto);
        verify(mockDAO, times(1)).update(entity);
        verifyNoMoreInteractions(mockDAO);
        assertEquals(returned, dto);
    }

    /**
     * Test of delete method, of class SystemUserServiceImpl.
     */
    @Test
    public void testDelete() {

      SystemUserDto dto = new SystemUserDto();
        dto.setId(Long.MIN_VALUE);
        dto.setUsername("user");
        dto.setPassword("1234");
        dto.setEnabled(UserStatus.ENABLED);
        dto.setAuthority(UserGroup.ROLE_USER);

        SystemUser entity = mapper.map(dto, SystemUser.class);


        service.delete(dto.getId());

        verify(mockDAO, times(1)).delete(entity.getId());
        verifyNoMoreInteractions(mockDAO);


    }

    /**
     * Test of findById method, of class SystemUserServiceImpl.
     */
    @Test
    public void testFindById() {

       SystemUserDto dto = new SystemUserDto();
       
        dto.setUsername("user");
        dto.setPassword("1234");
        dto.setEnabled(UserStatus.ENABLED);
        dto.setAuthority(UserGroup.ROLE_USER);

        SystemUser entity = mapper.map(dto, SystemUser.class);

        when(mockDAO.findByName(dto.getUsername())).thenReturn(entity);

        SystemUserDto returned = service.findByName(dto.getUsername());
        verify(mockDAO, times(1)).findByName(dto.getUsername());
        verifyNoMoreInteractions(mockDAO);

        assertEquals(returned, dto);


    }
}