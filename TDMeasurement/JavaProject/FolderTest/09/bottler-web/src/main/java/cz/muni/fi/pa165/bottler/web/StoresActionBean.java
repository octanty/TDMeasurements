package cz.muni.fi.pa165.bottler.web;

import cz.muni.fi.pa165.bottler.data.dto.BottleDto;
import cz.muni.fi.pa165.bottler.data.dto.StatisticsDto;
import cz.muni.fi.pa165.bottler.data.dto.StoreDto;
import cz.muni.fi.pa165.bottler.service.CompanyRegisterService;
import cz.muni.fi.pa165.bottler.service.LiquorBottleService;
import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pa165.bottler.service.StatisticsService;
import cz.muni.fi.pa165.bottler.service.StoreService;
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
 * Action Bean for stores
 *
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@UrlBinding("/stores/{$event}/{store.id}")
public class StoresActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected CompanyRegisterService companyRegisterService;
    @SpringBean
    protected StoreService storeService;
    @SpringBean
    protected LiquorBottleService liquorBottleService;
    @SpringBean
    protected StatisticsService statisticsService;
    // list for results
    private List<StoreDto> stores;

    public List<StoreDto> getStores() {
        return stores;
    }
    // list of bottles on the store
    private List<BottleDto> bottles;

    public List<BottleDto> getBottles() {
        return bottles;
    }
    // one store
    @ValidateNestedProperties(value = {
        @Validate(on = {"create", "update"}, field = "name", required = true),
        @Validate(on = {"create", "update"}, field = "address", required = true),
        @Validate(on = {"create", "update"}, field = "ico", required = true, mask = "^([0-9])+$")
    })
    private StoreDto store;

    public void setStore(StoreDto store) {
        this.store = store;
    }

    public StoreDto getStore() {
        return this.store;
    }
    private StatisticsDto statistics;

    public StatisticsDto getStatistics() {
        return this.statistics;
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
    
    // listing all 
    @DefaultHandler
    public Resolution list() {

        stores = companyRegisterService.getAllStores();
        return new ForwardResolution("/stores.jsp");
    }

    // searching
    public Resolution search() {

        // empty at first
        stores = new ArrayList<>();

        String address = getContext().getRequest().getParameter("store.address");
        if (address != null) {
            if (!address.equals("")) {
                stores = companyRegisterService.findStoreByAddress("%" + address + "%");
            } else {
                getContext().getValidationErrors().add("store.address", new LocalizableError("search.cannotbeempty", ""));
            }
        }

        return new ForwardResolution("/storesSearch.jsp");
    }

    public Resolution create() {
        try {
            companyRegisterService.createStore(store);
            getContext().getMessages().add(new LocalizableMessage("store.was_created"));
            return new RedirectResolution(this.getClass(), "list");
        } catch (Exception e) {
            getContext().getMessages().add(new LocalizableError("store.wasnt_created"));
            return new RedirectResolution(this.getClass(), "list");
        }
    }

    public Resolution edit() {
        return new ForwardResolution("/storeEdit.jsp");

    }

    public Resolution delete() {
        try {
            store = companyRegisterService.findStoreById(store.getId());
            companyRegisterService.removeStore(store);
            getContext().getMessages().add(new LocalizableMessage("store.was_deleted"));
        } catch (Exception e) {
            getContext().getMessages().add(new LocalizableMessage("store.wasnt_deleted"));
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution update() {

        try {
            companyRegisterService.updateStore(store);
            getContext().getMessages().add(new LocalizableMessage("store.was_saved"));
            return new RedirectResolution(this.getClass(), "list");
        } catch (Exception e) {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("store.wasnt_saved"));
            return new ForwardResolution(this.getClass(), "edit");
        }


    }

    public Resolution detail() {
        // load bottles on current store - not sold ones
        List<BottleDto> allBottles = storeService.getAvailableBottles(store);
        bottles = new ArrayList<>();
        for (BottleDto bottle : allBottles) {
            if (!bottle.isSold()) {
                bottles.add(bottle);
            }
        }

        // load statistics of store
        statistics = statisticsService.getStatisticsForStore(store);

        return new ForwardResolution("/storeDetail.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        stores = companyRegisterService.getAllStores();
        return null;
    }

    /**
     * Loads store from DB before edit and update call
     *
     * @return
     */
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "update", "detail"})
    public Resolution loadStoreFromDatabase() {
        String ids = getContext().getRequest().getParameter("store.id");
        if (ids == null) {
            return new ErrorResolution(400, "ID not specified.");
        }

        store = companyRegisterService.findStoreById(Long.parseLong(ids));

        if (store == null) {
            return new ErrorResolution(404, "Store with this ID not found.");
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
