package cz.muni.fi.pa165.bottler.data.dto;

/**
 * DTO for Liquor
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */
public class LiquorDto {

    private Long id;
    private String name;
    private double alcoholPercentage;
    private double volume;
    private ProducerDto producer;
    private String ean;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ProducerDto getProducer() {
        return producer;
    }

    public void setProducer(ProducerDto producer) {
        this.producer = producer;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LiquorDto liquorDto = (LiquorDto) o;

        if (!id.equals(liquorDto.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
