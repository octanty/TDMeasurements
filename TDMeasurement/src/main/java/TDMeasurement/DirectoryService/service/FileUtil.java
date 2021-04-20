package TDMeasurement.DirectoryService.service;

import java.io.File;

public class FileUtil {

 /*   public static boolean isFile(String filepath) {
        File f = new File(filepath);
        return f.exists() && f.isFile();
    }*/

    public static boolean isDir(String dirPath) {
        File f = new File(dirPath);
        return f.exists() && f.isDirectory();
    }

    /**
     * Create multi-level directories
     * @param path
     */
    public static void makeDirs(String path) {
        File file = new File(path);
        // Create if the folder does not exist
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }else {
            System.out.println("Failed to create directory:"+path);
        }
    }
}