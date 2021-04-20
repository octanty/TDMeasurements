package cz.muni.fi.pa165.bottler.web;

import cz.muni.fi.pa165.bottler.data.dto.BottleDto;
import cz.muni.fi.pa165.bottler.data.dto.TestResultDto;
import cz.muni.fi.pa165.bottler.service.LiquorBottleService;
import cz.muni.fi.pa165.bottler.service.TestResultService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;

/**
 * Action bean for tests
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
@UrlBinding("/testresults/{$event}/{testResult.id}")
public class TestResultsActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected TestResultService testResultService;
    @SpringBean
    protected LiquorBottleService liquorBottleService;
    // list for results
    private List<TestResultDto> tests;

    public List<TestResultDto> getTests() {
        return tests;
    }
    private List<BottleDto> bottles;

    public List<BottleDto> getBottles() {
        bottles = liquorBottleService.getAllBottles();
        return bottles;
    }
    // one testResult
    @ValidateNestedProperties(value = {
        @Validate(on = {"create", "update"}, field = "bottle", required = true, converter = ObjectTypeConverter.class),
        @Validate(on = {"create", "update"}, field = "time",
                required = true, converter = JodaDateTimeConverter.class)
    })
    private TestResultDto testResult;

    private BottleDto bottle;

    public void setTestResult(TestResultDto testResult) {
        this.testResult = testResult;
    }

    public TestResultDto getTestResult() {
        return this.testResult;
    }

    // listing all
    @DefaultHandler
    public Resolution list() {

        tests = testResultService.getAllTestResults();
        return new ForwardResolution("/tests.jsp");
    }

    public Resolution create() {
        try {
            if (testResult.isToxic() == null) {
                testResult.setToxic(Boolean.FALSE);
            }
            bottle = liquorBottleService.findBottleById(Long.parseLong(getContext().getRequest().getParameter("testResult.bottle")));
            testResult.setBottle(null);
            TestResultDto tr = testResultService.createTestResult(testResult);
            tr.setBottle(bottle);
            testResultService.updateTestResult(tr);
            getContext().getMessages().add(new LocalizableMessage("testresult.was_created"));
        } catch (Exception e) {
            getContext().getMessages().add(new LocalizableError("testresult.wasnt_created"));
        }
        
        return new RedirectResolution("/bottles/detail/" + getContext().getRequest().getParameter("testResult.bottle"));
    }

    public Resolution edit() {
        return new ForwardResolution("/testResultEdit.jsp");

    }

    public Resolution delete() {
        try {
            testResult = testResultService.findTestResultById(testResult.getId());
            testResultService.removeTestResult(testResult);
            getContext().getMessages().add(new LocalizableMessage("testresult.was_deleted"));
        } catch (Exception e) {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("testresult.wasnt_deleted"));
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution update() {

        try {
            if (testResult.isToxic() == null) {
                testResult.setToxic(Boolean.FALSE);
            }
            testResult.setBottle(liquorBottleService.findBottleById(Long.parseLong(getContext().getRequest().getParameter("testResult.bottle"))));
            testResultService.updateTestResult(testResult);
            getContext().getMessages().add(new LocalizableMessage("testresult.was_saved"));
            return new RedirectResolution(this.getClass(), "list");
        } catch (Exception e) {
            getContext().getMessages().add(new LocalizableMessage("testresult.wasnt_saved"));
            return new ForwardResolution(this.getClass(), "edit");
        }


    }

    public Resolution detail() {

        return new ForwardResolution("/testResultDetail.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        tests = testResultService.getAllTestResults();
        return null;
    }

    /**
     * Loads testResult from DB before edit and update call
     *
     * @return
     */
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "update", "detail"})
    public Resolution loadTestResultFromDatabase() {
        String ids = getContext().getRequest().getParameter("testResult.id");
        if (ids == null) {
            return new ErrorResolution(400, "ID not specified.");
        }

        testResult = testResultService.findTestResultById(Long.parseLong(ids));

        if (testResult == null) {
            return new ErrorResolution(404, "TestResult with this ID not found.");
        }

        return null;
    }
}
