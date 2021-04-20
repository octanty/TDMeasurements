package TDMeasurement.SonarqubeService.service;

import TDMeasurement.SonarqubeService.vo.MeasureData;
import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.services.Measure;
import org.sonar.wsclient.services.ResourceQuery;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class SonarqubeService {
    //static String resourceKey = "cz.muni.fi.pa165.fleetmanagement:PA165-FleetManagement";
    static String[] MEASURES_TO_GET = new String[]{"ncloc", "complexity", "cognitive_complexity", "lcom4", "duplicated_blocks", "duplicated_lines", "code_smells", "sqale_debt_ratio"};
    public static Sonar localSonar;


    public List<MeasureData> getMeasurement(String resourceKey) {

        //  To Create Connection Using Resource Key which is Project name , user Name and Password
        List<MeasureData> list = new ArrayList<MeasureData>();
        //Executing the Get request
        localSonar  =  Sonar.create("http://localhost:9000");

        ResourceQuery query = ResourceQuery.createForMetrics(resourceKey, MEASURES_TO_GET);
        query.setIncludeTrends(true);
        Object resource = localSonar.find(query);
        List<Measure> allMeasures = ((org.sonar.wsclient.services.Resource) resource).getMeasures();
        MeasureData measureData = new MeasureData();
        for (Measure measure : allMeasures) {
               /* System.out.println("Statements : " + measure.getMetricKey()
                        + " === " + measure.getFormattedValue()); */
            measureData.setMetric(measure.getMetricKey(), measure.getFormattedValue());
        }
        list.add(measureData);
        //System.out.println(list);
        //ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //String arrayToJson = objectMapper.writeValueAsString(list);
        //System.out.println("Convert List to JSON :");
        //System.out.println(arrayToJson);
        //return arrayToJson;
        return list;
    }


}
