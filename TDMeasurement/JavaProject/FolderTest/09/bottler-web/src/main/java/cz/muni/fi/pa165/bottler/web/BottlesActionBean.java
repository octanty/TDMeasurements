package cz.muni.fi.pa165.bottler.web;

import cz.muni.fi.pa165.bottler.data.dto.BottleDto;
import cz.muni.fi.pa165.bottler.data.dto.LiquorDto;
import cz.muni.fi.pa165.bottler.data.dto.StampDto;
import cz.muni.fi.pa165.bottler.data.dto.StoreDto;
import cz.muni.fi.pa165.bottler.data.dto.TestResultDto;
import cz.muni.fi.pa165.bottler.service.CompanyRegisterService;
import cz.muni.fi.pa165.bottler.service.LiquorBottleService;
import cz.muni.fi.pa165.bottler.service.StampService;
import cz.muni.fi.pa165.bottler.service.TestResultService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;

import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Bottles Action Bean
 *
 * @author Jakub Macak <374551@mail.muni.cz>
 */
@UrlBinding("/bottles/{$event}/{bottle.id}")
public class BottlesActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected LiquorBottleService liquorBottleService;
    
     @SpringBean
    protected TestResultService testResultService;
    
     @SpringBean
    protected StampService stampService;
    @SpringBean
    protected CompanyRegisterService companyRegisterService;
    private List<BottleDto> bottles;

    public List<BottleDto> getBottles() {
        return bottles;
    }
    
    private boolean userIsPermittedToEdit;
    
    private boolean userIsPermittedToDelete;
    
    private boolean userIsPermittedToCreate;

    public boolean isUserIsPermittedToEdit() {
        return userIsPermittedToEdit;
    }

    public boolean isUserIsPermittedToDelete() {
        return userIsPermittedToDelete;
    }
    
    public boolean isUserIsPermittedToCreate() {
        return userIsPermittedToCreate;
    }
    
    private List<LiquorDto> liquors;
    private List<StampDto> stamps;
    private List<StoreDto> stores;
    private List<TestResultDto> testResults;

    @ValidateNestedProperties(value = {
        @Validate(on = {"create", "update"}, field = "liquor", required = true, converter = ObjectTypeConverter.class),
        @Validate(on = {"create", "update"}, field = "stamp", required = true, converter = ObjectTypeConverter.class),
        @Validate(on = {"create", "update"}, field = "producedDate", required = true, converter = JodaDateTimeConverter.class),
        @Validate(on = {"create", "update"}, field = "store", required = false, converter = ObjectTypeConverter.class),
        @Validate(on = {"create", "update"}, field = "sold", required = false, converter = BooleanTypeConverter.class),
        @Validate(on = {"create", "update"}, field = "lotCode", required = true)
    })
    private BottleDto bottle;

    public void setBottle(BottleDto bottle) {
        this.bottle = bottle;
    }

    public BottleDto getBottle() {
        return this.bottle;
    }

    public List<LiquorDto> getLiquors() {
        liquors = liquorBottleService.getAllLiquors();
        return liquors;
    }
    

    public List<StampDto> getStamps() {
        stamps = stampService.getAllStamps();
        return stamps;
    }

    public List<StoreDto> getStores() {
        stores = companyRegisterService.getAllStores();
        return stores;
    }
    
    public List<TestResultDto> getTestResults()
    {
        return testResults;
    }

    // listing all
    @DefaultHandler
    public Resolution list() {

        bottles = liquorBottleService.getAllBottles();
        return new ForwardResolution("/bottles.jsp");
    }

    public Resolution create() {
        try {
            bottle.setLiquor(liquorBottleService.findLiquorById(Long.parseLong(getContext().getRequest().getParameter("bottle.liquor"))));
            bottle.setStamp(stampService.findStampById(Long.parseLong(getContext().getRequest().getParameter("bottle.stamp"))));
            String storeId = getContext().getRequest().getParameter("bottle.store");
            if(storeId != null)
            {
                bottle.setStore(companyRegisterService.findStoreById(Long.parseLong(storeId)));
            }else{
                bottle.setStore(null);
            }
            
            liquorBottleService.createBottle(bottle);
            getContext().getMessages().add(new LocalizableError("bottle.was_created"));
            return new RedirectResolution(this.getClass(), "list");
        } catch (Exception e) {
            getContext().getMessages().add(new LocalizableError("bottle.wasnt_created"));
            return new RedirectResolution(this.getClass(), "list");
        }
    }

    public Resolution edit() {
        return new ForwardResolution("/bottleEdit.jsp");

    }

    public Resolution delete() {
        try {
            bottle = liquorBottleService.findBottleById(bottle.getId());
            liquorBottleService.removeBottle(bottle);
            getContext().getMessages().add(new LocalizableMessage("bottle.was_deleted"));
        } catch (Exception e) {
            getContext().getMessages().add(new LocalizableMessage("bottle.wasnt_saved"));
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution update() {

        try {
            bottle.setLiquor(liquorBottleService.findLiquorById(Long.parseLong(getContext().getRequest().getParameter("bottle.liquor"))));
            bottle.setStamp(stampService.findStampById(Long.parseLong(getContext().getRequest().getParameter("bottle.stamp"))));
            String storeId = getContext().getRequest().getParameter("bottle.store");
            if(storeId != null)
            {
                bottle.setStore(companyRegisterService.findStoreById(Long.parseLong(storeId)));
            }else{
                bottle.setStore(null);
            }
            liquorBottleService.updateBottle(bottle);
            getContext().getMessages().add(new LocalizableMessage("bottle.was_saved"));
            return new RedirectResolution(this.getClass(), "list");
        } catch (Exception e) {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("bottle.wasnt_saved"));
            return new ForwardResolution(this.getClass(), "edit");
        }


    }
    
    public Resolution sell()
    {
        try{
            // sell the bottle 
            bottle = liquorBottleService.findBottleById(bottle.getId());
            bottle.setSold(true);
            liquorBottleService.updateBottle(bottle);
            getContext().getMessages().add(new LocalizableMessage("bottle.was_saved"));
        }catch(Exception e)
        {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("bottle.wasnt_saved"));
        }
        
        RedirectResolution res = new RedirectResolution("/bottles/detail/" + bottle.getId());    
        return res;
    }

    public Resolution detail() {
        
       
        // list of results for this bottle
        testResults = testResultService.findTestResultsByBottle(bottle);
        
        
        return new ForwardResolution("/bottleDetail.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        bottles = liquorBottleService.getAllBottles();
        return null;
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "update", "detail", "sell"})
    public Resolution loadBottleFromDatabase() {
        String ids = getContext().getRequest().getParameter("bottle.id");
        if (ids == null) {
            return new ErrorResolution(400, "ID not specified.");
        }

        bottle = liquorBottleService.findBottleById(Long.parseLong(ids));

        if (bottle == null) {
            return new ErrorResolution(404, "Bottle with this ID not found.");
        }

        return null;
    }    
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"list"})
    public Resolution loadCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        userIsPermittedToCreate = currentUser.hasRole("admin");
        userIsPermittedToEdit = currentUser.hasRole("admin");
        userIsPermittedToDelete = currentUser.hasRole("admin");
        return null;
    }
}
