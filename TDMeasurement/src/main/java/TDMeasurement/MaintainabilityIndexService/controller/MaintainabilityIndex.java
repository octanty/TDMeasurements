package TDMeasurement.MaintainabilityIndexService.controller;

import java.text.DecimalFormat;

// refactored from https://github.com/rasatmaja/MaintainabilityIndex
public class MaintainabilityIndex {
    public Double getMaintainabilityIndex(double halsteadMetricsVolume, double cyclomaticComplexityValue, int lineOfCOde, int comment) {
        double maintainabilityIndex = (lineOfCOde != 0 && halsteadMetricsVolume != 0) ?
                171 - 5.2 * Math.log(halsteadMetricsVolume) - 0.23 * cyclomaticComplexityValue - 16.2 * Math.log(lineOfCOde) +  (50 * Math.sin(Math.sqrt(2.46 * comment)))
                :0 ;
        double maintainabilityIndexValue = Math.max(0,maintainabilityIndex);
        return  Double.parseDouble(new DecimalFormat("0.##").format(maintainabilityIndexValue));
    }

    public Double getAvgMaintainabilityIndex(double avgHalsteadMetricsVolume, double avgCyclomaticComplexityValue, double avgLineOfCOde, double avgComment) {

        double maintainabilityIndex = (avgLineOfCOde != 0 && avgHalsteadMetricsVolume != 0) ?
                171 - 5.2 * Math.log(avgHalsteadMetricsVolume) - 0.23 * avgCyclomaticComplexityValue - 16.2 * Math.log(avgLineOfCOde) +  (50 * Math.sin(Math.sqrt(2.46 * avgComment)))
                :0;
        double maintainabilityIndexValue = Math.max(0,maintainabilityIndex);
        return  Double.parseDouble(new DecimalFormat("0.##").format(maintainabilityIndexValue));
    }

    public String getMaintainabilityRate(double miValue){
        if (miValue>= 85){
            return "High";
        }
        else if(miValue>= 65 && miValue<=85)
            return "Moderate";
        else if(miValue<65)
            return "Low";
        else
            return "Low";
    }
}
