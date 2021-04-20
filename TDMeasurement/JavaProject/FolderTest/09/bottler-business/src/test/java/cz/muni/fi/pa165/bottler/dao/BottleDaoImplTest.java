package cz.muni.fi.pa165.bottler.dao;

import cz.muni.fi.pa165.bottler.data.dao.BottleDao;
import cz.muni.fi.pa165.bottler.data.dao.BottleDaoImpl;
import cz.muni.fi.pa165.bottler.data.model.*;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.mockito.Mockito;
import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */
public class BottleDaoImplTest extends TestCase {

    private BottleDao bottleDao;
    private BottleDao mockedDao;
    private EntityManager em;
    private BottlerTestDb testDb;

    public BottleDaoImplTest(String testName) {
        super(testName);
        testDb = new BottlerTestDb();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testDb.startDb();
        em = testDb.getEntityManager();
        bottleDao = new BottleDaoImpl(em);
        mockedDao = Mockito.mock(BottleDao.class);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        testDb.stopDb();
    }

    /**
     * Test creating null bottle.
     */
    public void testCreateBottleWithNull() {
        try {
            Bottle bottle = null;
            em.getTransaction().begin();
            bottleDao.create(bottle);
            
            em.getTransaction().commit();
            fail("Create null bottle should throw IllegalArgumentException.");
        } catch (IllegalArgumentException ex) {

        }
    }

    /**
     * Test creating bottle with already assigned id.
     */
    public void testCreateBottleWithId() {
        try {
            Bottle bottle = MockFactory.on(Bottle.class).create(em);
            em.getTransaction().begin();
            bottleDao.create(bottle);
            em.getTransaction().commit();
            fail("Inserting bottle with id should throw IllegalArgumentException.");
        } catch (IllegalArgumentException ex) {

        }

    }

    /**
     * Test creating bottle with right attributes.
     */
    public void testCreateRightBottle() {
        Liquor liquor = MockFactory.on(Liquor.class).create(em);
        Store store = MockFactory.on(Store.class).create(em);
        Stamp stamp = MockFactory.on(Stamp.class).create(em);
        Bottle bottle = new Bottle();
        bottle.setLotCode("abcd");
        bottle.setSold(Boolean.FALSE);
        bottle.setStore(store);
        bottle.setProducedDate(DateTime.now());
        bottle.setStamp(stamp);
        bottle.setLiquor(liquor);

        try {
            em.getTransaction().begin();
            bottleDao.create(bottle);
            em.getTransaction().commit();
        } catch (Exception exception) {
            fail("Bottle should be created.");
        }
    }

    /**
     * Test creating bottle with empty date.
     */
    public void testCreateBottleWithEmptyDate() {
        Liquor liquor = MockFactory.on(Liquor.class).create(em);
        Store store = MockFactory.on(Store.class).create(em);
        Stamp stamp = MockFactory.on(Stamp.class).create(em);
        Bottle bottle = new Bottle();
        bottle.setLotCode("abcd");
        bottle.setSold(Boolean.FALSE);
        bottle.setStore(store);
        // date is null
        bottle.setProducedDate(null);
        bottle.setStamp(stamp);
        bottle.setLiquor(liquor);

        try {
            em.getTransaction().begin();
            bottleDao.create(bottle);
            em.getTransaction().commit();
            fail("Produced date for bottle should not be null");
        } catch (IllegalArgumentException ex) {

        }
    }

    /**
     * Test updating null bottle.
     */
    public void testUpdateBottleWithNull() {
        try {
            Bottle bottle = null;
            em.getTransaction().begin();
            bottleDao.update(bottle);
            em.getTransaction().commit();
            fail("Updating bottle with null should throw IllegalArguemntException");
        } catch (IllegalArgumentException ex) {

        }
    }

