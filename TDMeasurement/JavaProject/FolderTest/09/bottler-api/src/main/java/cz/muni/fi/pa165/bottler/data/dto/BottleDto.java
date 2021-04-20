package cz.muni.fi.pa165.bottler.data.dto;

import org.joda.time.DateTime;

/**
 * DTO for Bottle
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */
public class BottleDto {

    private Long id;
    private LiquorDto liquor;
    private StampDto stamp;
    private DateTime producedDate;
    private String lotCode;
    private StoreDto store;
    private boolean sold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LiquorDto getLiquor() {
        return liquor;
    }

    public void setLiquor(LiquorDto liquor) {
        this.liquor = liquor;
    }

    public StampDto getStamp() {
        return stamp;
    }

    public void setStamp(StampDto stamp) {
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

    public StoreDto getStore() {
        return store;
    }

    public void setStore(StoreDto store) {
        this.store = store;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BottleDto bottleDto = (BottleDto) o;

        if (!id.equals(bottleDto.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 33 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
