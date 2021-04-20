package cz.muni.fi.pa165.web;

import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.services.BookService;
import cz.muni.fi.pa165.library.services.ImpressionService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 *
 * @author Mjartan
 */
@UrlBinding("/impressions/{$event}/{$impression.id}")
public class ImpressionEditBean extends BaseBean implements ValidationErrorHandler {

    @SpringBean
    private ImpressionService imService;
    @SpringBean
    private BookService bookService;
    @ValidateNestedProperties(value = {
        @Validate(on = {"create", "save"}, field = "isbn", required = true),
        @Validate(on = {"create", "save"}, field = "author", required = true),
        @Validate(on = {"create", "save"}, field = "name", required = true),
        @Validate(on = {"create", "save"}, field = "releaseDate", required = true, converter = DateConverter.class),})
    private ImpressionTO impression;
    private List<ImpressionTO> allImpressions;
    private List<BookTO> impressionBooks;

    @DefaultHandler
    public Resolution printImpressions() {
        allImpressions = imService.findAllImpressions();
        return new ForwardResolution("/impression/main.jsp");
    }

    public Resolution listBooks() {
        allImpressions = imService.findAllImpressions();
        ImpressionTO tempImpression =
                imService.findImpressionById(Long.parseLong(getContext().getRequest().getParameter("impression.id")));
        impressionBooks = bookService.findBooksForImpression(tempImpression);
        return new ForwardResolution("/impression/main.jsp");
    }

    public Resolution create() {
        imService.createImpression(impression);
        return new RedirectResolution(getClass(), "printImpressions");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "delete"})
    public void loadImpression() {
        impression = imService.findImpressionById(Long.parseLong(getContext().getRequest().getParameter("impression.id")));
    }

    public Resolution edit() {
        return new ForwardResolution("/impression/edit.jsp");
    }

    public Resolution save() {
        imService.updateImpression(impression);
        return new RedirectResolution(getClass(), "printImpressions");
    }

    public Resolution delete() {
        imService.deleteImpression(impression);
        return new ForwardResolution(getClass(), "printImpressions");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) {
        allImpressions = imService.findAllImpressions();
        return null;
    }

    public ImpressionTO getImpression() {
        return impression;
    }

    public void setImpression(ImpressionTO impression) {
        this.impression = impression;
    }

    public List<ImpressionTO> getAllImpressions() {
        return allImpressions;
    }

    public void setAllImpressions(List<ImpressionTO> allImpressions) {
        this.allImpressions = allImpressions;
    }

    public List<BookTO> getImpressionBooks() {
        return impressionBooks;
    }

    public void setImpressionBooks(List<BookTO> impressionBooks) {
        this.impressionBooks = impressionBooks;
    }
}
