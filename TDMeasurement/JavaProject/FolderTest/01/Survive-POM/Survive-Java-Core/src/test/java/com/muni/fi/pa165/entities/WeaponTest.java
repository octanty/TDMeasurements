package com.muni.fi.pa165.entities;

import com.muni.fi.pa165.enums.WeaponClass;
import com.muni.fi.pa165.enums.WeaponType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aubrey Oosthuizen
 */
public class WeaponTest {

    static Weapon weapon;

    public WeaponTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        weapon = new Weapon();
        weapon.setName("AK47");
        weapon.setCaliber(Double.MIN_NORMAL);
        weapon.setDescription("Africa's favourite");
        weapon.setRounds(44);
        weapon.setRange(100);
        weapon.setWeaponclass(WeaponClass.RANGED);
        weapon.setWeapontype(WeaponType.GUN);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Weapon.
     */
    @Test
    public void testGetName() {
        Weapon instance = weapon;
        String expResult = "AK47";
        String result = instance.getName();
        assertEquals(expResult, result);

    }

    /**
     * Test of setName method, of class Weapon.
     */
    @Test
    public void testSetName() {
        String name = "UZI";
        Weapon instance = new Weapon();
        instance.setName(name);
        assertEquals(name, instance.getName());

    }

    /**
     * Test of getWeaponType method, of class Weapon.
     */
    @Test
    public void testGetWeaponType() {
        Weapon instance = weapon;
        WeaponType expResult = WeaponType.GUN;
        WeaponType result = instance.getWeapontype();
        assertEquals(expResult, result);

    }

    /**
     * Test of setWeaponType method, of class Weapon.
     */
    @Test
    public void testSetWeaponType() {
        WeaponType weaponType = WeaponType.EXPLOSIVE;
        Weapon instance = new Weapon();
        instance.setWeapontype(weaponType);
        assertEquals(weaponType, instance.getWeapontype());
    }

    /**
     * Test of getWeaponClass method, of class Weapon.
     */
    @Test
    public void testGetWeaponClass() {
        Weapon instance = weapon;
        WeaponClass expResult = WeaponClass.RANGED;
        WeaponClass result = instance.getWeaponclass();
        assertEquals(expResult, result);

    }

    /**
     * Test of setWeaponClass method, of class Weapon.
     */
    @Test
    public void testSetWeaponClass() {
        WeaponClass weaponClass = WeaponClass.MELEE;
        Weapon instance = new Weapon();
        instance.setWeaponclass(weaponClass);
        assertEquals(weaponClass, instance.getWeaponclass());
    }

    /**
     * Test of getRange method, of class Weapon.
     */
    @Test
    public void testGetRange() {
        Weapon instance = weapon;
        Integer expResult = 100;
        Integer result = instance.getRange();
        assertEquals(expResult, result);

    }

    /**
     * Test of setRange method, of class Weapon.
     */
    @Test
    public void testSetRange() {
        Integer range = 333;
        Weapon instance = new Weapon();
        instance.setRange(range);
        assertEquals(range, instance.getRange());
    }

    /**
     * Test of getCaliber method, of class Weapon.
     */
    @Test
    public void testGetCaliber() {
        Weapon instance = weapon;
        double expResult = 0.44;
        double result = instance.getCaliber();
        assertEquals(expResult, result, 0.44);

    }

    /**
     * Test of setCaliber method, of class Weapon.
     */
    @Test
    public void testSetCaliber() {
        double caliber = 0.25;
        Weapon instance = new Weapon();
        instance.setCaliber(caliber);
        assertEquals(caliber, instance.getCaliber(), 0.25);
    }

    /**
     * Test of getRounds method, of class Weapon.
     */
    @Test
    public void testGetRounds() {
        Weapon instance = weapon;
        Integer expResult = 44;
        Integer result = instance.getRounds();
        assertEquals(expResult, result);

    }

    /**
     * Test of setRounds method, of class Weapon.
     */
    @Test
    public void testSetRounds() {
        Integer rounds = 99;
        Weapon instance = new Weapon();
        instance.setRounds(rounds);
        assertEquals(rounds, instance.getRounds(), 99);
    }

    /**
     * Test of getDescription method, of class Weapon.
     */
    @Test
    public void testGetDescription() {
        Weapon instance = weapon;
        String expResult = "Africa's favourite";
        String result = instance.getDescription();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDescription method, of class Weapon.
     */
    @Test
    public void testSetDescription() {
        String description = "One killing machine";
        Weapon instance = new Weapon();
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
    }

}