package cz.muni.fi.pa165.bottler.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Liquor is a definition of a drink.
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
 */

@Entity
public class Liquor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private double alcoholPercentage;
    
    @Column(nullable = false)
    private double volume;
    
    @ManyToOne
   private Producer producer;
    
    @Column(nullable = false, columnDefinition = "varchar(20)")
    private String ean;

    
    public Liquor() {
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Liquor other = (Liquor) obj;
        return !(this.id != other.id && (this.id == null || !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Liquor{" + "id=" + id + ", name=" + name + ", alcoholPercentage=" + alcoholPercentage + ", volume=" + volume + ", producer=" + producer + ", ean=" + ean + '}';
    }
    
    
    
    
    
    
}
