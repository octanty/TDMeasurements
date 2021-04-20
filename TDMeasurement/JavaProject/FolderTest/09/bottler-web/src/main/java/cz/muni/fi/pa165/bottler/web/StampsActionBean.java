package cz.muni.fi.pa165.bottler.web;

import cz.muni.fi.pa165.bottler.data.dto.StampDto;
import cz.muni.fi.pa165.bottler.service.StampService;
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
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Action bean for stamps
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
@UrlBinding("/stamps/{$event}/{stamp.id}")
public class StampsActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected StampService stampService;
    private DateTimeFormatter fmt;
    // list for stamps
    private List<StampDto> stamps;

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

    public List<StampDto> getTests() {
        return stamps;
    }
    // one stamp
    @ValidateNestedProperties(value = {
        @Validate(on = {"create", "update"}, field = "numberOfStamp", required = true),
        @Validate(on = {"create", "update"}, field = "issuedDate",
                required = true, converter = JodaDateTimeConverter.class)
    })
    private StampDto stamp;

    public void setStamp(StampDto stamp) {
        this.stamp = stamp;
    }

    public StampDto getStamp() {
        return this.stamp;
    }

    // listing all
    @DefaultHandler
    public Resolution list() {
        fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        stamps = stampService.getAllStamps();
        return new ForwardResolution("/stamps.jsp");
    }

    public Resolution create() {
        try {
            stampService.createStamp(stamp);
            getContext().getMessages().add(new LocalizableMessage("stamp.was_created"));
            return new RedirectResolution(this.getClass(), "list");
        } catch (Exception e) {
            getContext().getMessages().add(new LocalizableError("stamp.wasnt_created"));
            System.err.printf("here::::" + e.getMessage());
            return new RedirectResolution(this.getClass(), "list");
        }
    }

    public Resolution edit() {
        return new ForwardResolution("/stampEdit.jsp");

    }

    public Resolution delete() {
        try {
            stamp = stampService.findStampById(stamp.getId());
            stampService.removeStamp(stamp);
            getContext().getMessages().add(new LocalizableMessage("stamp.was_deleted"));
        } catch (Exception e) {
            getContext().getMessages().add(new LocalizableMessage("stamp.wasnt_deleted"));
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution update() {

        try {
            stampService.updateStamp(stamp);
            getContext().getMessages().add(new LocalizableMessage("stamp.was_saved"));
            return new RedirectResolution(this.getClass(), "list");
        } catch (Exception e) {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("stamp.wasnt_saved"));
            return new ForwardResolution(this.getClass(), "edit");
        }


    }

    public Resolution detail() {

        return new ForwardResolution("/stampDetail.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        stamps = stampService.getAllStamps();
        return null;
    }

    /**
     * Loads stamp from DB before edit and update call
     *
     * @return
     */
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "update", "detail"})
    public Resolution loadStampFromDatabase() {
        String ids = getContext().getRequest().getParameter("stamp.id");
        if (ids == null) {
            return new ErrorResolution(400, "ID not specified.");
        }

        stamp = stampService.findStampById(Long.parseLong(ids));

        if (stamp == null) {
            return new ErrorResolution(404, "Stamp with this ID not found.");
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
