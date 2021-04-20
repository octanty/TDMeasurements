package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.BookDao;
import cz.muni.fi.pa165.library.services.BookService;
import cz.muni.fi.pa165.library.utils.Convertor;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.services.BookServiceImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.dao.DataAccessException;
import static org.mockito.Mockito.*;

/**
 *
 * @author MiškoHu
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Convertor.class)
public class BookServiceImplTest extends TestCase {
   
    @Mock
    private BookDao bookDAO;
    
    @InjectMocks
    private BookService bookService = new BookServiceImpl();
    
    @Mock
    private Book book;
    
    @Mock
    private BookTO bookTO;
    
    private Convertor convertor;
    
    @Before
    @Override
    public void setUp() {
        convertor = PowerMockito.mock(Convertor.class);
        
        when(convertor.convert((Book)null)).thenReturn(null);
        when(convertor.convert((BookTO)null)).thenReturn(null);
        when(convertor.convert(book)).thenReturn(bookTO);
        when(convertor.convert(bookTO)).thenReturn(book);
    }
    
       @Test(expected=Exception.class)
    public void testCreateNullBook() {
        doThrow(new Exception("null argument")).when(bookDAO).createBook(null);
        bookService.createBook(null);
    }
    
    
    @Test(expected=DataAccessException.class)
    public void testFindBookByNullId() {
        when(bookDAO.findBookById(null)).thenThrow(new DataAccessException("null book id") {}); 
        
        bookService.findBookById(null);
    }
    
    @Test(expected=DataAccessException.class) 
    public void testFindBookWithWrongId() {
        when(bookDAO.findBookById(new Long(-1))).thenThrow(new DataAccessException("illegal book id") {});
        bookService.findBookById(new Long(-1));
    }

    @Test(expected=Exception.class)
    public void testFindBookByCorrectId() {
        when(bookDAO.findBookById(1l)).thenReturn(book);
        assertEquals(bookTO, bookService.findBookById(1l));
    }
    
    @Test(expected=Exception.class)
    public void testUpdateBookWithNullID() {
       doThrow(new DataAccessException("null book id") {}).when(bookDAO).updateBook(book);
       bookService.updateBook(null);
    }
    /*
    @Test
    public void testDeleteBook() {
        bookService.deleteBook(bookTO);
        verify(bookDAO).deleteBook(book);
    }
    */

    @Test(expected=Exception.class)
    public void testUpdateBook() {
        bookService.updateBook(bookTO);
        verify(bookDAO).updateBook(book);
    }
}
