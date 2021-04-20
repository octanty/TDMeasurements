package com.muni.fi.pa165.dto;

import com.muni.fi.pa165.enums.MonsterClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data transfer object for the {@link Monster}.
 *
 *
 * @author Michal Vinkler
 */
@XmlRootElement
public class MonsterDto {

    private Long id;
    private MonsterClass monsterClass;
    private String name;
    private String description;
    private Double height;
    private Double weight;
    private Double agility;
    private Double strength;
    private Double stamina;
    private Double dangerLevel;
    private String imagePath;
    private List<MonsterWeaponDto> efficiencies = new ArrayList<>();
    private List<MonsterAreaDto> locations = new ArrayList<>();

    //Getters and setters
    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param monsterId
     */
    public void setId(Long monsterId) {
        this.id = monsterId;
    }

    /**
     *
     * @return
     */
    public MonsterClass getMonsterClass() {
        return monsterClass;
    }

    /**
     *
     * @param monsterClass
     */
    public void setMonsterClass(MonsterClass monsterClass) {
        this.monsterClass = monsterClass;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public Double getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     *
     * @return
     */
    public Double getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     *
     * @return
     */
    public Double getAgility() {
        return agility;
    }

    /**
     *
     * @param agility
     */
    public void setAgility(Double agility) {
        this.agility = agility;
    }

    /**
     *
     * @return
     */
    public Double getStrength() {
        return strength;
    }

    /**
     *
     * @param strength
     */
    public void setStrength(Double strength) {
        this.strength = strength;
    }

    /**
     *
     * @return
     */
    public Double getStamina() {
        return stamina;
    }

    /**
     *
     * @param stamina
     */
    public void setStamina(Double stamina) {
        this.stamina = stamina;
    }

    /**
     *
     * @return
     */
    public Double getDangerLevel() {
        return dangerLevel;
    }

    /**
     *
     * @param dangerLevel
     */
    public void setDangerLevel(Double dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    /**
     *
     * @return
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     *
     * @param imagePath
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.monsterClass);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.height);
        hash = 97 * hash + Objects.hashCode(this.weight);
        hash = 97 * hash + Objects.hashCode(this.agility);
        hash = 97 * hash + Objects.hashCode(this.strength);
        hash = 97 * hash + Objects.hashCode(this.stamina);
        hash = 97 * hash + Objects.hashCode(this.dangerLevel);
        hash = 97 * hash + Objects.hashCode(this.imagePath);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MonsterDto other = (MonsterDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.monsterClass != other.monsterClass) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.height, other.height)) {
            return false;
        }
        if (!Objects.equals(this.weight, other.weight)) {
            return false;
        }
        if (!Objects.equals(this.agility, other.agility)) {
            return false;
        }
        if (!Objects.equals(this.strength, other.strength)) {
            return false;
        }
        if (!Objects.equals(this.stamina, other.stamina)) {
            return false;
        }
        if (!Objects.equals(this.dangerLevel, other.dangerLevel)) {
            return false;
        }
        if (!Objects.equals(this.imagePath, other.imagePath)) {
            return false;
        }
        return true;
    }
}
