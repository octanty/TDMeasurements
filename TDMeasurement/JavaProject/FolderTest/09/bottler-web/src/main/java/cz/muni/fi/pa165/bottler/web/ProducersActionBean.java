package cz.muni.fi.pa165.bottler.web;

import cz.muni.fi.pa165.bottler.data.dto.ProducerDto;
import cz.muni.fi.pa165.bottler.data.dto.StatisticsDto;
import cz.muni.fi.pa165.bottler.service.CompanyRegisterService;
import java.util.List;

import cz.muni.fi.pa165.bottler.service.StatisticsService;
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
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Producers Action Bean
 *
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@UrlBinding("/producers/{$event}/{producer.id}")
public class ProducersActionBean extends BaseActionBean implements ValidationErrorHandler {
    
  
    @SpringBean
    protected CompanyRegisterService companyRegisterService;

    @SpringBean
    protected StatisticsService statisticsService;

    // list for results
    private List<ProducerDto> producers;

    public List<ProducerDto> getProducers() {
        return producers;
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
      
    // one producer
    @ValidateNestedProperties(value = {
         @Validate(on = {"create", "update"}, field = "name", required = true),
        @Validate(on = {"create", "update"}, field = "address", required = true),
        @Validate(on = {"create", "update"}, field = "ico", required = true, mask = "^([0-9])+$")
    })
    private ProducerDto producer;

    public void setProducer(ProducerDto producer) {
        this.producer = producer;
    }
    public ProducerDto getProducer() {
        return this.producer;
    }

    private StatisticsDto statistics;
    public StatisticsDto getStatistics() { return this.statistics; }

    // listing all 
    @DefaultHandler
    public Resolution list() {
     
        producers = companyRegisterService.getAllProducers();
        return new ForwardResolution("/producers.jsp");
    }

    
   
    public Resolution create() {
        try{
            companyRegisterService.createProducer(producer);
              getContext().getMessages().add(new LocalizableMessage("producer.was_created"));
              return new RedirectResolution(this.getClass(), "list");
        }catch(Exception e)
        {
              getContext().getMessages().add(new LocalizableError("producer.wasnt_created"));
              return new RedirectResolution(this.getClass(), "list");
        }
    }
    
    public Resolution edit()
    {
        return new ForwardResolution("/producerEdit.jsp");

    }        
    
    public Resolution delete()
    {
         try{
            producer = companyRegisterService.findProducerById(producer.getId());
            companyRegisterService.removeProducer(producer);
            getContext().getMessages().add(new LocalizableMessage("producer.was_deleted"));
         }catch(Exception e){
             getContext().getMessages().add(new LocalizableMessage("producer.wasnt_deleted"));
         }
        return new RedirectResolution(this.getClass(), "list");
    }
        
        

    public Resolution update() {
        
        try{
            companyRegisterService.updateProducer(producer);
             getContext().getMessages().add(new LocalizableMessage("producer.was_saved"));
             return new RedirectResolution(this.getClass(), "list");
        }catch(Exception e){
             getContext().getValidationErrors().addGlobalError(new LocalizableError("producer.wasnt_saved"));
             return new ForwardResolution(this.getClass(), "edit");
        }

        
    }
    
    public Resolution detail()
    {
        statistics = statisticsService.getStatisticsForProducer(producer);
        return new ForwardResolution("/producerDetail.jsp");
    }
        

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        producers = companyRegisterService.getAllProducers();
        return null;
    }
    
    /**
     * Loads producer from DB before edit and update call
     * @return 
     */
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "update", "detail"})
    public Resolution loadProducerFromDatabase()
    {
         String ids = getContext().getRequest().getParameter("producer.id");
        if (ids == null) return new ErrorResolution(400, "ID not specified.");
        
        producer = companyRegisterService.findProducerById(Long.parseLong(ids));
        
        if (producer == null) return new ErrorResolution(404, "Producer with this ID not found.");
        
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
