package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.ImpressionDaoImpl;
import cz.muni.fi.pa165.library.dtos.Department;
import cz.muni.fi.pa165.library.entities.Impression;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author MiškoHu
 */
public class ImpressionDaoImplTest {

    private ImpressionDaoImpl dao;
    private EntityManager em;

    public ImpressionDaoImplTest() {
    }

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryTestPU");
        em = emf.createEntityManager();
        dao = new ImpressionDaoImpl();
        dao.setEntityManager(em);
    }

    @Test
    public void testCreateImpression() {
        em.getTransaction().begin();
        Impression impression = getTestImpression();
        dao.createImpression(impression);
        em.getTransaction().commit();
        assertNotNull(impression.getId());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullImpression() {
        dao.createImpression(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateImpressionWithId() {
        Impression impression = getTestImpression();
        impression.setId(5L);
        em.getTransaction().begin();
        dao.createImpression(impression);
        em.getTransaction().commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateImpressionWithNullName() {
        Impression impression = getTestImpression();
        impression.setName(null);
        dao.createImpression(impression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateImpressionWithNullIsbn() {
        Impression impression = getTestImpression();
        impression.setIsbn(null);
        dao.createImpression(impression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateImpressionWithNullAuthor() {
        Impression impression = getTestImpression();
        impression.setAuthor(null);
        dao.createImpression(impression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateImpressionWithNullDate() {
        Impression impression = getTestImpression();
        impression.setReleaseDate(null);
        dao.createImpression(impression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateImpressionWithNullDepartment() {
        Impression impression = getTestImpression();
        impression.setDepartment(null);
        dao.createImpression(impression);
    }

    @Test
    public void testFindAllImpressions() {
        List<Impression> impressions = new ArrayList<Impression>() {
            {
                add(getTestImpression());
                add(createTestImpression("124", "Varime spolu", "Smolko",
                        new LocalDate(2010, 11, 8), Department.COOKING));
                add(createTestImpression("125", "Man of Steel", "John Smith",
                        new LocalDate(2013, 1, 1), Department.FICTION));
            }
        };
        for (Impression impression : impressions) {
            em.getTransaction().begin();
            dao.createImpression(impression);
            em.getTransaction().commit();
        }
        compareImpressions(impressions, dao.findAllImpressions());
    }

    @Test
    public void testFindImpressionById() {
        Impression impression = getTestImpression();
        em.getTransaction().begin();
        dao.createImpression(impression);
        em.getTransaction().commit();
        Impression found = dao.findImpressionById(impression.getId());
        compareImpressions(impression, found);
    }

    @Test
    public void testDeleteImpression() {
        Impression impression = getTestImpression();
        em.getTransaction().begin();
        dao.createImpression(impression);
        em.getTransaction().commit();
        Long oldID = impression.getId();
        em.getTransaction().begin();
        dao.deleteImpression(impression);
        em.getTransaction().commit();
        assertNull(impression.getId());
        assertNull(dao.findImpressionById(oldID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteImpressionWithNullId() {
        Impression impression = getTestImpression();
        em.getTransaction().begin();
        dao.createImpression(impression);
        em.getTransaction().commit();
        impression.setId(null);
        em.getTransaction().begin();
        dao.deleteImpression(impression);
        em.getTransaction().commit();
    }

    @Test
    public void testUpdateImpression() {
        Impression impression = getTestImpression();
        em.getTransaction().begin();
        dao.createImpression(impression);
        em.getTransaction().commit();
        impression.setName("Ferko");
        impression.setIsbn("555");
        impression.setAuthor("Anton Mravec");
        em.getTransaction().begin();
        dao.updateImpression(impression);
        em.getTransaction().commit();
        assertEquals(impression, dao.findImpressionById(impression.getId()));
    }

    static Impression getTestImpression() {
        return createTestImpression("123", "Ako islo vajce na vandrovku",
                "Michal Kekely", new LocalDate(2001, 11, 8), Department.CHILDREN);
    }

    static Impression createTestImpression(String isbn, String name,
            String author, LocalDate releaseDate, Department department) {

        Impression impression = new Impression();
        impression.setIsbn(isbn);
        impression.setName(name);
        impression.setAuthor(author);
        impression.setReleaseDate(releaseDate);
        impression.setDepartment(department);
        return impression;
    }

    private void compareImpressions(Impression i1, Impression i2) {
        assertEquals(i1.getName(), i2.getName());
        assertEquals(i1.getIsbn(), i2.getIsbn());
        assertEquals(i1.getAuthor(), i2.getAuthor());
        assertEquals(i1.getReleaseDate(), i2.getReleaseDate());
        assertEquals(i1.getDepartment(), i2.getDepartment());
    }

    private void compareImpressions(List<Impression> i1, List<Impression> i2) {
        Collections.sort(i1, impressionIdCmp);
        Collections.sort(i2, impressionIdCmp);
        assertEquals(i1.size(), i2.size());
        for (int i = 0; i < i1.size(); i++) {
            compareImpressions(i1.get(i), i2.get(i));
        }
    }
    private static Comparator<Impression> impressionIdCmp = new Comparator<Impression>() {
        public int compare(Impression o1, Impression o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };
}
