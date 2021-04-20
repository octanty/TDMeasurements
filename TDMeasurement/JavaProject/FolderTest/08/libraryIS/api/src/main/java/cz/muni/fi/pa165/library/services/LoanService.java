package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.dtos.CustomerTO;
import cz.muni.fi.pa165.library.dtos.LoanTO;

import java.util.List;

/**
 *
 * @author Matej
 */
public interface LoanService {

    /**
     * Inserts loan into DB
     *
     * @param loan to be stored
     */
    void createLoan(LoanTO loan);

    /**
     * Returns all loans from DB
     *
     * @return List of all loans found in DB
     */
    List<LoanTO> findAllLoans();

    /**
     * Finds Loan with given ID
     *
     * @param long ID of loan to be found
     * @return loan with given ID or NULL if search was unsuccessful
     */
    LoanTO findLoanById(Long id);

    /**
     * Deletes loan in DB
     *
     * @param loan to be deleted
     */
    void deleteLoan(LoanTO loan);

    /**
     * updates loan in DB
     *
     * @param loan to be updated
     */
    void updateLoan(LoanTO loan);

    /**
     *
     * @param customer to get info about
     * @return list of loans which customer made
     */
    List<LoanTO> findLoansForCustomer(CustomerTO customer);

    /**
     *
     * @param book which we want info about
     * @return list of loans in which this book was lent
     */
    List<LoanTO> findLoansForBook(BookTO book);

    /**
     * Assign loans toDate to current date and changes loaned book's condition
     *
     * @param loan which book is to be returned loan has already set return
     * condition
     */
    void returnBook(LoanTO loan);
}
