package TDMeasurement.DirectoryService.service;

import TDMeasurement.DirectoryService.controller.UnzipFile;
import TDMeasurement.DirectoryService.entity.Directory;
import TDMeasurement.DirectoryService.repository.DirectoryRepository;
import TDMeasurement.DirectoryService.vo.LocalDirectory;
import com.github.javaparser.ParseException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.javaparser.JavaParser.parse;


@Service
public class DirectoryService {
    //TODO edit upload folder to directory server
    //private static String UPLOADED_FOLDER = "C:\\Users\\Octanty\\Documents\\Course\\Masaryk\\thesis\\JavaProjectForThesis\\";

    String currentDirectory ="";

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public String getZipPath(){
        Path serverFilePath = Paths.get("");
        return serverFilePath.toAbsolutePath().toString()+"\\JavaProjectZIP\\";
    }

    public String getJavaPath(){
        Path serverFilePath = Paths.get("");
        return serverFilePath.toAbsolutePath().toString()+"\\JavaProject\\"+currentDirectory+"\\";
    }

    @Autowired
    DirectoryRepository directoryRepository;

        //TODO: refactor upload function to zip
  /*      public String upload(MultipartFile[] dir){
        File file;
        String nameFile="";
        String pathFile="";
        for (
        MultipartFile fi : dir) {
        nameFile=fi.getOriginalFilename();
        String type=fi.getContentType();
        pathFile=UPLOADED_FOLDER+nameFile.substring(0,nameFile.lastIndexOf("/"));
        if(!FileUtil.isDir(pathFile)){
            FileUtil.makeDirs(pathFile);
        }
        file = new File(UPLOADED_FOLDER + nameFile);
        try {
            file.createNewFile();
            //Save the uploaded file to a target file
            fi.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        return "upload success";
    } */

    public String upload(MultipartFile zipFile) throws ParseException, IOException {
        setCurrentDirectory("");
        byte[] bytes = zipFile.getBytes();
        Path file = Paths.get(getZipPath()+ zipFile.getOriginalFilename());
        Files.write(file,bytes);
        UnzipFile uf = new UnzipFile();
        uf.unzip(file.toString(),getJavaPath());
        String filename = getFileNameWithoutExt(zipFile.getOriginalFilename());
        setCurrentDirectory(filename);
        return "upload success";
    }

    //refactored from https://www.technicalkeeda.com/java-tutorials/get-filename-without-extension-using-java
    private static String getFileNameWithoutExt(String fileName) {
        String name = "";

        try {
            if (fileName != null) {
                name = fileName.replaceFirst("[.][^.]+$", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            name = "";
        }

        return name;

    }

    public String uploadStatus() {
        return "uploadStatus";
    }

  public List<LocalDirectory> getListDirectory() {
      List<String> result = null;
      try (Stream<Path> listFiles = Files.walk(Paths.get(getJavaPath()), 1)) {
          result = listFiles.filter(Files::isDirectory)
                  .map(x -> x.toString()).collect(Collectors.toList());
      } catch (IOException e) {
          e.printStackTrace();
      }
      List<LocalDirectory> listLD = new ArrayList<>();
      for (int i = 1; i < result.size(); i++) {
            LocalDirectory ld = new LocalDirectory();
            ld.setPath(result.get(i));
            listLD.add(ld);
      }
      return listLD;
  }
   public List<Directory> saveToRepository(){

            List<LocalDirectory> listDirectory = getListDirectory();
            List<Directory> listDir = new ArrayList<>();
            for(int i=0; i<listDirectory.size(); i++ ){
                Directory dir = new Directory();
                dir.setPath(listDirectory.get(i).getPath());
                listDir.add(dir);
                directoryRepository.save(dir);
            }
        return listDir;
   }

    public String deleteDirectories() throws IOException {
            List<LocalDirectory> listDirectory = getListDirectory();
            for(int i=0;i<listDirectory.size();i++) {
                File file = new File(listDirectory.get(i).getPath());
                FileUtils.deleteDirectory(file);
            }
        return "Directory deleted";
    }

   public List<Directory> getListDirectoryInDB(){
         return directoryRepository.findAll();
   }


/*    public String pushandcommit(){
        try{
            String localPath = "C:\\Users\\Octanty\\Documents\\Course\\Masaryk\\thesis\\JavaProjectForThesis";
            String httpUrl = "https://github.com/octanty/JavaProjectForThesis";
            Repository localRepo = new FileRepository(localPath);
            try (Git git = Git.open(new File(localPath))) {

                AddCommand add = git.add();

                add.addFilepattern(".").call();

                git.commit().setMessage("new commit").call();
                RemoteAddCommand remoteAddCommand = git.remoteAdd();
                remoteAddCommand.setName("origin");
                remoteAddCommand.setUri(new URIish(httpUrl));

                remoteAddCommand.call();

                PushCommand pushCommand = git.push();
                pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider("octanty", "calya123#"));
                pushCommand.call();
            }


        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "redirect:/uploadStatus";
    } */
}
