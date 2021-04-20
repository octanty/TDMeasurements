package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.BookDaoImpl;
import cz.muni.fi.pa165.library.daos.ImpressionDaoImpl;
import cz.muni.fi.pa165.library.dtos.Department;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Impression;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.joda.time.LocalDate;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Mjartan
 */
public class BookDaoImplTest {

    private BookDaoImpl dao;
    private EntityManager em;
    private Impression im1;
    private Impression im2;

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryTestPU");
        em = emf.createEntityManager();

        dao = new BookDaoImpl();
        dao.setEntityManager(em);

        im1 = getTestImpression();
        im2 = getTestImpression2();

        ImpressionDaoImpl imDao = new ImpressionDaoImpl();
        imDao.setEntityManager(em);

        em.getTransaction().begin();
        imDao.createImpression(im2);
        imDao.createImpression(im1);
        em.getTransaction().commit();
    }

    @Test
    public void testCreateBook() {
        em.getTransaction().begin();
        Book book = getTestBook();
        dao.createBook(book);
        em.getTransaction().commit();
        assertNotNull(book.getId());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullBook() {
        em.getTransaction().begin();
        dao.createBook(null);
        em.getTransaction().commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBookWithId() {
        em.getTransaction().begin();
        Book book = getTestBook();
        book.setId(5L);
        dao.createBook(book);
        em.getTransaction().commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBookWithNullImpression() {
        em.getTransaction().begin();
        Book book = getTestBook();
        book.setImpression(null);
        dao.createBook(book);
        em.getTransaction().commit();
    }

    @Test
    public void testFindAllBooks() {
        List<Book> books = new ArrayList<Book>() {
            {
                add(getTestBook());
                add(createTestBook("Nice", new LocalDate(2008, 3, 25), im1));
                add(createTestBook("Chyba strana 55", new LocalDate(2007, 5, 14), im2));
            }
        };

        for (Book book : books) {
            em.getTransaction().begin();
            dao.createBook(book);
            em.getTransaction().commit();
        }
        compareBooks(books, dao.findAllBooks());
    }

    @Test
    public void testFindBookById() {
        Book book = getTestBook();
        em.getTransaction().begin();
        dao.createBook(book);
        em.getTransaction().commit();
        Book found = dao.findBookById(book.getId());
        compareBooks(book, found);
    }

    @Test
    public void testDeleteBook() {
        em.getTransaction().begin();
        Book book = getTestBook();
        dao.createBook(book);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Long oldID = book.getId();
        dao.deleteBook(book);
        em.getTransaction().commit();
        assertNull(book.getId());
        assertNull(dao.findBookById(oldID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeletebookWithNullId() {
        em.getTransaction().begin();
        Book book = getTestBook();
        dao.createBook(book);
        em.getTransaction().commit();

        book.setId(null);
        em.getTransaction().begin();
        dao.deleteBook(book);
        em.getTransaction().commit();
    }

    @Test
    public void testUpdateBook() {
        em.getTransaction().begin();
        Book book = getTestBook();
        dao.createBook(book);
        em.getTransaction().commit();
        book.setCondition("Very good");

        book.setImpression(im2);
        em.getTransaction().begin();
        dao.updateBook(book);
        em.getTransaction().commit();
        assertEquals(book, dao.findBookById(book.getId()));
    }

    private Book getTestBook() {
        return createTestBook("Very bad", new LocalDate(2001, 11, 8), im1);
    }

    static Book createTestBook(String condition, LocalDate date, Impression impression) {
        Book book = new Book();
        book.setImpression(impression);
        book.setCondition(condition);

        book.setImpression(impression);
        return book;
    }

    static Impression getTestImpression() {
        Impression impression = new Impression();
        impression.setAuthor("Daniel Hevier");
        impression.setName("Pekna kniha");
        impression.setIsbn("123456");
        impression.setDepartment(Department.CHILDREN);
        impression.setReleaseDate(new LocalDate(2000, 10, 15));
        return impression;
    }

    static Impression getTestImpression2() {
        Impression impression = new Impression();
        impression.setAuthor("John Cook");
        impression.setName("Naj recepty");
        impression.setIsbn("98765");
        impression.setDepartment(Department.COOKING);
        impression.setReleaseDate(new LocalDate(1990, 8, 15));
        return impression;
    }

    void compareBooks(Book b1, Book b2) {
        assertEquals(b1.getCondition(), b2.getCondition());

        assertEquals(b1.getImpression(), b2.getImpression());
        assertEquals(b1.getId(), b2.getId());
    }

    void compareBooks(List<Book> b1, List<Book> b2) {
        Collections.sort(b1, bookIdCmp);
        Collections.sort(b2, bookIdCmp);
        assertEquals(b1.size(), b2.size());
        for (int i = 0; i < b1.size(); i++) {
            compareBooks(b1.get(i), b2.get(i));
        }
    }
    private static Comparator<Book> bookIdCmp = new Comparator<Book>() {
        public int compare(Book o1, Book o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };
}
