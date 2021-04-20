package cz.muni.fi.pa165.bottler.data.model;

import javax.persistence.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */

@Entity
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false, unique=true)
    private String numberOfStamp;

    @Column
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime issuedDate;

    public Stamp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberOfStamp() {
        return numberOfStamp;
    }

    public void setNumberOfStamp(String numberOfStamp) {
        this.numberOfStamp = numberOfStamp;
    }

    public DateTime getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(DateTime issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stamp stamp = (Stamp) o;

        if (!id.equals(stamp.id)) return false;
        if (numberOfStamp != null ? !numberOfStamp.equals(stamp.numberOfStamp) : stamp.numberOfStamp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + numberOfStamp.hashCode();
        return result;
    }
}
