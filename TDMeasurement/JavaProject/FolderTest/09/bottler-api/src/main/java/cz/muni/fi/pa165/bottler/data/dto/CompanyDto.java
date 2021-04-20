package cz.muni.fi.pa165.bottler.data.dto;

/**
 * DTO for Store and Producer together
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
  */
public class CompanyDto {
    
    private int ico;
    
    private String name;
    
    private String address;

    public int getIco() {
        return ico;
    }

    public void setIco(int ico) {
        this.ico = ico;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + this.ico;
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
        final CompanyDto other = (CompanyDto) obj;
        if (this.ico != other.ico) {
            return false;
        }
        return true;
    }
    
    
    
}
