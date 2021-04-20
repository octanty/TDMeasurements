package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.dao.MockFactory;
import cz.muni.fi.pa165.bottler.data.dao.BottleDao;
import cz.muni.fi.pa165.bottler.data.dao.LiquorDao;
import cz.muni.fi.pa165.bottler.data.dto.BottleDto;
import cz.muni.fi.pa165.bottler.data.dto.LiquorDto;
import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.Liquor;
import static cz.muni.fi.pa165.bottler.service.EntityAndDtoMapping.*;
import cz.muni.fi.pa165.bottler.service.LiquorBottleService;
import cz.muni.fi.pa165.bottler.service.LiquorBottleServiceImpl;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.ThreadState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
@RunWith(MockitoJUnitRunner.class)
public class LiquorBottleServiceImplTest extends TestCase {

    @InjectMocks
    LiquorBottleService instance = new LiquorBottleServiceImpl();
    @Mock
    private BottleDao bottleDao;
    @Mock
    private LiquorDao liquorDao;

    private ThreadState _threadState;
    protected Subject _mockSubject;

    @Before
    public void attachSubject()
    {
        _mockSubject = Mockito.mock(Subject.class);
        _threadState = new SubjectThreadState(_mockSubject);
        _threadState.bind();
    }

    @After
    public void detachSubject()
    {
        _threadState.clear();
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of createLiquor method, of class LiquorBottleServiceImpl.
     */
    @Test
    public void testCreateLiquor() {

        Mockito.when(_mockSubject.hasRole("admin")).thenReturn(true);

        System.out.println("createLiquor");
        final Liquor liquor = MockFactory.on(Liquor.class).create(null, false);
        LiquorDto liquorDto = liquorToLiquorDto(liquor);

        instance.createLiquor(liquorDto);
        Mockito.verify(liquorDao).create(liquor);
    }

    /**
     * Test of updateLiquor method, of class LiquorBottleServiceImpl.
     */
    @Test
    public void testUpdateLiquor() {

        Mockito.when(_mockSubject.hasRole("admin")).thenReturn(true);

        System.out.println("updateLiquor");
        final Liquor liquor = MockFactory.on(Liquor.class).create(null);
        LiquorDto liquorDto = liquorToLiquorDto(liquor);

        Mockito.when(liquorDao.findById(liquor.getId())).thenReturn(liquor);
        Mockito.when(liquorDao.update(liquor)).thenReturn(liquor);

        LiquorDto result = instance.updateLiquor(liquorDto);
        assertEquals(liquorDto, result);

        Mockito.verify(liquorDao).update(liquor);

    }

    /**
     * Test of removeLiquor method, of class LiquorBottleServiceImpl.
     */
    @Test
    public void testRemoveLiquor() {

        Mockito.when(_mockSubject.hasRole("admin")).thenReturn(true);

        System.out.println("removeLiquor");
        final Liquor liquor = MockFactory.on(Liquor.class).create(null);
        LiquorDto liquorDto = liquorToLiquorDto(liquor);

        Mockito.when(liquorDao.findById(liquor.getId())).thenReturn(liquor);

        instance.removeLiquor(liquorDto);

        Mockito.verify(liquorDao).remove(liquor);
    }

    /**
     * Test of createBottle method, of class LiquorBottleServiceImpl.
     */
    @Test
    public void testCreateBottle() {

        Mockito.when(_mockSubject.hasRole("admin")).thenReturn(true);

        System.out.println("createBottle");
        final Bottle bottle = MockFactory.on(Bottle.class).create(null, false);
        BottleDto bottleDto = bottleToBottleDto(bottle);

        instance.createBottle(bottleDto);
        Mockito.verify(bottleDao).create(bottle);
    }

    /**
     * Test of updateBottle method, of class LiquorBottleServiceImpl.
     */
    @Test
    public void testUpdateBottle() {

        Mockito.when(_mockSubject.hasRole("admin")).thenReturn(true);

        System.out.println("updateBottle");
        final Bottle bottle = MockFactory.on(Bottle.class).create(null);
        BottleDto bottleDto = bottleToBottleDto(bottle);

        Mockito.when(bottleDao.findById(bottle.getId())).thenReturn(bottle);
        Mockito.when(bottleDao.update(bottle)).thenReturn(bottle);

        BottleDto result = instance.updateBottle(bottleDto);
        assertEquals(bottleDto, result);

        Mockito.verify(bottleDao).update(bottle);
    }

    /**
     * Test of removeBottle method, of class LiquorBottleServiceImpl.
     */
    @Test
    public void testRemoveBottle() {

        Mockito.when(_mockSubject.hasRole("admin")).thenReturn(true);

        System.out.println("removeBottle");
        final Bottle bottle = MockFactory.on(Bottle.class).create(null);
        BottleDto bottleDto = bottleToBottleDto(bottle);

        Mockito.when(bottleDao.findById(bottle.getId())).thenReturn(bottle);

        instance.removeBottle(bottleDto);

        Mockito.verify(bottleDao).remove(bottle);
    }
}
