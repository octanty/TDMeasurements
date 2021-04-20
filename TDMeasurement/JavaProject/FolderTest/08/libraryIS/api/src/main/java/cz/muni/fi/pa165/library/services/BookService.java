package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import java.util.List;

/**
 *
 * Service used for manipulating books
 *
 * @author Mjartan
 *
 */
public interface BookService {

    /**
     * Inserts a book in the database
     *
     * @param book to be inserted
     */
    void createBook(BookTO book);

    /**
     * Retrieves all books from the database
     *
     * @return List of books
     */
    List<BookTO> findAllBooks();

    /**
     * Retrieves a book with specific id from the database
     *
     * @param id of the book
     * @return book with the given id
     */
    BookTO findBookById(Long id);

    /**
     * Deletes a specific book from the database
     *
     * @param book to be deleted
     */
    void deleteBook(BookTO book);

    /**
     * Updates attributes of a specific book in the database
     *
     * @param book to be updated
     */
    void updateBook(BookTO book);
    
    /**
     * 
     * @param impression which books we want to get
     * @return list of books which belong to impression
     */
    List<BookTO> findBooksForImpression(ImpressionTO impression);    
    
    
    public List<BookTO> findNotBorrowedBooks();
}
