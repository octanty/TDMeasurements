package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.CustomerDaoImpl;
import cz.muni.fi.pa165.library.entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author vit.mica
 */
public class CustomerDaoImplTest {

    private CustomerDaoImpl dao;
    private EntityManager em;

    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryTestPU");
        em = emf.createEntityManager();
        dao = new CustomerDaoImpl();
        dao.setEntityManager(em);
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = newCustomer("Petr", "Praha");
        em.getTransaction().begin();
        dao.createCustomer(customer);
        em.getTransaction().commit();
        assertNotNull(customer.getId());
        Customer sameCustomer = dao.findCustomerById(customer.getId());
        assertEquals(customer, sameCustomer);
    }

    @Test
    public void testCreateCustomerWithNulls() {
        Customer customer = newCustomer("Petr", null);
        try {
            dao.createCustomer(null);
            fail("Ex not thrown");
        } catch (Exception ex) {
        }
        try {
            dao.createCustomer(customer);
            fail("Ex not thrown");
        } catch (Exception ex) {
        }
        customer = newCustomer("Petr", "Praha");
        dao.createCustomer(customer);
        assertNotNull(customer.getId());
        assertNotNull(customer.getName());
        assertNotNull(customer.getAddress());
    }

    @Test
    public void testGetCustomer() {
        Customer customer1 = newCustomer("Petr", "Praha");
        Customer customer2 = newCustomer("Jan", "Brno");
        em.getTransaction().begin();
        dao.createCustomer(customer1);
        dao.createCustomer(customer2);
        em.getTransaction().commit();
        assertEquals(dao.findCustomerById(customer1.getId()), customer1);
        assertEquals(dao.findCustomerById(customer2.getId()), customer2);
    }

    @Test
    public void testGetCustomerWithNullId() {
        try {
            dao.findCustomerById(null);
            fail("Ex not thrown");
        } catch (Exception ex) {
        }
    }

/*    @Test
    public void testDelete() {
        Customer customer = newCustomer("Petr", "Praha");
        em.getTransaction().begin();
        dao.createCustomer(customer);
        em.getTransaction().commit();
        assertNotNull(dao.findCustomerById(customer.getId()));
        em.getTransaction().begin();
    
        em.getTransaction().commit();
        try {
          
            fail("Ex not thrown");
        } catch (Exception ex) {
        }
    }*/

    /*@Test
    public void testDeleteWithNullId() {
        Customer customer = newCustomer("Petr", "Praha");
        try {
           
            fail("Ex not thrown");
        } catch (Exception ex) {
        }
    }*/

    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setName("Petr");
        customer.setAddress("Prague");
        em.getTransaction().begin();
        dao.createCustomer(customer);
        em.getTransaction().commit();
        em.getTransaction().begin();
        Customer customer2 = dao.findCustomerById(customer.getId());
        customer2.setName("Jan");
        dao.updateCustomer(customer2);
        em.getTransaction().commit();
        Customer customer3 = dao.findCustomerById(customer.getId());
        assertEquals(customer2, customer3);
    }

    @Test
    public void testGetAllCustomers() {
        assertTrue(dao.findAllCustomers().isEmpty());
        Customer customer1 = newCustomer("Petr", "Praha");
        Customer customer2 = newCustomer("Jan", "Brno");
        em.getTransaction().begin();
        dao.createCustomer(customer1);
        dao.createCustomer(customer2);
        em.getTransaction().commit();
        List<Customer> actual = dao.findAllCustomers();
       
        assertEquals(dao.findAllCustomers().size(), 2);
    }

    private static Customer newCustomer(String name, String address) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        return customer;
    }
}
