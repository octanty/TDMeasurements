package com.muni.fi.pa165.dao.jpa.impl;

import com.muni.fi.pa165.dao.WeaponDao;
import com.muni.fi.pa165.dao.gen.AbstractDaoIntegrationTest;
import com.muni.fi.pa165.entities.Weapon;
import com.muni.fi.pa165.enums.WeaponClass;
import com.muni.fi.pa165.enums.WeaponType;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Aubrey Oosthuizen
 */
public class WeaponDaoImplTest extends AbstractDaoIntegrationTest {

    @Autowired
    WeaponDao dao;
    private Weapon entity;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        entity = new Weapon();
        entity.setName("TESTAK47");
        entity.setCaliber(Double.valueOf(0.88));
        entity.setDescription("Africa's favourite");
        entity.setRounds(44);
        entity.setRange(100);
        entity.setWeaponclass(WeaponClass.RANGED);
        entity.setWeapontype(WeaponType.GUN);
        dao.save(entity);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of checkAvailable method, of class WeaponJpaDaoImpl.
     */
    @Test
    public void testCheckAvailable() {
        String weaponName = "TESTAK47";

        boolean expResult = true;
        boolean result = dao.checkAvailable(weaponName);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of checkAvailable method when null is passed, of class WeaponJpaDaoImpl.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckAvailableWithNull() {
        
        dao.checkAvailable(null);
        
    }
    /**
     * Test of checkAvailable method with not existing name, of class WeaponJpaDaoImpl.
     */
        @Test
        public void testCheckAvailableWithNonExistingName() {
        String weaponName = "BLABLA";
        boolean result = dao.checkAvailable(weaponName);
        assertEquals(false, result);

    }
        
}