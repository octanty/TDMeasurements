package TDMeasurement.SqualeService.controller;

import TDMeasurement.MaintainabilityIndexService.entity.DirResult;
import TDMeasurement.SqualeService.entity.Squale;
import TDMeasurement.SqualeService.service.SqualeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/squale")
public class SqualeController {

    @Autowired
    SqualeService squaleService;

    @PostMapping("/calculate")
    public List<Squale> calculate() throws IOException {
        return squaleService.calculate();
    }

    @GetMapping("/listResult")
    public List<Squale> getListResult() {
        return squaleService.getListResult();
    }

}
