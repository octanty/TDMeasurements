package cz.muni.fi.pa165.library.utils;

import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.dtos.CustomerTO;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.dtos.LoanTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.entities.Loan;

public interface Convertor {
    BookTO convert(Book book);    
    Book convert(BookTO book);
    LoanTO convert(Loan loan);
    Loan convert(LoanTO loan);
    CustomerTO convert(Customer customer);
    Customer convert(CustomerTO customer);    
    ImpressionTO convert(Impression impression);   
    Impression convert(ImpressionTO impression);
}
