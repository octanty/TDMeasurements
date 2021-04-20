package cz.muni.fi.pa165.bottler.data.dao;

import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.TestResult;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Inteface for TestResult dao object
 * Test result contains toxicity info about bottle
 *
 * @author Josef Ludvicek <374268@mail.muni.cz>
 */
public interface TestResultDao {

    /**
     * Create new TestResult in db
     * @param t TestResult instance to create
     * @return created instance
     * @throws IllegalArgumentException if t is null or if t already has id
     */
    TestResult create(TestResult t);

    /**
     * Update test result entity in db
     * @param t test result instance to update
     * @return updated instance
     */
    TestResult update(TestResult t);

    /**
     * Remove TestResult from db
     * @param t
     * @throws IllegalArgumentException if t is null
     */
    void remove(TestResult t);

    /**
     * Find TestResult instance by id
     *
     * @param id id of test result > 0
     * @return TestResult instance if found, null otherwise
     * @throws IllegalArgumentException if id is null or lover than 0
     */
    TestResult findById(Long id);

    /**
     * Find TestResult instance by bottle instance
     * @param bottle bottle instance
     * @return List of TestResults if found, empty list otherwise
     * @throws IllegalArgumentException if bottle instance is null
     */
    List<TestResult> findByBottle(Bottle bottle);

    /**
     * Find TestResult instance by interval of dates when test was done
     * @param from lowest date
     * @param to highest date
     * @return List of TestResults, empty list if nothing found
     */
    List<TestResult> findByTime(DateTime from, DateTime to);

    /**
     * Find test result by toxicity - if bottle was toxic or not
     * @param toxic toxicity of bottle
     * @return List of TestResults if found, empty list otherwise
     */
    List<TestResult> findByToxicity(Boolean toxic);


    /**
     * Get all TestResults from db
     * @return list of all TestResult instances
     */
    List<TestResult> getAll();
}
