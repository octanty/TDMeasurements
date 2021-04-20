package TDMeasurement.MeasurementService.controller;

import TDMeasurement.MeasurementService.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;


    @PostMapping("/calculate")
    public String calculate() throws IOException, URISyntaxException {
        return measurementService.calculate();
    }

    @PostMapping("/saveToRepository")
    public String saveToRepository()  throws IOException, URISyntaxException {
        return measurementService.saveToRepository();
    }

}