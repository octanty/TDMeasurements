package cz.muni.fi.pa165.bottler.dao;

import cz.muni.fi.pa165.bottler.data.dao.ProducerDao;
import cz.muni.fi.pa165.bottler.data.dao.ProducerDaoImpl;
import cz.muni.fi.pa165.bottler.data.model.Producer;
import junit.framework.TestCase;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */
public class ProducerDaoImplTest extends TestCase {

    private ProducerDao producerDao;
    private ProducerDao mockedDao;
    private EntityManager em;
    private BottlerTestDb testDb;

    public ProducerDaoImplTest(String testName){
        super(testName);
        testDb = new BottlerTestDb();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testDb.startDb();
        em = testDb.getEntityManager();
        producerDao = new ProducerDaoImpl(em);
        mockedDao = Mockito.mock(ProducerDao.class);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        testDb.stopDb();
    }

    /**
     * Test creating null producer.
     */
    public void testCreateProducerWithNull() {
        try {
            Producer producer = null;
            em.getTransaction().begin();
            producerDao.create(producer);
            em.getTransaction().commit();
            fail("Create null producer should throw IllegalArgumentException.");
        } catch (IllegalArgumentException ex) {

        }
    }

    /**
     * Test creating producer with already assigned id.
     */
    public void testCreateProducerWithId() {
        try {
            Producer producer = MockFactory.on(Producer.class).create(em);
            em.getTransaction().begin();
            producerDao.create(producer);
            em.getTransaction().commit();
            fail("Inserting producer with id should throw IllegalArgumentException.");
        } catch (IllegalArgumentException ex) {

        }
    }

    /**
     * Test creating producer with right attributes.
     */
    public void testCreateProducerWithRightAttributes() {
        Producer producer = new Producer();
        producer.setAddress("Trnkova 35, Vizovice");
        producer.setName("Rudolf Jelinek");
        producer.setIco(1234567890);
        try {
            em.getTransaction().begin();
            producerDao.create(producer);
            em.getTransaction().commit();
        } catch (Exception exception) {
            fail("Producer should be created.");
        }
    }

    /**
     * Test creating producer with ICO which is already assigned.
     */
    public void testCreateProducerWithIco() {
        Producer producer1 = new Producer();
        Integer ico = 123456789;
        producer1.setAddress("Trnkova 35, Vizovice");
        producer1.setName("Rudolf Jelinek");
        producer1.setIco(ico);
        try {
            em.getTransaction().begin();
            producerDao.create(producer1);
            em.getTransaction().commit();
        } catch (Exception exception) {
            fail("Producer should be created.");
        }

        Producer producer2 = new Producer();
        producer2.setAddress("Bajajova 12, Zlin");
        producer2.setName("Likerka Drak");
        producer1.setIco(ico);

        try {
            em.getTransaction().begin();
            producerDao.create(producer2);
            em.getTransaction().commit();
            fail("Producer with same ico should not be persited.");
        } catch (Exception ex) {

        }
    }

    /**
     * Test updating null producer.
     */
    public void testUpdateProducerWithNull() {
        try {
            Producer producer = null;
            em.getTransaction().begin();
            producerDao.update(producer);
            em.getTransaction().commit();
            fail("Updating producer with null should throw IllegalArguemntException");
        } catch (IllegalArgumentException ex) {

        }
    }

    /**
     * Test updating producer with right data.
     */
    public void testUpdateProducer() {
        Producer producer = MockFactory.on(Producer.class).create(em);
        producer.setAddress("U Prazdroje, Plzen");

        try {
            em.getTransaction().begin();
            producerDao.update(producer);
            em.getTransaction().commit();
        } catch (Exception ex) {
            fail("Error when updating producer");
        }
    }

    /**
     * Test updating producer with same ico.
     */
    public void testUpdateProducerWithSameIco() {
        Producer producer1 = MockFactory.on(Producer.class).create(em);
        Integer ico = producer1.getIco();
        Producer producer2 = MockFactory.on(Producer.class).create(em);
        producer2.setIco(ico);

        try {
            em.getTransaction().begin();
            producerDao.update(producer2);
            em.getTransaction().commit();
            fail("Producer with same ico should not be updated.");
        } catch (Exception ex) {

        }
    }

    /**
     * Test removing producer.
     */
    public void testRemoveProducer() {
        Producer producer = MockFactory.on(Producer.class).create(em);
        try {
            em.getTransaction().begin();
            producerDao.remove(producer);
            em.getTransaction().commit();
        } catch (Exception ex) {
            fail("Error when removing producer.");
        }
    }

    /**
     * Test removing already removed producer.
     */
    public void testRemoveProducerAlreadyRemoved() {
        Producer producer = MockFactory.on(Producer.class).create(em);
        em.getTransaction().begin();
        producerDao.remove(producer);
        em.getTransaction().commit();
        try {
            em.getTransaction().begin();
            producerDao.remove(producer);
            em.getTransaction().commit();
            fail("Error when removing already removed producer.");
        } catch (Exception ex) {

        }
    }

    /**
     * Test finding producer by id.
     */
    public void testFindProducerById() {
        List<Producer> producers = MockFactory.on(Producer.class).create(em, 30);
        Producer toFindProducer = producers.get(10);
        Producer foundProducer = producerDao.findById(toFindProducer.getId());
        assertNotNull("Producer should be found.", foundProducer);
        assertEquals("Initial producer should be same as found producer.", toFindProducer, foundProducer);
        assertNotSame("Producers should not be same.", producers.get(1), foundProducer);
        Producer notExistingProducer = producerDao.findById(new Long(96));
        assertNull("Result should be null.", notExistingProducer);
    }

    /**
     * Test finding producer by ico.
     */
    public void testFindProducerByIco() {
        Producer producer1 = new Producer();
        Producer producer2 = new Producer();
        producer1.setIco(1020);
        producer2.setIco(3000);
        em.getTransaction().begin();
        em.persist(producer1);
        em.persist(producer2);
        em.getTransaction().commit();
        em.getTransaction().begin();
        Producer foundProducer = producerDao.findByIco(3000);
        em.getTransaction().commit();
        assertNotNull("Producer should be found.", foundProducer);
    }

    /**
     * Test finding producer by name.
     */
    public void testFindProducerByName() {
        List<Producer> producers = MockFactory.on(Producer.class).create(em, 10);
        Producer producer = producers.get(2);
        String name = "Likerka Drak - nejlepsi a nejkvalitnejsi alkohol siroko daleko";
        producer.setName(name);
        em.getTransaction().begin();
        em.merge(producer);
        em.getTransaction().commit();
        List<Producer> foundProducers = producerDao.findByName(name);
        assertNotNull("Producer should be found.", foundProducers);
        assertEquals("Incorrect number of found producers.", foundProducers.size(), 1);
        assertTrue("List should contain producers.", foundProducers.contains(producer));
    }

    /**
     * Test find all producers.
     */
    public void testGetAllProducers() {
        List<Producer> producers = MockFactory.on(Producer.class).create(em, 13);
        List<Producer> foundProducers = producerDao.getAll();
        for(Producer producer : producers) {
            assertTrue("List should contain producer", foundProducers.contains(producer));
        }
        assertEquals(producers.size(), foundProducers.size());
    }

}
