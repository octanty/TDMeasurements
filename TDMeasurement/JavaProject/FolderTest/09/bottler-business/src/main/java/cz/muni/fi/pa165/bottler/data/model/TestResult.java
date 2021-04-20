package cz.muni.fi.pa165.bottler.data.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Each bottle is tested for toxicity.
 * This class holds result of that test.
 * @author Josef Ludvicek <374268@mail.muni.cz>
 */

@Entity
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Bottle bottle;

    @Column(nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime time;

    @Column(nullable = false)
    private Boolean toxic;

    public TestResult() {
    }

    /**
     * Test results are considered as equals based on same id
     * @param o
     * @return true if given object is instance of TestResult with same id
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestResult)) return false;

        TestResult that = (TestResult) o;

        return !(this.id != that.id && (this.id == null || !this.id.equals(that.id)));

    }

    /**
     * HashCode for TestResult based only on id
     * @return magic constant + (this.id.hashcode or 0)
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "id=" + id +
                ", bottle=" + bottle +
                ", time=" + time +
                ", toxic=" + toxic +
                '}';
    }


    //-------------getters setters-------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * FK to bottle
     * @return
     */
    public Bottle getBottle() {
        return bottle;
    }

    public void setBottle(Bottle bottle) {
        this.bottle = bottle;
    }

    /**
     * Time when test was done
     * @return datetime from Joda time package
     */
    public DateTime getTime() {
        return time;
    }

    /**
     * Set time when test was done
     * @param time
     */
    public void setTime(DateTime time) {
        this.time = time;
    }

    /**
     * Not good idea to drink toxic liquor :)
     * @return true if toxic bottle
     */
    public Boolean isToxic() {
        return toxic;
    }

    /**
     * Set toxicity of test result for bottle
     * @param toxic true if toxic bottle
     */
    public void setToxic(Boolean toxic) {
        this.toxic = toxic;
    }


}
