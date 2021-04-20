package com.muni.fi.pa165.dao.jpa.impl;

import com.muni.fi.pa165.dao.AreaDao;
import com.muni.fi.pa165.dao.gen.AbstractDaoIntegrationTest;
import com.muni.fi.pa165.entities.Area;
import com.muni.fi.pa165.enums.TerrainType;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Aubrey Oosthuizen 
 */
public class AreaDaoImplTest extends AbstractDaoIntegrationTest {

    @Autowired
    AreaDao dao;
    Area entity;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        entity = new Area();
        entity.setName("CAVS");
        entity.setDescription("The lion sleeps there tonight");
        entity.setTerrain(TerrainType.JUNGLE);

        dao.save(entity);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of testCheckAvailable method, of class AreaJpaDaoImpl.
     */
    @Test
    public void testCheckAvailable() {

        String areaName = "CAVS";
        boolean expResult = true;
        boolean result = dao.checkAvailable(areaName);
        assertEquals(expResult, result);

    }
    @Test(expected = IllegalArgumentException.class)
    
    /**
     * Test of testCheckAvailable method when null is passed, of class AreaJpaDaoImpl.
     */
    public void testCheckAvailableWithNull() {

        dao.checkAvailable(null);

    }
    
/**
     * Test of checkAvailable method with not existing name, of class AreaJpaDaoImpl.
     */
        @Test
        public void testCheckAvailableWithNonExistingName() {
        String areaName = "TRALALA";
        boolean result = dao.checkAvailable(areaName);
        assertEquals(false, result);

    }
}