package TDMeasurement.MaintainabilityIndexService.controller;

import TDMeasurement.MaintainabilityIndexService.entity.DirResult;
import TDMeasurement.MaintainabilityIndexService.entity.FileResult;
import TDMeasurement.MaintainabilityIndexService.service.MIService;
import TDMeasurement.MaintainabilityIndexService.vo.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mi")
public class MIController {

    @Autowired
    private MIService miService;

 /*  @PostMapping("/calculate")
    public List<DirResult> calculate(@RequestBody List<Directory> listDirectory) throws IOException {
        return miService.calculate(listDirectory);
    }*/

    @PostMapping("/calculate")
    public List<DirResult> calculate() throws IOException {
        return miService.calculate();
    }

    @GetMapping("/fileResult/{id}")
    public FileResult findByFileResultId(@PathVariable("id") long fileResultID) {
        return miService.findByFileResultID(fileResultID);
    }

    @GetMapping("/dirResult/{id}")
    public DirResult findByDirResultId(@PathVariable("id") long dirResultID) {
        return miService.findByDirResultID(dirResultID);
    }

    @GetMapping("/dirResult/findDirResultByDirectoryID/{directoryID}")
    public DirResult findDirResultByDirectoryID(@PathVariable("directoryID") long directoryID) {
        return miService.findDirResultByDirectoryID(directoryID);
    }

    @GetMapping("/dirResult")
    public List<DirResult> getDirResult() {
        return miService.getListDirResult();
    }

    @GetMapping("/fileResult")
    public List<FileResult> getFileResult() {
        return miService.getListFileResult();
    }

    @GetMapping("/dirResult/findFileResultByDirectoryID/{directoryID}")
    public List<FileResult> findFileResultByDirectoryID(@PathVariable("directoryID") long directoryID) {
        return miService.findFileResultByDirectoryID(directoryID);
    }

}
