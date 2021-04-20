package cz.muni.fi.pa165.bottler.data.dto;

import org.joda.time.DateTime;

/**
 * DTO for Stamp
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */
public class StampDto {

    private Long id;
    private String numberOfStamp;
    private DateTime issuedDate;

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

        StampDto stampDto = (StampDto) o;

        if (!id.equals(stampDto.id)) return false;
        if (!numberOfStamp.equals(stampDto.numberOfStamp)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + numberOfStamp.hashCode();
        return result;
    }
}
