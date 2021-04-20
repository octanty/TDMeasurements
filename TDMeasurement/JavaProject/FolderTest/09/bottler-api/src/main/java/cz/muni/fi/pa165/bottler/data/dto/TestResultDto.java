package cz.muni.fi.pa165.bottler.data.dto;

import org.joda.time.DateTime;

/**
 * DTO for TestResult
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */
public class TestResultDto {

    private Long id;
    private BottleDto bottle;
    private DateTime time;
    private Boolean toxic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BottleDto getBottle() {
        return bottle;
    }

    public void setBottle(BottleDto bottle) {
        this.bottle = bottle;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public Boolean isToxic() {
        return toxic;
    }

    public void setToxic(Boolean toxic) {
        this.toxic = toxic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestResultDto that = (TestResultDto) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
