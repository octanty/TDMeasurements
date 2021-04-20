package cz.muni.fi.pa165.bottler.dao;

import cz.muni.fi.pa165.bottler.data.dao.LiquorDao;
import cz.muni.fi.pa165.bottler.data.dao.LiquorDaoImpl;
import cz.muni.fi.pa165.bottler.data.model.Liquor;
import cz.muni.fi.pa165.bottler.data.model.Producer;
import junit.framework.TestCase;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Josef Ludvicek <374268@mail.muni.cz>
 */
public class LiquorDaoImplTest extends TestCase {

    private BottlerTestDb testDb;
    private EntityManager em;
    private LiquorDao dao;

    public LiquorDaoImplTest(String name) {
        super(name);
        testDb = new BottlerTestDb();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        testDb.startDb();
        em = testDb.getEntityManager();
        dao = new LiquorDaoImpl(em);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        testDb.stopDb();
    }

    /**
     * Cant create new entity with already existing id
     *
     * @throws Exception
     */
    public void testCreateNotNullId() throws Exception {
        Liquor l = MockFactory.on(Liquor.class).create(em);

        try {
            em.getTransaction().begin();
            dao.create(l);
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
        Liquor l = MockFactory.on(Liquor.class).create(em);

        String old = l.getName();
        String newName = "noovoucky";
        l.setName(newName);
        
        em.getTransaction().begin();
        l = dao.update(l);
        em.getTransaction().commit();

        assertEquals(l.getName(), newName);
        assertNotSame(l.getName(), old);
    }

    /**
     * Test entity remove, ensure entity was removed
     *
     * @throws Exception
     */
    public void testRemove() throws Exception {
        int count = 9;
        List<Liquor> Liquors = MockFactory.on(Liquor.class).create(em, count);
        TypedQuery<Liquor> q = em.createQuery("select l from Liquor l", Liquor.class);
        List<Liquor> result = q.getResultList();


        assertEquals(Liquors.size(), result.size());
        Liquor s = Liquors.get(3);
        em.getTransaction().begin();
        dao.remove(s);
        em.getTransaction().commit();

        assertEquals(count - 1, q.getResultList().size());
        assertNull(em.find(Liquor.class, s.getId()));
    }

    /**
     * Expects fail when searching by null id
     *
     * @throws Exception
     */
    public void testFindByIdNull() throws Exception {
        try {
            dao.findById(null);
            fail("id cant be null");
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test find by name method
     * ensure all entities with requested name were found
     *
     * @throws Exception
     */
    public void testFindByName() throws Exception {
        List<Liquor> ll = MockFactory.on(Liquor.class).create(null, 10, false);
        String name = "asdf 123";
        ll.get(2).setName(name);
        ll.get(6).setName(name);
        ll.get(7).setName(name);

        em.getTransaction().begin();
        for (Liquor l : ll) {
            em.persist(l.getProducer());
            em.persist(l);
        }
        em.getTransaction().commit();

        em.getTransaction().begin();
        List<Liquor> found = dao.findByName(name);
        em.getTransaction().commit();
        assertEquals(3, found.size());
        assertTrue(found.contains(ll.get(2)));
        assertTrue(found.contains(ll.get(6)));
        assertTrue(found.contains(ll.get(7)));
    }

    /**
     * Test find by id method
     *
     * @throws Exception
     */
    public void testFindById() throws Exception {
        Liquor l = MockFactory.on(Liquor.class).create(em);
        Liquor found = dao.findById(l.getId());
        assertNotNull(found);
        assertEquals(l, found);
    }

    /**
     * Test find liquor by producer
     *
     * @throws Exception
     */
    public void testFindByProducer() throws Exception {
        List<Liquor> liquors = MockFactory.on(Liquor.class).create(em, 10);
        Producer p1 = liquors.get(2).getProducer();

        List<Liquor> found = dao.findByProducer(p1);
        assertEquals(1, found.size());
        assertEquals(liquors.get(2), found.get(0));
    }

    /**
     * Ensure getAll works as expected - empty list for emtpy DB
     *
     * @throws Exception
     */
    public void testGetAllEmpty() throws Exception {
        assertEquals(0, dao.getAll().size());
    }


    /**
     * Ensure all items from db were found
     *
     * @throws Exception
     */
    public void testGetAll() throws Exception {
        List<Liquor> liquors = MockFactory.on(Liquor.class).create(em, 10);
        List<Liquor> found = dao.getAll();
        assertEquals(liquors.size(), found.size());

        for (Liquor l : liquors) {
            assertTrue(found.contains(l));
        }
    }
}
