package com.muni.fi.pa165.dao.jpa.impl;

import com.muni.fi.pa165.dao.SystemUserDao;
import com.muni.fi.pa165.dao.gen.AbstractDaoIntegrationTest;
import com.muni.fi.pa165.entities.SystemUser;
import com.muni.fi.pa165.enums.UserGroup;
import com.muni.fi.pa165.enums.UserStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Maria
 */
public class SystemUserDaoImplTest extends AbstractDaoIntegrationTest{

    @Autowired
    SystemUserDao dao;
    
    SystemUser entity;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        entity = new SystemUser();       
        entity.setUsername("user");
        entity.setPassword("1234");
        entity.setEnabled(UserStatus.ENABLED);
        entity.setAuthority(UserGroup.ROLE_USER);
        dao.save(entity);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Delete method of class SystemUserJpaDaoImpl
     */
    @Test
    public void testDelete() {
        dao.delete(entity.getId());
        SystemUser deletedUser = dao.findById(entity.getId());
        Assert.assertNull(deletedUser);
    }
     /**
     * Test of createTest method of class SystemUserJpaDaoImpl
     */
    @Test
    public void createTest() {
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getUsername());
    }
     /**
     * Test of findTest method of class SystemUserJpaDaoImpl
     */
    @Test
    public void findTest() {
        SystemUser systemUserFound = dao.findByName(entity.getUsername());
        Assert.assertNotNull(systemUserFound);
    }
}