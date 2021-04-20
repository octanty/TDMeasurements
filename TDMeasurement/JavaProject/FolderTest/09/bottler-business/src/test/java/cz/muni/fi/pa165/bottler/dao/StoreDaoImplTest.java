package cz.muni.fi.pa165.bottler.dao;

import cz.muni.fi.pa165.bottler.data.dao.StoreDao;
import cz.muni.fi.pa165.bottler.data.dao.StoreDaoImpl;
import cz.muni.fi.pa165.bottler.data.model.Store;
import junit.framework.TestCase;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Josef Ludvicek <374268@mail.muni.cz>
 */
public class StoreDaoImplTest extends TestCase {
    private StoreDao storeDao;
    private EntityManager em;
    private BottlerTestDb testDb;

    public StoreDaoImplTest(String name) {
        super(name);
        testDb = new BottlerTestDb();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        //get em and start db
        testDb.startDb();
        em = testDb.getEntityManager();

        storeDao = new StoreDaoImpl(em);

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        testDb.stopDb();
    }


    /**
     * Test create method
     *
     * @throws Exception
     */
    public void testCreateOneItem() throws Exception {
        TypedQuery<Store> q = em.createQuery("select s from Store s", Store.class);
        assertEquals(q.getResultList().size(), 0);

        Store s = new Store();
        s.setName("asdf");
        s.setAddress("ffff");
        s.setIco(123);

        em.getTransaction().begin();
        Store stored = storeDao.create(s);
        em.getTransaction().commit();
        
        List<Store> stores = q.getResultList();

        assertEquals(stores.size(), 1);
        assertNotNull(stored.getId());

        assertEquals(s.getAddress(), stored.getAddress());
        assertEquals(s.getIco(), stored.getIco());
        assertEquals(s.getName(), stored.getName());
    }

    /**
     * Expected exception when creating entity with defined id
     *
     * @throws Exception
     */
    public void testCreateNotNullId() throws Exception {
        Store s = new Store();
        s.setName("asdf");
        s.setAddress("ffff");
        s.setIco(123);

        s.setId(new Long(123));

        try {
            em.getTransaction().begin();
            storeDao.create(s);
            em.getTransaction().commit();
            fail("Illegal argument exception is expected when creating entity with existing id");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Test entity update
     *
     * @throws Exception
     */
    public void testUpdate() throws Exception {
        Store s = MockFactory.on(Store.class).create(em);

        String old = s.getName();
        String newName = "noovoucky obchod";
        s.setName(newName);
        em.getTransaction().begin();
        s = storeDao.update(s);
        em.getTransaction().commit();

        assertEquals(s.getName(), newName);
        assertNotSame(s.getName(), old);
    }

    /**
     * Test entity remove, ensure entity was removed
     *
     * @throws Exception
     */
    public void testRemove() throws Exception {
        int count = 9;
        List<Store> stores = MockFactory.on(Store.class).create(em, count);
        TypedQuery<Store> q = em.createQuery("select s from Store s", Store.class);
        List<Store> result = q.getResultList();


        assertEquals(stores.size(), result.size());
        Store s = stores.get(3);
        em.getTransaction().begin();
        storeDao.remove(s);
        em.getTransaction().commit();

        assertEquals(count - 1, q.getResultList().size());
        assertNull(em.find(Store.class, s.getId()));
    }

    /**
     * Expects fail when searching by null id
     *
     * @throws Exception
     */
    public void testFindByIdNull() throws Exception {
        try {
            storeDao.findById(null);
            fail("id cant be null");
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test find by id method
     *
     * @throws Exception
     */
    public void testFindById() throws Exception {
        List<Store> ss = MockFactory.on(Store.class).create(em, 10);
        Store s = ss.get(5);
        Store found = storeDao.findById(s.getId());
        assertNotNull(found);
        assertEquals(s, found);
    }

    /**
     * Test finding by address. Ensure all requested items from db were found
     *
     * @throws Exception
     */
    public void testFindByAddress() throws Exception {
        List<Store> ss = MockFactory.on(Store.class).create(null, 10, false);
        String address = "pod mostem 1 u jezecka";
        ss.get(4).setAddress(address);
        ss.get(7).setAddress(address);
        ss.get(1).setAddress(address + "asdf");

        em.getTransaction().begin();
        for (Store s : ss) {
            em.persist(s);
        }
        em.getTransaction().commit();

        List<Store> found = storeDao.findByAddress(address);
        assertEquals(2, found.size());
        assertTrue(found.contains(ss.get(4)));
        assertTrue(found.contains(ss.get(7)));
        assertFalse(found.contains(ss.get(1)));
    }

    /**
     * Test finding by name. Ensure all requested items from db were found
     *
     * @throws Exception
     */
    public void testFindByName() throws Exception {
        List<Store> ss = MockFactory.on(Store.class).create(null, 10, false);
        String name = "oxymorona";
        ss.get(4).setName(name);
        ss.get(7).setName(name);
        ss.get(1).setName(name + "asdf");

        em.getTransaction().begin();
        for (Store s : ss) {
            em.persist(s);
        }
        em.getTransaction().commit();

        List<Store> found = storeDao.findByName(name);
        assertEquals(2, found.size());
        assertTrue(found.contains(ss.get(4)));
        assertTrue(found.contains(ss.get(7)));
        assertFalse(found.contains(ss.get(1)));
    }

    /**
     * Ensure getAll works as expected - empty list for emtpy DB
     *
     * @throws Exception
     */
    public void testGetAllEmpty() throws Exception {
        assertEquals(0, storeDao.getAll().size());
    }

    /**
     * Ensure all items from db were found
     *
     * @throws Exception
     */
    public void testGetAll() throws Exception {
        List<Store> ss = MockFactory.on(Store.class).create(em, 10);
        List<Store> found = storeDao.getAll();

        assertEquals(ss.size(), found.size());
        for (Store s : ss) {
            assertTrue(found.contains(s));
        }
    }
}
