package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dto.BottleDto;
import cz.muni.fi.pa165.bottler.data.dto.TestResultDto;
import java.util.List;

/**
 * Services for test results
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
public interface TestResultService {

    /**
     * Creates a test result
     * @param resultDto Result to be created
     * @return TestResultDto Created test result
     */
    TestResultDto createTestResult(TestResultDto resultDto);

    /**
     * Updates test result
     * @param resultDto Result to be updated
     * @return TestResultDto Updated test result
     */
    TestResultDto updateTestResult(TestResultDto resultDto);

    /**
     * Removes test result
     * @param resultDto Result to be removed
     */
    void removeTestResult(TestResultDto resultDto);

    /**
     * Return list of all results
     * @return List<TestResultDto> List of all results
     */
    List<TestResultDto> getAllTestResults();

    /**
     * Find test by id
     * @param id ID of required result
     * @return TestResultDto Test result with required ID
     */
    TestResultDto findTestResultById(Long id);

    /**
     * Results of bottle
     * @param bottleDto Bottle whose result should be found
     * @return List<TestResultDto> List of all results
     */
    List<TestResultDto> findTestResultsByBottle(BottleDto bottleDto);
}
