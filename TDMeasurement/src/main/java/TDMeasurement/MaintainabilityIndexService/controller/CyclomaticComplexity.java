package TDMeasurement.MaintainabilityIndexService.controller;

public class CyclomaticComplexity {
    public String getCCRate(double ccValue){
        if (ccValue> 50){
            return "Extra High";
        }
        else if(ccValue> 30 && ccValue<=50)
            return "Very High";
        else if(ccValue> 10 && ccValue<=30)
            return "High";
        else if(ccValue> 3 && ccValue<=10)
            return "Nominal";
        else if(ccValue> 0 && ccValue<=15)
            return "Low";
        else
            return "Low";
    }
}
