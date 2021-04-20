package com.muni.fi.pa165.actions.monster;

import com.muni.fi.pa165.actions.base.BaseActionBean;
import static com.muni.fi.pa165.actions.base.BaseActionBean.escapeHTML;
import com.muni.fi.pa165.dto.MonsterDto;
import com.muni.fi.pa165.service.MonsterService;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Stripes ActionBean for handling monster operations.
 *
 * @author Aubrey Oosthuizen
 */
@UrlBinding("/monster/{$event}/{monster.id}")
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_REST'")
public class MonsterActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(MonsterActionBean.class);
    /**
     *
     */
    @SpringBean
    protected MonsterService monsterService;
    private List<MonsterDto> monsters;

    /**
     *
     * @return
     */
    @DefaultHandler
       public Resolution list() {
        log.debug("list()");
        monsters = monsterService.findAll();
        return new ForwardResolution("/monster/list.jsp");
    }

    /**
     *
     * @return
     */
    public List<MonsterDto> getMonsters() {
        return monsters;
    }
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true, maxlength = 255),
        @Validate(on = {"add", "save"}, field = "stamina", required = false, minvalue = 0, maxvalue = 100),
        @Validate(on = {"add", "save"}, field = "height", required = false, minvalue = 0, maxvalue = 1000),
        @Validate(on = {"add", "save"}, field = "strength", required = false, minvalue = 0, maxvalue = 100),
        @Validate(on = {"add", "save"}, field = "agility", required = false, minvalue = 0, maxvalue = 100),
        @Validate(on = {"add", "save"}, field = "dangerLevel", required = true, minvalue = 0, maxvalue = 100),
        @Validate(on = {"add", "save"}, field = "monsterclass", required = false),
        @Validate(on = {"add", "save"}, field = "weight", required = false, minvalue = 0, maxvalue = 10000),
        @Validate(on = {"add", "save"}, field = "description", required = false, maxlength = 255),
        @Validate(on = {"add", "save"}, field = "imagePath", required = false, maxlength = 255)
    })
    private MonsterDto monster;

    /**
     *
     * @return
     */
    public Resolution add() {
        log.debug("add() monster={}", monster);
        try {
            if (monster.getImagePath() == null || monster.getImagePath().isEmpty()) {
                monster.setImagePath("http://static.zerochan.net/SlenderMan.full.1338548.jpg");
            }
            monster = monsterService.save(monster);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        getContext().getMessages().add(new LocalizableMessage("add.message", escapeHTML(monster.getName()), escapeHTML(monster.getDescription())));
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     *
     * @param errors
     * @return
     * @throws Exception
     */
    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        monsters = monsterService.findAll();
        return null;
    }

    /**
     *
     * @return
     */
    public MonsterDto getMonster() {
        return monster;
    }

    /**
     *
     * @param monster
     */
    public void setMonster(MonsterDto monster) {
        this.monster = monster;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public Resolution delete() throws Exception {
        log.debug("delete({})", monster.getId());
        monsterService.delete(monster.getId());
        return new RedirectResolution(this.getClass(), "all");
    }

    /**
     *
     */
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadMonsterFromDatabase() {
        String ids = getContext().getRequest().getParameter("monster.id");
        if (ids == null) {
            return;
        }
        monster = monsterService.findById(Long.parseLong(ids));
        log.debug("Loaded monster from DB");
    }

    /**
     *
     * @return
     */
    public Resolution edit() {
        log.debug("edit() monster={}", monster);
        return new ForwardResolution("/monster/edit.jsp");
    }

    /**
     *
     * @return
     */
    public Resolution gallery() {
        log.debug("edit() monster={}", monster);
        monsters = monsterService.findAll();
        return new ForwardResolution("/monster/gallery.jsp");
    }

    /**
     *
     * @return
     */
    public Resolution save() {
        log.debug("save() monster={}", monster);
        monster = getMonster();
        monsterService.update(monster);
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     *
     * @return
     */
    public Resolution cancel() {
        log.debug("cancel");
        return new RedirectResolution(this.getClass(), "list");
    }
}
