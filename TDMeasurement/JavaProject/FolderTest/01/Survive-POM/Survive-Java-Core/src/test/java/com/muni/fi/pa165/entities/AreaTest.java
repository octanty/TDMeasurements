package com.muni.fi.pa165.entities;

import com.muni.fi.pa165.enums.TerrainType;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Aubrey Oosthuizen 
 */
public class AreaTest {

    static Area area;

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
        area = new Area();
        area.setName("Jungle");
        area.setDescription("Area baby");
        area.setTerrain(TerrainType.JUNGLE);
    }

    /**
     * Test of getName method, of class Area.
     */
    @Test
    public void testGetName() {
        Area instance = area;
        String expResult = "Jungle";
        String result = instance.getName();
        assertEquals(expResult, result);

    }

    /**
     * Test of setName method, of class Area.
     */
    @Test
    public void testSetName() {
        String name = "Jungle";
        Area instance = new Area();
        instance.setName(name);
        assertEquals(name, area.getName());

    }

    /**
     * Test of getTerrain method, of class Area.
     */
    @Test
    public void testGetTerrain() {
        Area instance = area;
        TerrainType expResult = TerrainType.JUNGLE;
        TerrainType result = instance.getTerrain();
        assertEquals(expResult, result);

    }

    /**
     * Test of setTerrain method, of class Area.
     */
    @Test
    public void testSetTerrain() {
        TerrainType terrain = TerrainType.SAVANNA;
        Area instance = new Area();
        instance.setTerrain(terrain);
        assertEquals(instance.getTerrain(), terrain);
    }

    /**
     * Test of getDescription method, of class Area.
     */
    @Test
    public void testGetDescription() {
        Area instance = area;
        String expResult = "Area baby";
        String result = instance.getDescription();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDescription method, of class Area.
     */
    @Test
    public void testSetDescription() {
        String description = "Hell on earth";
        Area instance = new Area();
        instance.setDescription(description);
        assertEquals(instance.getDescription(), description);
    }

    /**
     * Test of hashCode method, of class Area.
     */
    @Test
    public void testHashCode() {
        Area instance = area;
        int expResult = area.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Area.
     */
    @Test
    public void testEquals() {
        Object obj = new Object();
        Area instance = area;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
}