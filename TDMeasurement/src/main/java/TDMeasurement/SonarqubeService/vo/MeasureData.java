package TDMeasurement.SonarqubeService.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeMap;

@Data
@NoArgsConstructor
public class MeasureData {
    private final TreeMap<String, Object> metric = new TreeMap<String, Object>();;
    public Object getMetric(String metricName)
    {
        return metric.get(metricName);
    }
    public void setMetric(String metricName, String value)
    {
        metric.put(metricName, value);
    }
}

