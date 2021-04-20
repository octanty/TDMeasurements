package cz.muni.fi.pa165.rest;

import cz.muni.fi.pa165.library.dtos.CustomerTO;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Mjartan
 */
public class CustomerListModel extends AbstractListModel {

    private List<CustomerTO> customers;

    public CustomerListModel() {
        this.customers = new ArrayList<CustomerTO>();
    }

    public void setCustomers(List<CustomerTO> customers) {
        this.customers = customers;
        fireContentsChanged(this, 0, customers.size());
    }

    public void delete(int index) {
        customers.remove(index);
        fireContentsChanged(this, 0, customers.size());
    }

    public void add(CustomerTO c) {
        customers.add(c);
        fireContentsChanged(this, 0, customers.size());
    }

    public int getSize() {
        return customers.size();
    }

    public Object getElementAt(int index) {
        CustomerTO c = customers.get(index);
        return c.getName() + " : " + c.getAddress();
    }
}
