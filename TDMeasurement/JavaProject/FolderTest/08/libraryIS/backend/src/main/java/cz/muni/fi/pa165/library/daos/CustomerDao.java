package cz.muni.fi.pa165.library.daos;

/**
 *
 * @author vit.mica
 */
import cz.muni.fi.pa165.library.entities.Customer;
import java.util.List;

public interface CustomerDao {

    /**
     * Inserts Customer into DB
     *
     * @param Customer to be stored
     */
    void createCustomer(Customer customer);

    /**
     * Returns all customers from DB who are not deleted
     *
     * @return List of all, not deleted Customers found in DB
     */
    List<Customer> findAllCustomers();

    /**
     * Finds Customer with given ID
     *
     * @param long ID of customer to be found
     * @return Customer with given ID or NULL if search was unsuccessful
     */
    Customer findCustomerById(Long id);   

    /**
     * updates Customer in DB
     *
     * @param Customer to be updated
     */
    void updateCustomer(Customer customer);
    
    /**
     * 
     * @param name substring of name we are looking for
     * @return list of not deleted customers whose name contains param name 
     */
    List<Customer> findCustomersByName(String name);
    
    Customer findCustomerWithUsername(String username);
}
