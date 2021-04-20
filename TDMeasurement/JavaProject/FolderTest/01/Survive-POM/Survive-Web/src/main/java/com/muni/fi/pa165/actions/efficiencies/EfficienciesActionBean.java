package com.muni.fi.pa165.actions.efficiencies;

import com.muni.fi.pa165.actions.base.BaseActionBean;
import com.muni.fi.pa165.dto.MonsterDto;
import com.muni.fi.pa165.dto.MonsterWeaponDto;
import com.muni.fi.pa165.dto.WeaponDto;
import com.muni.fi.pa165.service.MonsterService;
import com.muni.fi.pa165.service.MonsterWeaponService;
import com.muni.fi.pa165.service.WeaponService;
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
 * Stripes ActionBean for handling monsterMonsterWeapon operations.
 *
 * @author Aubrey Oosthuizen
 */
@UrlBinding("/efficiencies/{$event}/{monsterWeapon.weapon.id}")
public class EfficienciesActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(EfficienciesActionBean.class);
    /**
     *
     */
    @SpringBean
    protected MonsterWeaponService service;
    /**
     *
     */
    @SpringBean
    protected WeaponService weaponService;
    /**
     *
     */
    @SpringBean
    protected MonsterService monsterService;
    private List<MonsterWeaponDto> efficiencies;
    private List<WeaponDto> weapons;
    private List<MonsterDto> monsters;

    /**
     *
     * @return
     */
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        efficiencies = service.findAll();
        return new ForwardResolution("/efficiencies/list.jsp");
    }

    /**
     *
     * @return
     */
    public List<MonsterWeaponDto> getEfficiencies() {
        return efficiencies;
    }

    /**
     *
     * @param efficiencies
     */
    public void setEfficiencies(List<MonsterWeaponDto> efficiencies) {
        this.efficiencies = efficiencies;
    }

    /**
     *
     * @return
     */
    public List<WeaponDto> getWeapons() {
        weapons = weaponService.findAll();
        return weapons;
    }

    /**
     *
     * @param weapons
     */
    public void setWeapons(List<WeaponDto> weapons) {
        this.weapons = weapons;
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
    public List<MonsterWeaponDto> getMonsterWeapons() {
        return efficiencies;
    }
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "monster.id", required = true),
        @Validate(on = {"add", "save"}, field = "weapon.id", required = true),
        @Validate(on = {"add", "save"}, field = "hitRate", required = false, minvalue = 0, maxvalue = 1000),
        @Validate(on = {"add", "save"}, field = "damage", required = false, minvalue = 0, maxvalue = 100),
        @Validate(on = {"add", "save"}, field = "efficiency", required = true, minvalue = 0, maxvalue = 100),
        @Validate(on = {"add", "save"}, field = "description", required = false, maxlength = 255)})
    private MonsterWeaponDto monsterWeapon;

    /**
     *
     * @return
     */
    public Resolution add() {
        log.debug("add() monsterMonsterWeapon={}", monsterWeapon);

        try {
            MonsterDto monster = monsterService.findById(Long.parseLong(getContext().getRequest().getParameter("monsterWeapon.monster.id")));
            WeaponDto weapon = weaponService.findById(Long.parseLong(getContext().getRequest().getParameter("monsterWeapon.weapon.id")));
            monsterWeapon.setMonster(monster);
            monsterWeapon.setWeapon(weapon);
            monsterWeapon = service.save(monsterWeapon);
        } catch (Exception ex) {
            log.error(ex.getMessage());
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
        efficiencies = service.findAll();
        return null;
    }

    /**
     *
     * @return
     */
    public MonsterWeaponDto getMonsterWeapon() {
        return monsterWeapon;
    }

    /**
     *
     * @param monsterMonsterWeapon
     */
    public void setMonsterWeapon(MonsterWeaponDto monsterMonsterWeapon) {
        this.monsterWeapon = monsterMonsterWeapon;
    }

    /**
     *
     * @return
     */
    public Resolution delete() {

        log.debug("delete({})", monsterWeapon.getMonster());
        try {
            Long monsterId = Long.parseLong(getContext().getRequest().getParameter("monsterWeapon.monster.id"));
            Long weaponId = Long.parseLong(getContext().getRequest().getParameter("monsterWeapon.weapon.id"));
            service.delete(service.getCompositeKey(monsterId, weaponId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     *
     */
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadMonsterWeaponFromDatabase() {
        String monsterId = getContext().getRequest().getParameter("monsterWeapon.monster.id");
        String weaponId = getContext().getRequest().getParameter("monsterWeapon.weapon.id");
        if (monsterId == null || weaponId == null) {
            return;
        }
        try {
            monsterWeapon = service.findById(Long.parseLong(monsterId), Long.parseLong(weaponId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        log.debug("Loaded monsterMonsterWeapon from DB");
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
        efficiencies = service.findByMonsterId(Long.parseLong(monsterId));
        return new ForwardResolution("/efficiencies/list.jsp");
    }

    /**
     *
     * @return
     */
    public Resolution findByWeapon() {
        String weaponId = getContext().getRequest().getParameter("filter.weapon.id");
        if (weaponId == null) {
            return new RedirectResolution(this.getClass(), "list");
        }
        efficiencies = service.findByWeaponId(Long.parseLong(weaponId));
        return new ForwardResolution("/efficiencies/list.jsp");
    }

    /**
     *
     * @return
     */
    public Resolution clearFilters() {
        log.debug("edit() monsterMonsterWeapon={}", monsterWeapon);
        efficiencies = service.findAll();
        return new ForwardResolution("/efficiencies/edit.jsp");
    }

    /**
     *
     * @return
     */
    public Resolution edit() {
        log.debug("edit() monsterMonsterWeapon={}", monsterWeapon);
        return new ForwardResolution("/efficiencies/edit.jsp");
    }

    /**
     *
     * @return
     */
    public Resolution save() {
        log.debug("Called method save");
        try {
            monsterWeapon = service.update(monsterWeapon);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        log.debug("save() monsterMonsterWeapon={}", monsterWeapon);
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     *
     * @return
     */
    public Resolution cancel() {
        log.debug("Called method cancel");
        return new RedirectResolution(this.getClass(), "list");
    }
}
