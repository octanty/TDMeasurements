package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.entities.Loan;
import java.util.List;

/**
 *
 * @author Matej
 */
public interface LoanDao {

    /**
     * Inserts loan into DB
     *
     * @param loan to be stored
     */
    void createLoan(Loan loan);

    /**
     * Returns all loans from DB
     *
     * @return List of all loans found in DB
     */
    List<Loan> findAllLoans();

    /**
     * Finds Loan with given ID
     *
     * @param long ID of loan to be found
     * @return loan with given ID or NULL if search was unsuccessful
     */
    Loan findLoanById(Long id);

    /**
     * Deletes loan in DB
     *
     * @param loan to be deleted
     */
    void deleteLoan(Loan loan);

    /**
     * updates loan in DB
     *
     * @param loan to be updated
     */
    void updateLoan(Loan loan);
    
    /**
     * 
     * @param customer to get info about
     * @return list of loans which customer made
     */
    List<Loan> findLoansForCustomer(Customer customer);
    
    /**
     * 
     * @param book which we want info about
     * @return list of loans in which this book was lent
     */
    List<Loan> findLoansForBook(Book book);   
}
