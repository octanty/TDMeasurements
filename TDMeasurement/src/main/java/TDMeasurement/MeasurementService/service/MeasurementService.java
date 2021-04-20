package TDMeasurement.MeasurementService.service;

import TDMeasurement.MaintainabilityIndexService.vo.Directory;
import java.io.*;
import java.net.URISyntaxException;
import java.util.List;

import TDMeasurement.MeasurementService.vo.DirResult;
import TDMeasurement.MeasurementService.vo.Squale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MeasurementService {
    //private static Gson gson;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder.build();
    }

    public String saveToRepository(){
        restTemplate.exchange("http://localhost:8004/directories/saveToRepository",
                        HttpMethod.POST, null, new ParameterizedTypeReference<List<Directory>>() {
                        });
        return "Successfully saved";
    }


    public String calculate() throws IOException, URISyntaxException {
        saveToRepository();
        calculateMI();
        calculateSquale();
        return "Successfully Calculated";
    }

    public List<DirResult> calculateMI() throws  URISyntaxException, IOException {
        ResponseEntity<List<DirResult>> responseEntity = restTemplate.exchange("http://localhost:8004/mi/calculate",
                HttpMethod.POST, null, new ParameterizedTypeReference<List<DirResult>>() {
                });
        List<DirResult> result = responseEntity.getBody();
        return result;
    }

    public List<Squale> calculateSquale() throws  URISyntaxException, IOException {
        ResponseEntity<List<Squale>> responseEntity = restTemplate.exchange("http://localhost:8004/squale/calculate",
                HttpMethod.POST, null, new ParameterizedTypeReference<List<Squale>>() {
                });
        List<Squale> result = responseEntity.getBody();
        return result;
    }


 /*  public static void main(String[] args) throws IOException, URISyntaxException{
        RestTemplate restTemplate = new RestTemplate();
        gson = new GsonBuilder().setPrettyPrinting().create();
        String access_token = "access_token=dcb950a2efd8eaa09132a28b8aa08f0f7640403a";
        Map jsonMap = restTemplate.getForObject("https://api.github.com/repos/octanty/JavaProjectForThesis/branches/main?"+access_token, Map.class);

        String treeApiUrl = gson.toJsonTree(jsonMap).getAsJsonObject().get("commit").getAsJsonObject().get("commit")
                .getAsJsonObject().get("tree").getAsJsonObject().get("url").getAsString();
        System.out.println("TREE API URL = " + treeApiUrl + "\n");

        Map jsonTreeMap = restTemplate.getForObject(treeApiUrl + "?recursive=1&" +access_token, Map.class);

        for (Object obj : ((List) jsonTreeMap.get("tree"))) {

            Map fileMetadata = (Map) obj;

            // Type tree will be directory & blob will be file. Print files & directory
            // list with file sizes.
            if (fileMetadata.get("type").equals("tree")) {
                    System.out.println("Directory = " + fileMetadata.get("path"));

            } /* else {
                System.out.println(
                        "File = " + fileMetadata.get("path") + " | Size = " + fileMetadata.get("size") + " Bytes");
            }*/
    /*    }
    } */
}


