package cz.muni.fi.pa165.bottler.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * 
 * Bottle is a physical representation of a drink.
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
 */

@Entity
public class Bottle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Liquor liquor;
    
    @ManyToOne
    private Stamp stamp;
    
    @Column(nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime producedDate;
    
    @Column(nullable = false)
    private String lotCode;
    
    @ManyToOne
    private Store store;
    
    private boolean sold;
    
    
    public Bottle()
    {
        this.sold = false;        
    }

    public Long getId() {
        return id;
    }

    public Liquor getLiquor() {
        return liquor;
    }

    public void setLiquor(Liquor liquor) {
        this.liquor = liquor;
    }

    public Stamp getStamp() {
        return stamp;
    }

    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
    }

    public DateTime getProducedDate() {
        return producedDate;
    }

    public void setProducedDate(DateTime producedDate) {
        this.producedDate = producedDate;
    }

    public String getLotCode() {
        return lotCode;
    }

    public void setLotCode(String lotCode) {
        this.lotCode = lotCode;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Bottle other = (Bottle) obj;
        return !(this.id != other.id && (this.id == null || !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Bottle{" + "id=" + id + ", liquor=" + liquor + ", stamp=" + stamp + ", producedDate=" + producedDate + ", lotCode=" + lotCode + ", store=" + (store == null ? "" : store) + ", sold=" + sold + '}';
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
    
    
    
    
}
