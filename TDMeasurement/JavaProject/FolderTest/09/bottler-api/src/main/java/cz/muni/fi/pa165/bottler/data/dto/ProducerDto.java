package cz.muni.fi.pa165.bottler.data.dto;

/**
 * DTO for Producer
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */
public class ProducerDto {

    private Long id;
    private String name;
    private String address;
    private Integer ico;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIco() {
        return ico;
    }

    public void setIco(Integer ico) {
        this.ico = ico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProducerDto that = (ProducerDto) o;

        if (!ico.equals(that.ico)) return false;
        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + ico.hashCode();
        return result;
    }
}
