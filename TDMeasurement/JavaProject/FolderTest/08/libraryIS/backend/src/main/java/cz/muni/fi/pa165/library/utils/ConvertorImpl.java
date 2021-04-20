package cz.muni.fi.pa165.library.utils;

import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.dtos.CustomerTO;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.dtos.LoanTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.entities.Loan;
import org.springframework.stereotype.Service;

@Service
public class ConvertorImpl implements Convertor {

    public BookTO convert(Book book) {
        if (book == null) {
            return null;
        }
        BookTO result = new BookTO();
        result.setId(book.getId());
        result.setCondition(book.getCondition());
        result.setImpression(convert(book.getImpression()));
        return result;
    }

    public Book convert(BookTO book) {
        if (book == null) {
            return null;
        }
        Book result = new Book();
        result.setId(book.getId());
        result.setCondition(book.getCondition());
        result.setImpression(convert(book.getImpression()));
        return result;
    }

    public LoanTO convert(Loan loan) {
        if (loan == null) {
            return null;
        }
        LoanTO result = new LoanTO();
        result.setId(loan.getId());
        result.setToDate(loan.getToDate());
        result.setFromDate(loan.getFromDate());
        result.setBook(convert(loan.getBook()));
        result.setConditionReturned(loan.getConditionReturned());
        result.setCustomer(convert(loan.getCustomer()));
        return result;
    }

    public Loan convert(LoanTO loan) {
        if (loan == null) {
            return null;
        }
        Loan result = new Loan();
        result.setId(loan.getId());
        result.setToDate(loan.getToDate());
        result.setFromDate(loan.getFromDate());
        result.setBook(convert(loan.getBook()));
        result.setConditionReturned(loan.getConditionReturned());
        result.setCustomer(convert(loan.getCustomer()));
        return result;
    }

    public CustomerTO convert(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerTO result = new CustomerTO();
        result.setId(customer.getId());
        result.setName(customer.getName());
        result.setAddress(customer.getAddress());
        result.setIsDeleted(customer.getIsDeleted());
        result.setPassword(customer.getPassword());
        result.setUserName(customer.getUserName());
        return result;
    }

    public Customer convert(CustomerTO customer) {
        if (customer == null) {
            return null;
        }
        Customer result = new Customer();
        result.setId(customer.getId());
        result.setName(customer.getName());
        result.setAddress(customer.getAddress());
        result.setIsDeleted(customer.getIsDeleted());
        result.setPassword(customer.getPassword());
        result.setUserName(customer.getUserName());
        return result;
    }

    public ImpressionTO convert(Impression impression) {
        if (impression == null) {
            return null;
        }
        ImpressionTO result = new ImpressionTO();
        result.setId(impression.getId());
        result.setName(impression.getName());
        result.setIsbn(impression.getIsbn());
        result.setAuthor(impression.getAuthor());
        result.setReleaseDate(impression.getReleaseDate());
        result.setDepartment(impression.getDepartment());
        return result;
    }

    public Impression convert(ImpressionTO impression) {
        if (impression == null) {
            return null;
        }
        Impression result = new Impression();
        result.setId(impression.getId());
        result.setName(impression.getName());
        result.setIsbn(impression.getIsbn());
        result.setAuthor(impression.getAuthor());
        result.setReleaseDate(impression.getReleaseDate());
        result.setDepartment(impression.getDepartment());
        return result;
    }
}
