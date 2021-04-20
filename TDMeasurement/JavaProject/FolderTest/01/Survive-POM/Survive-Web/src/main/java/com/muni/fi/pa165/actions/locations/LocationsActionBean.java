package com.muni.fi.pa165.actions.locations;

import com.muni.fi.pa165.actions.base.BaseActionBean;
import com.muni.fi.pa165.dto.AreaDto;
import com.muni.fi.pa165.dto.MonsterAreaDto;
import com.muni.fi.pa165.dto.MonsterDto;
import com.muni.fi.pa165.service.AreaService;
import com.muni.fi.pa165.service.MonsterAreaService;
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

/**
 * Stripes ActionBean for handling monsterMonsterArea operations.
 *
 * @author Aubrey Oosthuizen
 */
@UrlBinding("/locations/{$event}/{monsterArea.area.id}")
public class LocationsActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(LocationsActionBean.class);
    /**
     *
     */
    @SpringBean 
    protected MonsterAreaService service;
    /**
     *
     */
    @SpringBean
    protected AreaService areaService;
    /**
     *
     */
    @SpringBean
    protected MonsterService monsterService;
    private List<MonsterAreaDto> locations;
    private List<AreaDto> areas;
    private List<MonsterDto> monsters;

   
    /**
     *
     * @return
     */
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        locations = service.findAll();


        return new ForwardResolution("/locations/list.jsp");
    }

    /**
     *
     * @return
     */
    public List<MonsterAreaDto> getLocations() {
        return locations;
    }

    /**
     *
     * @param locations
     */
    public void setLocations(List<MonsterAreaDto> locations) {
        this.locations = locations;
    }

    /**
     *
     * @return
     */
    public List<AreaDto> getAreas() {
        areas = areaService.findAll();
        return areas;
    }

    /**
     *
     * @param areas
     */
    public void setAreas(List<AreaDto> areas) {
        this.areas = areas;
    }

    /**
     *
     * @return
     */
    public List<MonsterDto> getMonsters() {
        monsters = monsterService.findAll();
        return monsters;
    }

    /**
     *
     * @param monsters
     */
    public void setMonsters(List<MonsterDto> monsters) {
        this.monsters = monsters;
    }

    /**
     *
     * @return
     */
    public List<MonsterAreaDto> getMonsterAreas() {
        return locations;
    }
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "monster.id", required = true),
        @Validate(on = {"add", "save"}, field = "area.id", required = true),
        @Validate(on = {"add", "save"}, field = "monsterQuantity", required = true, minvalue = 1)})
    private MonsterAreaDto monsterArea;

    /**
     *
     * @return
     */
    public Resolution add() {
        log.debug("add() monsterMonsterArea={}", monsterArea);
        try {
            MonsterDto monster = monsterService.findById(Long.parseLong(getContext().getRequest().getParameter("monsterArea.monster.id")));
            AreaDto area = areaService.findById(Long.parseLong(getContext().getRequest().getParameter("monsterArea.area.id")));
            monsterArea.setMonster(monster);
            monsterArea.setArea(area);
            monsterArea = service.save(monsterArea);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            // getContext().getMessages().add(new LocalizableMessage("add.message", escapeHTML(monsterMonsterArea.getName()), escapeHTML(monsterMonsterArea.getDescription().toString())));

        }
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
        //fill up the data for the table if validation errors occured
        locations = service.findAll();
        //return null to let the event handling continue
        return null;
    }

    /**
     *
     * @return
     */
    public MonsterAreaDto getMonsterArea() {
        return monsterArea;
    }

    /**
     *
     * @param monsterMonsterArea
     */
    public void setMonsterArea(MonsterAreaDto monsterMonsterArea) {
        this.monsterArea = monsterMonsterArea;
    }

    //--- part for deleting a monsterMonsterArea ----
    /**
     *
     * @return
     */
    public Resolution delete() {
        log.debug("delete({})", monsterArea.getMonster());
        //only id is filled by the form
        try {
            Long monsterId = Long.parseLong(getContext().getRequest().getParameter("monsterArea.monster.id"));
            Long areaId = Long.parseLong(getContext().getRequest().getParameter("monsterArea.area.id"));

            service.delete(service.getCompositeKey(monsterId, areaId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    //--- part for editing a monsterMonsterArea ----
    /**
     *
     */
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadMonsterAreaFromDatabase() {
        String monsterId = getContext().getRequest().getParameter("monsterArea.monster.id");
        String areaId = getContext().getRequest().getParameter("monsterArea.area.id");
        if (monsterId == null || areaId == null) {
            return;
        }

        try {
            monsterArea = service.findById(Long.parseLong(monsterId), Long.parseLong(areaId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        log.debug("Loaded monsterMonsterArea from DB");
    }

    /**
     *
     * @return
     */
    public Resolution findByMonster() {
        String monsterId = getContext().getRequest().getParameter("filter.monster.id");
        if (monsterId == null) {
            return new RedirectResolution(this.getClass(), "list");
        }
        locations = service.findByMonsterId(Long.parseLong(monsterId));
        return new ForwardResolution("/locations/list.jsp");
    }

    /**
     *
     * @return
     */
    public Resolution findByArea() {
        String areaId = getContext().getRequest().getParameter("filter.area.id");
        if (areaId == null) {
            return new RedirectResolution(this.getClass(), "list");
        }
        locations = service.findByAreaId(Long.parseLong(areaId));
        return new ForwardResolution("/locations/list.jsp");
    }

    /**
     *
     * @return
     */
    public Resolution clearFilters() {
        log.debug("edit() monsterMonsterArea={}", monsterArea);
        locations = service.findAll();
        return new ForwardResolution("/locations/edit.jsp");
    }

    /**
     *
     * @return
     */
    public Resolution edit() {
        log.debug("edit() monsterMonsterArea={}", monsterArea);

        return new ForwardResolution("/locations/edit.jsp");
    }

    /**
     *
     * @return
     */
    public Resolution save() {
        log.debug("Called method save");
        try {

            monsterArea = service.update(monsterArea);


        } catch (Exception ex) {
            log.error(ex.getMessage());

        }
        log.debug("save() monsterMonsterArea={}", monsterArea);

        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     *
     * @return
     */
    public Resolution cancel() {
        log.debug("Called method cancel");
        log.debug("cancel");
        return new RedirectResolution(this.getClass(), "list");
    }
}
