/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bottler.data.dto;

import java.util.Objects;

/**
 * DTO for result of statistics
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
  */

public class StatisticsDto {
    
    private long toxicBottlesCount = 0;
    
    private long allBottlesCount = 0;
    
    private double toxicPercentage = 0;
    
    private CompanyDto company;

    public double getToxicPercentage() {
        return toxicPercentage;
    }

    public void setToxicPercentage(double toxicPercentage) {
        this.toxicPercentage = toxicPercentage;
    }
    
    public long getAllBottlesCount() {
        return allBottlesCount;
    }

    public void setAllBottlesCount(long allBottlesCount) {
        this.allBottlesCount = allBottlesCount;
    }

    
    public long getToxicBottlesCount() {
        return toxicBottlesCount;
    }

    public void setToxicBottlesCount(long toxicBottlesCount) {
        this.toxicBottlesCount = toxicBottlesCount;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.company);
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
        final StatisticsDto other = (StatisticsDto) obj;
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        return true;
    }

      
}
