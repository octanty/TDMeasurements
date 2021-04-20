package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.entities.Loan;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Matej
 */
@Repository
public class LoanDaoImpl implements LoanDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager em) {
        entityManager = em;
    }

    public void createLoan(Loan loan) {
        checkLoanAttributes(loan);
        if (loan.getId() != null) {
            throw new IllegalArgumentException("Cannot create loan with assigned id");
        }
        entityManager.persist(loan);
    }

    public List<Loan> findAllLoans() {
        Query query = entityManager.createQuery("SELECT l FROM Loan l");
        return query.getResultList();
    }

    public Loan findLoanById(Long id) {
        if (id == null) {
            throw new NullPointerException("ID is null");
        }
        return entityManager.find(Loan.class, id);
    }

    public void deleteLoan(Loan loan) {
        if (loan == null) {
            throw new NullPointerException("Loan is null");
        }
        if (loan.getId() == null) {
            throw new IllegalArgumentException("Cannot delete loan with no id");
        }
        Loan toRemove = entityManager.merge(loan);
        entityManager.remove(toRemove);
        loan.setId(null);
    }

    public void updateLoan(Loan loan) {
        checkLoanAttributes(loan);
        entityManager.merge(loan);
    }

    private void checkLoanAttributes(Loan loan) throws IllegalArgumentException, NullPointerException {
        if (loan == null) {
            throw new NullPointerException("Loan is null");
        }
        if (loan.getCustomer() == null) {
            throw new IllegalArgumentException("Loan.customer cannot be null");
        }
        if (loan.getBook() == null) {
            throw new IllegalArgumentException("Loan.book cannot be null");
        }
    }

    public List<Loan> findLoansForCustomer(Customer customer) {
        Query query = entityManager.createQuery("SELECT l FROM Loan l WHERE l.customer = :c");
        query.setParameter("c", customer);
        return query.getResultList();
    }

    public List<Loan> findLoansForBook(Book book) {
        Query query = entityManager.createQuery("SELECT l FROM Loan l WHERE l.book = :b");
        query.setParameter("b", book);
        return query.getResultList();        
    }
}
