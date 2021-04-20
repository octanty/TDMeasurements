package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.dao.MockFactory;
import cz.muni.fi.pa165.bottler.data.dao.TestResultDao;
import cz.muni.fi.pa165.bottler.data.dto.BottleDto;
import cz.muni.fi.pa165.bottler.data.dto.TestResultDto;
import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.TestResult;
import junit.framework.TestCase;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.ThreadState;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test of TestResultServiceImpl using Mockito
 *
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@RunWith(MockitoJUnitRunner.class)
public class TestResultServiceImplTest extends TestCase {

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

    @InjectMocks
    TestResultService testResultService = new TestResultServiceImpl();

    @Mock
    private TestResultDao testResultDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testCreateTestResult() {

        Mockito.when(_mockSubject.hasRole("tester")).thenReturn(true);

        final TestResult someResult = MockFactory.on(TestResult.class).create(null, false);
        final TestResultDto someResultDto = EntityAndDtoMapping.testResultToTestResultDto(someResult);

        testResultService.createTestResult(someResultDto);

        // whether create on TestResultDao was called on the right item
        Mockito.verify(testResultDao).create(someResult);


    }

    @Test
    public void testUpdateTestResult() {

        Mockito.when(_mockSubject.hasRole("tester")).thenReturn(true);

        // other values
        DateTime otherTime = new DateTime(4645564);
        Bottle otherBottle = MockFactory.on(Bottle.class).create(null);
        BottleDto otherBottleDto = EntityAndDtoMapping.bottleToBottleDto(otherBottle);
        boolean otherToxic = true;

        // our result
        TestResult someResult = MockFactory.on(TestResult.class).create(null);
        someResult.setToxic(false);

        // update DTO with other values
        TestResultDto someResultDto = EntityAndDtoMapping.testResultToTestResultDto(someResult);
        someResultDto.setBottle(otherBottleDto);
        someResultDto.setTime(otherTime);
        someResultDto.setToxic(otherToxic);

        // mocking DAO
        Mockito.when(testResultDao.findById(someResult.getId())).thenReturn(someResult);

        // call update
        testResultService.updateTestResult(someResultDto);

        // whether update on TestResultDao was called on the right item
        Mockito.verify(testResultDao).update(someResult);

        // compare values
        assertEquals("Bottle should match after update.", otherBottle, someResult.getBottle());
        assertEquals("Time should match after update.", otherTime, someResult.getTime());
        assertEquals("Toxic value should match after update.", (Boolean) otherToxic, (Boolean) someResult.isToxic());
    }

    @Test
    public void testRemoveTestResult() {

        Mockito.when(_mockSubject.hasRole("tester")).thenReturn(true);

        // our result
        TestResult someResult = MockFactory.on(TestResult.class).create(null);
        TestResultDto someResultDto = EntityAndDtoMapping.testResultToTestResultDto(someResult);


        // call delete
        testResultService.removeTestResult(someResultDto);

        // whether update on TestResultDao was called on the right item
        Mockito.verify(testResultDao).remove(someResult);
    }

}