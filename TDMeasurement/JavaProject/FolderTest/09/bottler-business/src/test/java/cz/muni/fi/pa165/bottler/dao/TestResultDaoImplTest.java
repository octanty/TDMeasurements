package cz.muni.fi.pa165.bottler.dao;

import cz.muni.fi.pa165.bottler.data.dao.TestResultDao;
import cz.muni.fi.pa165.bottler.data.dao.TestResultDaoImpl;
import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.TestResult;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

import junit.framework.TestCase;
import org.joda.time.DateTime;

/**
 * Bottle is a physical representation of a drink.
 *
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
public class TestResultDaoImplTest extends TestCase {

    private TestResultDao testResultDao;
    private EntityManager em;

    private BottlerTestDb testDb;

    public TestResultDaoImplTest(String testName) {
        super(testName);
        testDb = new BottlerTestDb();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        //start db and get entity manager
        testDb.startDb();
        em = testDb.getEntityManager();

        // dao result dao
        testResultDao = new TestResultDaoImpl(em);


    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        testDb.stopDb();
    }


    /**
     * When creating null result, IllegalArgumentException should be thrown
     */
    public void testCreateResultWithNull() {

        Throwable exception = null;

        try {
            TestResult result = null;
            em.getTransaction().begin();
            testResultDao.create(result);
            em.getTransaction().commit();
        } catch (IllegalArgumentException ex) {
            exception = ex;
        }

        assertNotNull("Creating null dao result should throw IllegalArgumentException", exception);

    }

    /**
     * Tests creating ordinary result
     */
    public void testCreateOrdinaryResult() {
        Bottle bottle = MockFactory.on(Bottle.class).create(em);

        TestResult result = new TestResult();
        result.setBottle(bottle);
        result.setTime(new DateTime());
        result.setToxic(false);

        try{
            em.getTransaction().begin();
            testResultDao.create(result);
            em.getTransaction().commit();
        }catch(Exception e){
            fail("Error while creating ordinary TestResult.");
        }
    }
    
    /**
     * Tests creating result with already assigned ID
     */
    public void testCreateResultWithIdSet() {
       TestResult result = MockFactory.on(TestResult.class).create(em);
       
        Exception ex = null;

        try{
            testResultDao.create(result); 
        }catch(IllegalArgumentException e){
            ex = e;
        }
        
        if(ex == null){
            fail("TestResult with already assigned ID could be created.");
        }
    }
    
    
    /**
     * Tests updating result
     */
    public void testUpdatingResult()
    {
        TestResult result = MockFactory.on(TestResult.class).create(em);
        result.setToxic(!result.isToxic());
        
        DateTime newDate = result.getTime().minusYears(2);
        result.setTime(newDate);
        
         try{
             em.getTransaction().begin();
            testResultDao.update(result);
            em.getTransaction().commit();
        }catch(Exception e){
            fail("Error while updating ordinary TestResult.");
        }
    }
    
    /**
     * Tests updating result with incorrect data
     */
    public void testUpdatingResultWithIncorrectData()
    {
        TestResult result = MockFactory.on(TestResult.class).create(em);
        result.setBottle(null);
        result.setTime(null);
        
        Exception ex = null;

        try{
            em.getTransaction().begin();
            testResultDao.update(result);  
            em.getTransaction().commit();
        }catch(Exception e){
            ex = e;
        }
        
        if(ex == null){
            fail("TestResult with incorrect values could be created.");
        }
    }
    
    /**
     * Tests removing result
     */
    public void testRemoveResult()
    {
        TestResult result = MockFactory.on(TestResult.class).create(em);
       
        try{
            em.getTransaction().begin();
            testResultDao.remove(result);
            em.getTransaction().commit();
        }catch(Exception e){
            fail("Error while removing ordinary TestResult.");
        }
    }
    
   
   
    
    /**
     * Tests updating result, which was didn't exist
     */
    public void testUpdateNonExistingResult()
    {
        TestResult result = MockFactory.on(TestResult.class).create(null, false);
        
        Exception ex = null;

        try{
            em.getTransaction().begin();
            testResultDao.update(result);
            em.getTransaction().commit();
        }catch(Exception e){
            ex = e;
        }
        
        if(ex == null){
            fail("Not stored TestResult can't be updated.");
        }
    }
    
    /**
     * Tests listing of all entities
     */
    public void testGetAll()
    {
        int count = 10;
        MockFactory.on(TestResult.class).create(em, count);
        assertEquals("Count of all items in DB should match with number of created items.", count, testResultDao.getAll().size());
    }
    
    /**
     * Tests finding by ID. First result should be found, second should be null.
     */
    public void testFindById(){
        
        List<TestResult> results = MockFactory.on(TestResult.class).create(em, 20);
        TestResult toFind = results.get(10);
        
        TestResult found = testResultDao.findById(toFind.getId());
        assertNotNull("Found result shouldn't be NULL.", found);
        
        assertEquals("Found result should match the initial result.", toFind, found);
        em.getTransaction().begin();
        em.remove(toFind);
        em.getTransaction().commit();
        found = testResultDao.findById(toFind.getId());
        assertNull("Found result should be NULL (item was removed).", found);
   }
    
    /**
     * Tests finding bottle by passing null
     */
    public void testFindByBottleWithNullPassed() {
       
        List<TestResult> found = testResultDao.findByBottle(null);
        assertEquals("No tests with null bottle are permited.", 0, found.size());
    }
    
    /**
     * Tests finding by toxicity. First result should be found, second should be null.
     */
    public void testFindByToxicity(){
        
       
        // not toxic
        List<TestResult> results = MockFactory.on(TestResult.class).create(em, 20);
        for(TestResult res : results){
            res.setToxic(false);
            em.getTransaction().begin();
            em.merge(res);
            em.getTransaction().commit();
        }
        
        
        // toxic
        List<TestResult> results2 = MockFactory.on(TestResult.class).create(em, 4);
        for(TestResult res : results2){
            res.setToxic(true);
            em.getTransaction().begin();
            em.merge(res);
            em.getTransaction().commit();
        }
       
        
        
        List<TestResult> found = testResultDao.findByToxicity(false);
        assertEquals("Number of found intoxic results should match.", 20, found.size());
        
        List<TestResult> found2 = testResultDao.findByToxicity(true);
        assertEquals("Number of found toxic results should match.", 4, found2.size());
        
       
   }
    
    /**
     * Testing find by bottle
     */
    public void testFindByBottle()
    {
        // tests count
        int count = 5;
        
        // create some
        MockFactory.on(TestResult.class).create(em, 20);
        
        // our bottle
        Bottle b = MockFactory.on(Bottle.class).create(em);
      
        // create our tests
        List<TestResult> createdResults = new ArrayList<>();
        for(int i = 0; i < count; i++ )
        {
            TestResult testResult = new TestResult();
            testResult.setBottle(b);
            testResult.setTime(new DateTime());
            testResult.setToxic(i%2 == 0);
            
            
            em.getTransaction().begin();
            em.persist(testResult);
            em.getTransaction().commit();
            
            createdResults.add(testResult);
        }
        
        // create some more
        MockFactory.on(TestResult.class).create(em, 20);
        
        // dao find
        List<TestResult> foundResults = testResultDao.findByBottle(b);
        
        assertEquals("Number of found tests should be match with created.", count, foundResults.size());
        
        // dao match
        assertEquals("Tests should be match with created.", foundResults, createdResults);
    
    }
    
    /**
     * Test finding real values by time
     */
    public void testFindByTime()
    {
        // create some results
        List<TestResult> results = MockFactory.on(TestResult.class).create(em, 50);
        
        // randomize time
        for(TestResult result : results){
            
            // minus some days in two years
            int daysDifference = 1 + (int) Math.round(Math.random() * 720);
            result.setTime(result.getTime().minusDays(daysDifference)); 
            
            // save 
            em.getTransaction().begin();
            em.merge(result);
            em.getTransaction().commit();
        }
        
        // change date of some and try to find them
        List<TestResult> changedResults = new ArrayList<>();
        changedResults.add(results.get(10));
        changedResults.add(results.get(15));
        changedResults.add(results.get(25));
        
        // randomize time to get even olderresults
        for(TestResult result : changedResults){
            
            // minus exactly two years
            result.setTime(result.getTime().minusYears(2)); 
            
            // save 
            em.getTransaction().begin();
            em.merge(result);
            em.getTransaction().commit();
        }
        
        // try to find these changed results
        DateTime start = new DateTime().minusYears(4);
        DateTime end = new DateTime().minusYears(2);
        
        List<TestResult> foundResults = testResultDao.findByTime(start, end);
        
        assertEquals("Created and found results doesn't match", changedResults, foundResults);
        
        
    }
    
    /**
     * Test finding by time - switched start end
     */
    public void testFindByTimeSwitchedStartEnd()
    {
        // create some results
        List<TestResult> results = MockFactory.on(TestResult.class).create(em, 50);
        
        // randomize time
        for(TestResult result : results){
            
            // minus some days in two years
            int daysDifference = 1 + (int) Math.round(Math.random() * 720);
            result.setTime(result.getTime().minusDays(daysDifference)); 
            
            // save 
            em.getTransaction().begin();
            em.merge(result);
            em.getTransaction().commit();
        }
        
        DateTime start = new DateTime().minusYears(2);
        DateTime end = new DateTime();
        
        List<TestResult> foundResults = testResultDao.findByTime(end, start);
        
        assertEquals("When passed start as second, no results should be found.", 0, foundResults.size());
        
        
    }
    
    /**
     * Test finding by time - switched start end
     */
    public void testFindByTimeAndReturnAll()
    {
        // create some results
        List<TestResult> results = MockFactory.on(TestResult.class).create(em, 50);
        
        // randomize time
        for(TestResult result : results){
            
            // minus some days in two years
            int daysDifference = 1 + (int) Math.round(Math.random() * 720);
            result.setTime(result.getTime().minusDays(daysDifference)); 
            
            // save 
            em.getTransaction().begin();
            em.merge(result);
            em.getTransaction().commit();
        }
        
        DateTime start = new DateTime().minusYears(2);
        DateTime end = new DateTime();
        
        List<TestResult> foundResults = testResultDao.findByTime(start, end);
        
        assertEquals("Found results should be all results.", results, foundResults);
        
        
    }
    
    
     /**
     * Tests finding by null time, exception should be thrown
     */
    public void testFindByNullTime()
    {
       
        Exception ex = null;

        try{
            testResultDao.findByTime(new DateTime(), null);  
        }catch(IllegalArgumentException e){
            ex = e;
        }
        
         try{
            testResultDao.findByTime(null, new DateTime());  
         }catch(IllegalArgumentException e){
            ex = e;
         }
         
         try{
            testResultDao.findByTime(null, null);  
         }catch(IllegalArgumentException e){
            ex = e;
         }
        
        if(ex == null){
            fail("Finding result can't be called with null.");
        }
    }
    
}
