package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.*;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vit.mica
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public void createCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is null.");
        }
        if (customer.getId() != null) {
            throw new IllegalArgumentException("Customer ID already set.");
        }
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new IllegalArgumentException("Customer name is empty");
        }
        if (customer.getAddress() == null || customer.getAddress().isEmpty()) {
            throw new IllegalArgumentException("customer address or empty");
        }

        em.persist(customer);
    }

    public List<Customer> findAllCustomers() {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.isDeleted = false");
        return query.getResultList();
    }

    public Customer findCustomerById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }
        return em.find(Customer.class, id);
    }

    public void updateCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is null.");
        }
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new IllegalArgumentException("Customer name is empty");
        }
        if (customer.getAddress() == null || customer.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Customer address or empty");
        }

        Customer finded = em.find(Customer.class, customer.getId());
        if (finded == null) {
            throw new IllegalArgumentException("Customer does not exist: " + customer);
        }
        em.merge(customer);
    }

    public List<Customer> findCustomersByName(String name) {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.name LIKE :name");
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    public Customer findCustomerWithUsername(String username) {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.userName LIKE :name");
        query.setParameter("name", username);
        return (Customer) query.getSingleResult();
    }
}
