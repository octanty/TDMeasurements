package com.muni.fi.pa165.entities;

import com.muni.fi.pa165.enums.TerrainType;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Auron
 */
@Entity
@Table(name = "AREA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a"),
    @NamedQuery(name = "Area.findById", query = "SELECT a FROM Area a WHERE a.id = :id"),
    @NamedQuery(name = "Area.findByDescription", query = "SELECT a FROM Area a WHERE a.description = :description"),
    @NamedQuery(name = "Area.findByName", query = "SELECT a FROM Area a WHERE a.name = :name"),
    @NamedQuery(name = "Area.findByTerrain", query = "SELECT a FROM Area a WHERE a.terrain = :terrain")})
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TERRAIN")
    @Enumerated(EnumType.STRING)
    private TerrainType terrain;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private Collection<MonsterArea> monsterareaCollection;

    public Area() {
    }

    public Area(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TerrainType getTerrain() {
        return terrain;
    }

    public void setTerrain(TerrainType terrain) {
        this.terrain = terrain;
    }

    @XmlTransient
    public Collection<MonsterArea> getMonsterareaCollection() {
        return monsterareaCollection;
    }

    public void setMonsterareaCollection(Collection<MonsterArea> monsterareaCollection) {
        this.monsterareaCollection = monsterareaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.muni.fi.pa165.entities.Area[ id=" + id + " ]";
    }
}
