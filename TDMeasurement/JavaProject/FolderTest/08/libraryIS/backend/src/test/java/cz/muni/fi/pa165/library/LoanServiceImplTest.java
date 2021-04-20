/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.services.LoanService;
import cz.muni.fi.pa165.library.daos.LoanDao;
import cz.muni.fi.pa165.library.dtos.LoanTO;
import cz.muni.fi.pa165.library.entities.Loan;
import cz.muni.fi.pa165.library.services.LoanServiceImpl;
import cz.muni.fi.pa165.library.utils.Convertor;
import static org.mockito.Mockito.*;
import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author vit.mica
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Convertor.class)
public class LoanServiceImplTest extends TestCase {

    @Mock
    private LoanDao loanDAO;
    @InjectMocks
    private LoanService loanService = new LoanServiceImpl();
    @Mock
    private Loan loan;
    @Mock
    private LoanTO loanTO;
    private Convertor convertor;

    @Before
    @Override
    public void setUp() {
        convertor = PowerMockito.mock(Convertor.class);

        when(convertor.convert((Loan) null)).thenReturn(null);
        when(convertor.convert((LoanTO) null)).thenReturn(null);
        when(convertor.convert(loan)).thenReturn(loanTO);
        when(convertor.convert(loanTO)).thenReturn(loan);
    }

    @Test(expected = Exception.class)
    public void testCreateNullLoan() {
        doThrow(new Exception("null argument")).when(loanDAO).createLoan(null);
        loanService.createLoan(null);
    }

    @Test(expected = DataAccessException.class)
    public void testFindLoanByNullId() {
        when(loanDAO.findLoanById(null)).thenThrow(new DataAccessException("null loan id") {
        });

        loanService.findLoanById(null);
    }

    @Test(expected = DataAccessException.class)
    public void testFindLoanWithWrongId() {
        when(loanDAO.findLoanById(new Long(-1))).thenThrow(new DataAccessException("illegal loan id") {
        });
        loanService.findLoanById(new Long(-1));
    }

    @Test(expected = Exception.class)
    public void testFindLoanByCorrectId() {
        when(loanDAO.findLoanById(1l)).thenReturn(loan);
        assertEquals(loanTO, loanService.findLoanById(1l));
    }

    @Test(expected = Exception.class)
    public void testUpdateLoanWithNullID() {
        doThrow(new DataAccessException("null loan id") {
        }).when(loanDAO).updateLoan(loan);
        loanService.updateLoan(null);
    }

    @Test(expected = Exception.class)
    public void testUpdateLoan() {
        loanService.updateLoan(loanTO);
        verify(loanDAO).updateLoan(loan);
    }
}
