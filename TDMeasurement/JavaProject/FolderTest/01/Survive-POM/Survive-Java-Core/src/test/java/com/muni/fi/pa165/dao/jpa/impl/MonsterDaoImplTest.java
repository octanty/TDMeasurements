package com.muni.fi.pa165.dao.jpa.impl;

import com.muni.fi.pa165.dao.MonsterDao;
import com.muni.fi.pa165.dao.gen.AbstractDaoIntegrationTest;
import com.muni.fi.pa165.entities.Monster;
import com.muni.fi.pa165.enums.MonsterClass;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Auron
 */
public class MonsterDaoImplTest extends AbstractDaoIntegrationTest {

    @Autowired
    MonsterDao dao;
    Monster entity;

    /**
     *
     */
    @Before
    public void setUp() {
        entity = new Monster();
        entity.setAgility(11.0);
        entity.setDangerlevel(22.4);
        entity.setDescription("Headless Zombie");
        entity.setHeight(11.4);
        entity.setImagepath("C:\\image.png");
        entity.setMonsterclass(MonsterClass.ZOMBIE);
        entity.setStamina(11.5);
        entity.setStrength(11.8);
        entity.setWeight(11.2);
        entity.setName("HeadlessNick");
        dao.save(entity);

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
    @Before
    public void setUpClass() {
    }

    /**
     * Test of checkAvailable method, of class MonsterJpaDaoImpl.
     */
    @Test
    public void testCheckAvailable() {
        MonsterClass monsterClass = MonsterClass.ZOMBIE;
        assertTrue(dao.checkAvailable(monsterClass));
    }
    /**
     * Test of checkAvailable method when null is passed, of class MonsterJpaDaoImpl.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckAvailableWithNull() {

        dao.checkAvailable(null);

    }
    /**
     * Test of checkAvailable method with not existing name, of class MonsterJpaDaoImpl.
     */
        @Test
        public void testCheckAvailableWithNonExistingName() {
        boolean result = dao.checkAvailable(MonsterClass.MUTANT);
        assertEquals(false, result);

    }
}