package TDMeasurement.SonarqubeService.controller;

import TDMeasurement.SonarqubeService.service.SonarqubeService;
import TDMeasurement.SonarqubeService.vo.MeasureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sonarqubes")
public class SonarqubeController {

    @Autowired
    private SonarqubeService sonarqubeService;

    @GetMapping("/{resourceKey}")
    @ResponseBody
    public List<MeasureData> getMeasurement(@PathVariable("resourceKey") String resourceKey) {
        return sonarqubeService.getMeasurement(resourceKey);
    }

}

