/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bottler.dao;

import cz.muni.fi.pa165.bottler.data.dao.StampDao;
import cz.muni.fi.pa165.bottler.data.dao.StampDaoImpl;
import cz.muni.fi.pa165.bottler.data.model.Stamp;
import java.util.List;
import javax.persistence.EntityManager;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
public class StampDaoImplTest extends TestCase {

    private StampDao stampDao;
    private EntityManager em;
    private BottlerTestDb testDb;

    public StampDaoImplTest(String testName) {
        super(testName);
        testDb = new BottlerTestDb();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //start db and get entity manager
        testDb.startDb();
        em = testDb.getEntityManager();

        // dao result dao
        stampDao = new StampDaoImpl(em);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        testDb.stopDb();
    }

    /**
     * When creating null stamp, IllegalArgumentException should be thrown
     */
    public void testCreateStampWithNull() {

        Throwable exception = null;

        try {
            Stamp stamp = null;
            em.getTransaction().begin();
            stampDao.create(stamp);
            em.getTransaction().commit();
        } catch (IllegalArgumentException ex) {
            exception = ex;
        }

        assertNotNull("Creating null stamp should throw IllegalArgumentException", exception);

    }

    /**
     * When creating a valid stamp, it should get an ID and be stored into DB
     */
    public void testCreateValidStamp() {

        Stamp s = MockFactory.on(Stamp.class).create(null, false);
        Stamp persistedStamp;

        try {
            em.getTransaction().begin();
            persistedStamp = stampDao.create(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            fail("Error while creating a valid Stamp: " + e.getMessage());
            persistedStamp = s;
        }

        assertNotNull("Persisting stamp should assign it an id", s.getId());
        Stamp retrievedStamp = em.find(Stamp.class, s.getId());
        assertEquals("The retrieved stamp and persisted stamp shloud be the same", persistedStamp, retrievedStamp);
    }

    /**
     * When updating null stamp, IllegalArgumentException should be thrown
     */
    public void testUpdateStampWithNull() {

        Throwable exception = null;

        try {
            Stamp stamp = null;
            em.getTransaction().begin();
            stampDao.update(stamp);
            em.getTransaction().commit();
        } catch (IllegalArgumentException ex) {
            exception = ex;
        }

        assertNotNull("Updating null stamp should throw IllegalArgumentException", exception);

    }

    /**
     * When creating a valid stamp, it should get an ID and be stored into DB
     */
    public void testUpdateValidStamp() {

        

        Stamp s = MockFactory.on(Stamp.class).create(em);
        String changedStampNmuber = "214124D07C";
        s.setNumberOfStamp(changedStampNmuber);
        Stamp persistedStamp;

        try {
            em.getTransaction().begin();
            persistedStamp = stampDao.update(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            fail("Error while creating a valid Stamp: " + e.getMessage());
            persistedStamp = s;
        }

        assertNotNull("Persisting stamp should assign it an id", s.getId());
        Stamp retrievedStamp = em.find(Stamp.class, s.getId());

        assertEquals("The retrieved stamp and persisted stamp shloud be the same",
                persistedStamp, retrievedStamp);
        assertEquals("The retrieved stamp should have the changed number",
                retrievedStamp.getNumberOfStamp(), changedStampNmuber);
    }

    /**
     * When removing a valid stamp, it should get an ID and be stored into DB
     */
    public void testRemoveValidStamp() {

        

        Stamp s = MockFactory.on(Stamp.class).create(em);

        try {
            em.getTransaction().begin();
            stampDao.remove(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            fail("Error while removing a stamp: " + e.getMessage());
        }
    }

    /**
     * When removing null stamp, IllegalArgumentException should be thrown
     */
    public void testRemoveStampWithNull() {

        Throwable exception = null;

        try {
            Stamp stamp = null;
            em.getTransaction().begin();
            stampDao.remove(stamp);
            em.getTransaction().commit();
        } catch (IllegalArgumentException ex) {
            exception = ex;
        }

        assertNotNull("Updating null stamp should throw IllegalArgumentException", exception);

    }

    /**
     * Tests listing of all stamps
     */
    public void testGetAll() {

        int countInDB = 0;
        List<Stamp> allStamps = stampDao.getAll();
        assertEquals("No stamp shloud be retrieved",
                allStamps.size(), countInDB);

        Stamp stamp = MockFactory.on(Stamp.class).create(em);
        countInDB++;
        allStamps = stampDao.getAll();
        assertEquals("Retrieved stamp shloud be the same as the created one",
                allStamps.get(0), stamp);
        assertEquals("One stamp shloud be retrieved",
                allStamps.size(), countInDB);

        int count = 20;
        MockFactory.on(Stamp.class).create(em, count);
        countInDB += count;
        allStamps = stampDao.getAll();
        assertEquals("All stamps shloud be retrieved",
                allStamps.size(), countInDB);
    }

    /**
     * Tests of finding stamps by valid id
     */
    public void testFindById() {
        Stamp nullStamp = stampDao.findById(new Long(10));
        assertNull("If there is not a stamp, null shloud be returned", nullStamp);

        Stamp stamp1 = MockFactory.on(Stamp.class).create(em);
        Stamp stamp1found = stampDao.findById(stamp1.getId());
        assertEquals("Found stampt shlould be the same", stamp1, stamp1found);

        Stamp stampNotfound = stampDao.findById(stamp1.getId() + 1);
        assertNull("If there is not a stamp, null shloud be returned", nullStamp);
    }

    /**
     * Tests of finding stamps by invalid id
     */
    public void testFindByNullId() {
        Throwable exception = null;
        try {
            stampDao.findById(null);
        } catch (IllegalArgumentException ex) {
            exception = ex;
        }

        assertNotNull("Finding stamp by null id should throw IllegalArgumentException", exception);
    }

    /**
     * Tests of finding stamps by valid number
     */
    public void testFindByNumber() {
        Stamp nullStamp = stampDao.findByNumber("214124D07C");
        assertNull("If there is not a stamp, null shloud be returned", nullStamp);

        Stamp stamp1 = MockFactory.on(Stamp.class).create(em);
        Stamp stamp1found = stampDao.findByNumber(stamp1.getNumberOfStamp());
        assertEquals("Found stampt shlould be the same", stamp1, stamp1found);

        Stamp stampNotfound = stampDao.findByNumber(stamp1.getNumberOfStamp() + "x");
        assertNull("If there is not a stamp, null shloud be returned", nullStamp);
    }

    /**
     * Tests of finding stamps by invalid number
     */
    public void testFindByNullNumber() {
        Throwable exception = null;
        try {
            stampDao.findByNumber(null);
        } catch (IllegalArgumentException ex) {
            exception = ex;
        }

        assertNotNull("Finding stamp by null id should throw IllegalArgumentException", exception);
    }
}
