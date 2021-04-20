package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.daos.BookDao;
import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mjartan
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private Convertor convertor;

    @Secured({"ROLE_ADMIN"})
    public void createBook(BookTO bookTO) {
        if (bookTO == null) {
            throw new NullPointerException("BookTO is null");
        }
        Book bookEntity = convertor.convert(bookTO);
        bookDao.createBook(bookEntity);
        bookTO.setId(bookEntity.getId());
    }

    public List<BookTO> findAllBooks() {
        List<BookTO> bookTOs = new ArrayList<BookTO>();
        List<Book> bookEntities = bookDao.findAllBooks();
        for (Book book : bookEntities) {
            bookTOs.add(convertor.convert(book));
        }
        return bookTOs;
    }

    public BookTO findBookById(Long id) {
        return convertor.convert(bookDao.findBookById(id));
    }

    @Secured({"ROLE_ADMIN"})
    public void deleteBook(BookTO bookTO) {
        bookDao.deleteBook(convertor.convert(bookTO));
        bookTO.setId(null);
    }

    @Secured({"ROLE_ADMIN"})
    public void updateBook(BookTO bookTO) {
        bookDao.updateBook(convertor.convert(bookTO));
    }

    public List<BookTO> findBooksForImpression(ImpressionTO impression) {
        List<BookTO> bookTOs = new ArrayList<BookTO>();
        List<Book> bookEntities;
        bookEntities = bookDao.findBooksForImpression(convertor.convert(impression));
        for (Book book : bookEntities) {
            bookTOs.add(convertor.convert(book));
        }
        return bookTOs;
    }

    public List<BookTO> findNotBorrowedBooks() {
        List<BookTO> bookTOs = new ArrayList<BookTO>();
        List<Book> bookEntities;
        bookEntities = bookDao.findNotBorrowedBooks();
        for (Book book : bookEntities) {
            bookTOs.add(convertor.convert(book));
        }
        return bookTOs;
    }
}
