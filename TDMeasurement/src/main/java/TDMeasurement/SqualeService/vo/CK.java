package TDMeasurement.SqualeService.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CK {
    private int dit;
    private int lcom;
    private int ca;
    private int rfc;
    private int ce;

    public void setDit(int dit) {
        this.dit = dit;
    }

    public int getDit() {
        return dit;
    }

    public void setLcom(int lcom) {
        this.lcom = lcom;
    }

    public int getLcom() {
        return lcom;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }

    public int getCa() {
        return ca;
    }

    public void setRfc(int rfc) {
        this.rfc = rfc;
    }

    public int getRfc() {
        return rfc;
    }

    public void setCe(int ce) {
        this.ce = ce;
    }

    public int getCe() {
        return ce;
    }
}