    /**
     * Test updating right bottle.
     */
    public void testUpdateBottle() {
        Bottle bottle = MockFactory.on(Bottle.class).create(em);
        DateTime date = new DateTime();
        bottle.setProducedDate(date);
        try {
            em.getTransaction().begin();
            bottleDao.update(bottle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            fail("Error when updating bottle");
        }
    }

    /**
     * Test updating bottle with incorrect lot code.
     */
    public void testUpdateBottleWithEmptyLotCode() {
        Bottle bottle = MockFactory.on(Bottle.class).create(em);
        bottle.setLotCode(null);
        try {
            em.getTransaction().begin();
            bottleDao.update(bottle);
            em.getTransaction().commit();
            fail("Bottle with empty lot code should not be updated.");
        } catch (Exception ex) {

        }
    }

    /**
     * Test removing bottle.
     */
    public void testRemoveBottle() {
        Bottle bottle = MockFactory.on(Bottle.class).create(em);
        try {
            em.getTransaction().begin();
            bottleDao.remove(bottle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            fail("Error when removing bottle.");
        }
    }

    /**
     * Test removing already removed bottle.
     */
    public void testRemoveBottleAlreadyRemoved() {
        Bottle bottle = MockFactory.on(Bottle.class).create(em);
        em.getTransaction().begin();
        bottleDao.remove(bottle);
        em.getTransaction().commit();
        try {
            em.getTransaction().begin();
            bottleDao.remove(bottle);
            em.getTransaction().commit();
            fail("Error when removing already removed bottle.");
        } catch (Exception ex) {

        }
    }

    /**
     * Test finding bottle by id.
     */
    public void testFindBottleById() {
        List<Bottle> bottles = MockFactory.on(Bottle.class).create(em, 10);
        Bottle toFindBottle = bottles.get(3);
        Bottle foundBottle = bottleDao.findById(toFindBottle.getId());
        assertNotNull("Bottle should be found.", foundBottle);
        assertEquals("Initial bottle should be same as found bottle.", toFindBottle, foundBottle);
        assertNotSame("Bottles should not be same.", bottles.get(1), foundBottle);
        Bottle notExistingBottle = bottleDao.findById(new Long(12));
        assertNull("Result should be null.", notExistingBottle);
    }

    /**
     * Test finding bottle by lot.
     */
    public void testFindBottleByLot() {
        List<Bottle> bottles = MockFactory.on(Bottle.class).create(em, 10);
        Bottle bottle = bottles.get(3);
        bottle.setLotCode("abcd");
        em.getTransaction().begin();
        em.merge(bottle);
        em.getTransaction().commit();
        List<Bottle> foundBottles = bottleDao.findByLot("abcd");
        assertNotNull("Bottle should be found.", foundBottles);
        assertEquals("Incorrect number of found bottles.", foundBottles.size(), 1);
        assertTrue("List should contain bottle.", foundBottles.contains(bottle));
    }

    /**
     * Test finding bottle by liquor.
     */
    public void testFindBottleByLiquor() {
        List<Bottle> bottles = MockFactory.on(Bottle.class).create(em, 10);
        Bottle b1 = bottles.get(1);
        Bottle b2 = bottles.get(2);
        Liquor liquor = MockFactory.on(Liquor.class).create(em);
        b1.setLiquor(liquor);
        b2.setLiquor(liquor);
        em.getTransaction().begin();
        em.merge(b1);
        em.merge(b2);
        em.getTransaction().commit();
        List<Bottle> foundBottles = bottleDao.findByLiquor(liquor);
        assertNotNull("Bottles should be found", foundBottles);
        assertEquals("Incorrect number of found bottles.", foundBottles.size(), 2);
        assertTrue("List should contain bottle.", foundBottles.contains(b1));
        assertTrue("List should contain bottle.", foundBottles.contains(b2));
    }

    /**
     * Test finding bottle by produced date.
     */
    public void testFindBottleByProducedDate() {
        List<Bottle> bottles = MockFactory.on(Bottle.class).create(em, 3);
        Bottle b1 = bottles.get(0);
        Bottle b2 = bottles.get(1);
        Bottle b3 = bottles.get(2);
        DateTime dateFrom = new DateTime(2010, 1, 1, 20, 00);
        DateTime dateTo = new DateTime(2010, 12, 1, 20, 00);
        b1.setProducedDate(new DateTime(2010, 2, 2, 11, 00));
        b2.setProducedDate(new DateTime(2010, 3, 3, 20, 22));
        b3.setProducedDate(new DateTime(2012, 3, 2, 11, 00));
        em.getTransaction().begin();
        em.merge(b1);
        em.merge(b2);
        em.merge(b3);
        em.getTransaction().commit();
        List<Bottle> foundBottles = bottleDao.findByProducedDate(dateFrom, dateTo);
        assertNotNull("Bottles should be found", foundBottles);
        assertEquals("Incorrect number of found bottles.", foundBottles.size(), 2);
        assertTrue("List should contain bottle.", foundBottles.contains(b1));
        assertTrue("List should contain bottle.", foundBottles.contains(b2));
    }

    /**
     * Test find all bottles.
     */
    public void testGetAllBottles() {
        List<Bottle> bottles = MockFactory.on(Bottle.class).create(em, 20);
        List<Bottle> foundBottles = bottleDao.getAll();
        for(Bottle bottle : bottles) {
            assertTrue("List should contain bottle", foundBottles.contains(bottle));
        }
        assertEquals(bottles.size(), foundBottles.size());
    }

}
