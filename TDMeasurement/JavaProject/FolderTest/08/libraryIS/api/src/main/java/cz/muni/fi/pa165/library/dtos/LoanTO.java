package cz.muni.fi.pa165.library.dtos;

import org.joda.time.LocalDate;

public class LoanTO {

    private Long id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String conditionReturned;
    private CustomerTO customer;
    private BookTO book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getConditionReturned() {
        return conditionReturned;
    }

    public void setConditionReturned(String conditionReturned) {
        this.conditionReturned = conditionReturned;
    }

    public CustomerTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerTO customer) {
        this.customer = customer;
    }

    public BookTO getBook() {
        return book;
    }

    public void setBook(BookTO book) {
        this.book = book;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LoanTO other = (LoanTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }  

    @Override
    public String toString() {
        return "Loan{" + "id=" + id + ", fromDate=" + fromDate + ", toDate=" + toDate + '}';
    }
}
