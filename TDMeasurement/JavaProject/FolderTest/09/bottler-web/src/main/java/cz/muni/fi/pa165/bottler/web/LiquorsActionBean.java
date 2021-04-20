package cz.muni.fi.pa165.bottler.web;

import cz.muni.fi.pa165.bottler.data.dto.LiquorDto;
import cz.muni.fi.pa165.bottler.data.dto.ProducerDto;
import cz.muni.fi.pa165.bottler.service.CompanyRegisterService;
import cz.muni.fi.pa165.bottler.service.LiquorBottleService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * Liquros Action Bean
 *
 * @author Jakub Macak <374551@mail.muni.cz>
 */
@UrlBinding("/liquors/{$event}/{liquor.id}")
public class LiquorsActionBean extends BaseActionBean implements ValidationErrorHandler {
    
  
    @SpringBean
    protected LiquorBottleService liquorBottleService;

    @SpringBean
    protected CompanyRegisterService companyRegisterService;

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

    public List<LiquorDto> getLiquors() {
        return liquors;
    }

    private List<ProducerDto> producers;

    public List<ProducerDto> getProducers() {
        producers = companyRegisterService.getAllProducers();
        return producers;
    }

    @ValidateNestedProperties(value = {
        @Validate(on = {"create", "update"}, field = "ean", required = true),
        @Validate(on = {"create", "update"}, field = "name", required = true),
        @Validate(on = {"create", "update"}, field = "alcoholPercentage", required = true, mask = "^((100)|(\\d{0,2}))$"),
        @Validate(on = {"create", "update"}, field = "volume", required = true, mask = "^(?!0\\d)\\d*(\\.\\d+)?$"),
        @Validate(on = {"create", "update"}, field = "producer", required = true, converter = ObjectTypeConverter.class)
    })
    private LiquorDto liquor;

    public void setLiquor(LiquorDto liquor) {
        this.liquor = liquor;
    }
    public LiquorDto getLiquor() {
        return this.liquor;
    }

    // listing all
    @DefaultHandler
    public Resolution list() {
     
        liquors = liquorBottleService.getAllLiquors();
        return new ForwardResolution("/liquors.jsp");
    }

    
   
    public Resolution create() {
        try{
            liquor.setProducer(companyRegisterService.findProducerById(Long.parseLong(getContext().getRequest().getParameter("liquor.producer"))));
            liquorBottleService.createLiquor(liquor);
            getContext().getMessages().add(new LocalizableMessage("liquor.was_created"));
            return new RedirectResolution(this.getClass(), "list");
        }catch(Exception e)
        {
            getContext().getMessages().add(new LocalizableError("liquor.wasnt_created"));
            return new RedirectResolution(this.getClass(), "list");
        }
    }
    
    public Resolution edit()
    {
        return new ForwardResolution("/liquorEdit.jsp");

    }        
    
    public Resolution delete()
    {
         try{
            liquor = liquorBottleService.findLiquorById(liquor.getId());
            liquorBottleService.removeLiquor(liquor);
            getContext().getMessages().add(new LocalizableMessage("liquor.was_deleted"));
         }catch(Exception e){
             getContext().getMessages().add(new LocalizableMessage("liquor.wasnt_deleted"));
         }
        return new RedirectResolution(this.getClass(), "list");
    }
        
        

    public Resolution update() {
        
        try{
            liquor.setProducer(companyRegisterService.findProducerById(Long.parseLong(getContext().getRequest().getParameter("liquor.producer"))));
            liquorBottleService.updateLiquor(liquor);
            getContext().getMessages().add(new LocalizableMessage("liquor.was_saved"));
            return new RedirectResolution(this.getClass(), "list");
        }catch(Exception e){
            getContext().getValidationErrors().addGlobalError(new LocalizableError("liquor.wasnt_saved"));
            return new ForwardResolution(this.getClass(), "edit");
        }

        
    }
    
    public Resolution detail()
    {
        
        return new ForwardResolution("/liquorDetail.jsp");
    }
        

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        liquors = liquorBottleService.getAllLiquors();
        return null;
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "update", "detail"})
    public Resolution loadLiquorFromDatabase()
    {
        String ids = getContext().getRequest().getParameter("liquor.id");
        if (ids == null) return new ErrorResolution(400, "ID not specified.");
        
        liquor = liquorBottleService.findLiquorById(Long.parseLong(ids));
        
        if (liquor == null) return new ErrorResolution(404, "Liquor with this ID not found.");
        
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
