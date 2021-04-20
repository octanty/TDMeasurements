package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dao.TestResultDao;
import cz.muni.fi.pa165.bottler.data.dto.BottleDto;
import cz.muni.fi.pa165.bottler.data.dto.TestResultDto;
import cz.muni.fi.pa165.bottler.data.model.TestResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cz.muni.fi.pa165.bottler.service.EntityAndDtoMapping.*;

/**
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
@Service
@Transactional
public class TestResultServiceImpl implements TestResultService {

    @Autowired
    TestResultDao testResultDao;

    public void setTestResultDao(TestResultDao testResultDao) {
        this.testResultDao = testResultDao;
    }

    private Subject currentUser;

    @Override
    public TestResultDto createTestResult(TestResultDto resultDto) {
        currentUser = SecurityUtils.getSubject();
        if (currentUser.hasRole("tester")) {
            TestResult result = testResultDtoToTestResult(resultDto, true);
            return testResultToTestResultDto(testResultDao.create(result));
        } else {
            return null;
        }
    }

    @Override
    public TestResultDto updateTestResult(TestResultDto resultDto) {
        currentUser = SecurityUtils.getSubject();
        if (currentUser.hasRole("tester")) {
            TestResult result = testResultDao.findById(resultDto.getId());
            result.setToxic(resultDto.isToxic());
            result.setBottle(bottleDtoToBottle(resultDto.getBottle()));
            result.setTime(resultDto.getTime());
            result.setToxic(resultDto.isToxic());
            return testResultToTestResultDto(testResultDao.update(result));
        } else {
            return null;
        }
    }

    @Override
    public void removeTestResult(TestResultDto resultDto) {
        currentUser = SecurityUtils.getSubject();
        if (currentUser.hasRole("tester")) {
            TestResult result = testResultDtoToTestResult(resultDto);
            testResultDao.remove(result);
        }
    }

    @Override
    public List<TestResultDto> getAllTestResults() {
        return EntityAndDtoMapping.testResultToTestResultDto(testResultDao.getAll());
    }
    
    @Override
    public List<TestResultDto> findTestResultsByBottle(BottleDto bottleDto) {
        return EntityAndDtoMapping.testResultToTestResultDto(testResultDao.findByBottle(EntityAndDtoMapping.bottleDtoToBottle(bottleDto)));
    }

    @Override
    public TestResultDto findTestResultById(Long id) {
        TestResult testResult = testResultDao.findById(id);
        return testResultToTestResultDto(testResult);
    }
}
