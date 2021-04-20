package TDMeasurement.MaintainabilityIndexService.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
//refactored from
public class CLOC {
    public int getCommentNumber(String file) throws IOException {
        int comment = 0;
        Scanner scan = new Scanner(new File(file));
        String code;
        while (scan.hasNextLine()) {
            code = scan.nextLine();
            if (code.contains("/*")) {
                comment ++;
            }
            if(code.contains("//")) {
                comment ++;
            }
        }
        return comment;
    }

}
