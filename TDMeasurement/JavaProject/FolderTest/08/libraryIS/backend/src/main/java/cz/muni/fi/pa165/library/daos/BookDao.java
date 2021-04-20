package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Impression;
import java.util.List;

/**
 * DAO interface for manipulating with the book entities
 *
 * @author Mjartan
 */
public interface BookDao {

    /**
     * Inserts a book in the database
     *
     * @param book to be inserted
     */
    void createBook(Book book);

    /**
     * Retrieves all books from the database
     *
     * @return List of books
     */
    List<Book> findAllBooks();

    /**
     * Retrieves a book with specific id from the database
     *
     * @param id of the book
     * @return book with the given id
     */
    Book findBookById(Long id);

    /**
     * Deletes a specific book from the database
     *
     * @param book to be deleted
     */
    void deleteBook(Book book);

    /**
     * Updates attributes of a specific book in the database
     *
     * @param book to be updated
     */
    void updateBook(Book book);

    /**
     *
     * @param impression which books we want to get
     * @return list of books which belong to impression
     */
    List<Book> findBooksForImpression(Impression impression);

    /**
     * Selects book which are available
     *
     * @return list of books which are not borrowed
     */
    List<Book> findNotBorrowedBooks();
}
