package TDMeasurement.DirectoryService.controller;

import TDMeasurement.DirectoryService.entity.Directory;
import TDMeasurement.DirectoryService.service.DirectoryService;
import TDMeasurement.DirectoryService.vo.LocalDirectory;
import com.github.javaparser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/directories")
public class DirectoryController {

    @Autowired
    private DirectoryService directoryService;

    @PostMapping("/upload")
    public String upload(@RequestParam("dir") MultipartFile dir) throws IOException, ParseException {
        return directoryService.upload(dir);
    }

    @GetMapping("/listDirectory")
    public List<LocalDirectory> listDirectory() {
        return directoryService.getListDirectory();
    }

    @GetMapping("/listDirectoryInDB")
    public List<Directory> getListDirectoryInDB() {
        return directoryService.getListDirectoryInDB();
    }

    @PostMapping("/saveToRepository")
    public List<Directory> saveToRepository(){
        return directoryService.saveToRepository();
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return directoryService.uploadStatus();
    }

    @DeleteMapping("/deleteDirectory")
    public String deleteDirectory() throws IOException {
        return directoryService.deleteDirectories();
    }

}
