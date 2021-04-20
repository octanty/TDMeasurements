package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.BookDaoImpl;
import cz.muni.fi.pa165.library.daos.CustomerDaoImpl;
import cz.muni.fi.pa165.library.daos.ImpressionDaoImpl;
import cz.muni.fi.pa165.library.daos.LoanDaoImpl;
import cz.muni.fi.pa165.library.dtos.Department;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.entities.Loan;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Matej
 */
public class LoanDaoImplTest {

    private LoanDaoImpl dao;
    private CustomerDaoImpl custDao;
    private BookDaoImpl bookDao;
    private ImpressionDaoImpl imDao;
    private EntityManager em;
    private Impression im;
    private Book book;
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryTestPU");
        em = emf.createEntityManager();

        dao = new LoanDaoImpl();
        dao.setEntityManager(em);

        imDao = new ImpressionDaoImpl();
        imDao.setEntityManager(em);

        custDao = new CustomerDaoImpl();
        custDao.setEntityManager(em);

        bookDao = new BookDaoImpl();
        bookDao.setEntityManager(em);

        customer = getTestCustomer();
        im = getTestImpression();
        book = getTestBook();
    }

    @Test
    public void testCreateLoan() {
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setCustomer(customer);
        loan.setConditionReturned("na penys");
        loan.setFromDate(new LocalDate(2005, 3, 5));
        loan.setToDate(new LocalDate(2006, 3, 5));

        em.getTransaction().begin();
        dao.createLoan(loan);
        em.getTransaction().commit();
        assertNotNull(loan.getId());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullLoan() {
        em.getTransaction().begin();
        dao.createLoan(null);
        em.getTransaction().commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateLoanWithId() {
        Loan loan = getTestLoan();
        loan.setId(5L);
        em.getTransaction().begin();
        dao.createLoan(loan);
        em.getTransaction().commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateLoanWithNullCustomer() {
        Loan loan = getTestLoan();
        loan.setCustomer(null);
        em.getTransaction().begin();
        dao.createLoan(loan);
        em.getTransaction().commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateLoanWithoutBooks() {
        Loan loan = getTestLoan();
        loan.setBook(null);
        em.getTransaction().begin();
        dao.createLoan(loan);
        em.getTransaction().commit();
    }

//    @Test(expected = Exception.class)
//    public void testCreateLoanWithNullFromDate() {
//        Loan loan = getTestLoan();
//        loan.setFromDate(null);
//        em.getTransaction().begin();
//        dao.createLoan(loan);
//        em.getTransaction().commit();
//    }
//
//    @Test(expected = Exception.class)
//    public void testCreateLoanWithNullToDate() {
//        Loan loan = getTestLoan();
//        loan.setToDate(null);
//        em.getTransaction().begin();
//        dao.createLoan(loan);
//        em.getTransaction().commit();
//    }
    @Test
    public void testFindAllLoans() {
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setCustomer(customer);
        loan.setConditionReturned("na penysek");
        loan.setFromDate(new LocalDate(2006, 3, 5));
        loan.setToDate(new LocalDate(2007, 3, 5));

        Loan loan2 = getTestLoan();

        em.getTransaction().begin();
        dao.createLoan(loan);
        dao.createLoan(loan2);
        em.getTransaction().commit();

        List<Loan> loans2 = dao.findAllLoans();
        assertEquals(loans2.size(), 2);
    }

    @Test
    public void testFindLoanById() {
        Loan loan = getTestLoan();
        em.getTransaction().begin();
        dao.createLoan(loan);
        em.getTransaction().commit();
        Loan found = dao.findLoanById(loan.getId());
        compareLoans(loan, found);
    }

    @Test
    public void testDeleteLoan() {
        Loan loan = getTestLoan();
        em.getTransaction().begin();
        dao.createLoan(loan);
        em.getTransaction().commit();

        Long oldID = loan.getId();
        em.getTransaction().begin();
        dao.deleteLoan(loan);
        em.getTransaction().commit();

        assertNull(loan.getId());
        assertNull(dao.findLoanById(oldID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteLoanWithNullId() {
        Loan loan = getTestLoan();
        em.getTransaction().begin();
        dao.createLoan(loan);
        em.getTransaction().commit();
        loan.setId(null);
        em.getTransaction().begin();
        dao.deleteLoan(loan);
        em.getTransaction().commit();
    }

    @Test
    public void testFindLoansForCustomer() {
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setCustomer(customer);
        loan.setConditionReturned("na penysek");
        loan.setFromDate(new LocalDate(2006, 3, 5));
        loan.setToDate(new LocalDate(2007, 3, 5));

        Loan loan2 = getTestLoan();

        em.getTransaction().begin();
        dao.createLoan(loan);
        dao.createLoan(loan2);
        em.getTransaction().commit();

        List<Loan> loans2 = dao.findLoansForCustomer(customer);
        assertEquals(loans2.size(), 2);
    }

    @Test
    public void testFindLoansForBook() {
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setCustomer(customer);
        loan.setConditionReturned("na penysek");
        loan.setFromDate(new LocalDate(2006, 3, 5));
        loan.setToDate(new LocalDate(2007, 3, 5));

        Loan loan2 = getTestLoan();

        em.getTransaction().begin();
        dao.createLoan(loan);
        dao.createLoan(loan2);
        em.getTransaction().commit();

        List<Loan> loans2 = dao.findLoansForBook(book);
        assertEquals(loans2.size(), 2);
    }

    private void compareLoans(Loan l1, Loan l2) {
        assertEquals(l1.getCustomer(), l2.getCustomer());
        assertEquals(l1.getBook(), l2.getBook());
        assertEquals(l1.getId(), l2.getId());
    }

    private Customer getTestCustomer() {
        Customer customer = new Customer();
        customer.setName("Vit");
        customer.setAddress("Pardubice");
        em.getTransaction().begin();
        custDao.createCustomer(customer);
        em.getTransaction().commit();

        return customer;
    }

    private Impression getTestImpression() {
        Impression impression = new Impression();
        impression.setAuthor("Daniel Hevier");
        impression.setName("Pekna kniha");
        impression.setIsbn("123456");
        impression.setDepartment(Department.CHILDREN);
        impression.setReleaseDate(new LocalDate(2000, 10, 15));
        em.getTransaction().begin();
        imDao.createImpression(impression);
        em.getTransaction().commit();
        return impression;
    }

    private Book getTestBook() {
        Book book = new Book();
        book.setImpression(im);
        book.setCondition("Good");
        em.getTransaction().begin();
        bookDao.createBook(book);
        em.getTransaction().commit();
        return book;
    }

    private Loan getTestLoan() {
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setCustomer(customer);
        loan.setConditionReturned("na penys");
        loan.setFromDate(new LocalDate(2005, 3, 5));
        loan.setToDate(new LocalDate(2006, 3, 5));
        return loan;
    }
}
