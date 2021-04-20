package TDMeasurement.MaintainabilityIndexService.service;


import TDMeasurement.MaintainabilityIndexService.controller.*;
import TDMeasurement.MaintainabilityIndexService.entity.DirResult;
import TDMeasurement.MaintainabilityIndexService.entity.FileResult;
import TDMeasurement.MaintainabilityIndexService.repository.DirResultRepository;
import TDMeasurement.MaintainabilityIndexService.repository.FileResultRepository;
import TDMeasurement.MaintainabilityIndexService.vo.Directory;
import TDMeasurement.SqualeService.controller.MetricResults;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MIService {
    @Autowired
    FileResultRepository fileResultRepository;

    @Autowired
    DirResultRepository dirResultRepository;


    double maintainabilityIndex;
    String MIRate;
    double avghalsteadVolume;
    double avgCC;
    double avgLOC;
    double avgComment;

    @Autowired
    private RestTemplate restTemplates;

    @Bean
    public RestTemplate restTemplates(RestTemplateBuilder builder) {

        return builder.build();
    }

    public List<DirResult> calculate() throws IOException {

      ResponseEntity<Directory[]> response = restTemplates.getForEntity(
                "http://localhost:8004/directories/listDirectoryInDB", Directory[].class);

        List<Directory> listDirectory =  Arrays.asList(response.getBody());
        List<DirResult> list = new ArrayList<>();
        DirResult dr = new DirResult();
        for(int n=0; n<listDirectory.size(); n++) {
          List<String> listJavaFiles = getJavaFiles(listDirectory.get(n).getPath());
          List<char[]> filesString = parseListFile(listJavaFiles);

          ASTVisitorCheck astVisitor;
          int uniqueOperators = 0;
          int uniqueOperands = 0;
          int totalNumOperators = 0;
          int totalNumOperands = 0;
          int numOperator = 0;
          int numOperand = 0;
          int uniqueOperator = 0;
          int uniqueOperand = 0;
          int ccValue = 0;
          int totalCC = 0;
          int comment = 0;
          int totalComment = 0;
          int loc = 0;
          int totalLOC = 0;
          int fileCount = 0;
          String ccRate="";
          String totalCCRate;

          for (int i = 0; i < filesString.size(); i++){
              astVisitor = parseFile(filesString.get(i));
              uniqueOperator = astVisitor.op.size();
              uniqueOperand = astVisitor.operand.size();
              uniqueOperators += uniqueOperator;
              uniqueOperands += uniqueOperand;

              numOperator = 0;
              for (int operator : astVisitor.op.values()) {
                  numOperator += operator;
              }

              totalNumOperators += numOperator;

              numOperand = 0;
              for (int operand : astVisitor.operand.values()) {
                  numOperand += operand;
              }

              totalNumOperands += numOperand;
              CyclomaticComplexity cc = new CyclomaticComplexity();
              MetricResults mr = new MetricResults();
           /*   ccValue =  mr.getCyC(listJavaFiles.get(i));
              ccRate = cc.getCCRate(ccValue); */
              CLOC commentLOC = new CLOC();
              comment = commentLOC.getCommentNumber(listJavaFiles.get(i));
              totalComment += comment;
              loc = mr.getLOC(listJavaFiles.get(i));
              totalLOC += loc;
              setFileResult(uniqueOperator, uniqueOperand, numOperator, numOperand, listJavaFiles.get(i), ccValue, ccRate, comment, loc, listDirectory.get(n).getDirectoryID());
              totalCC += ccValue;
              fileCount++;
          }

          CyclomaticComplexity cycom = new CyclomaticComplexity();
          totalCCRate = cycom.getCCRate(totalCC);
          dr = setDirResult(uniqueOperators, uniqueOperands, totalNumOperators, totalNumOperands, listDirectory.get(n).getPath(), listDirectory.get(n).getDirectoryID(), totalCC, totalCCRate, fileCount, totalComment, totalLOC);
          list.add(dr);
      }
        return list;
    }

    public List<DirResult> getListDirResult(){
        return dirResultRepository.findAll();
    }

    public List<FileResult> getListFileResult(){
        return fileResultRepository.findAll();
    }

    //refactored from https://github.com/aametwally/Halstead-Complexity-Measures
    public static List<String> getJavaFiles(String directory) {
        List<String> listFiles = new ArrayList<String>();
        File stringDir = new File(directory);

        if (!stringDir.isDirectory())
        {
            System.out.println("The directory is not valid");
            System.exit(1);
        }

        for (File filePath : stringDir.listFiles()) {
            if(filePath.isDirectory())
            {
                listFiles.addAll(getJavaFiles(filePath.getAbsolutePath()));
            }
            if (filePath.getName().endsWith((".java")))
            {
                listFiles.add(filePath.getAbsolutePath());
            }
        }

        return listFiles;
    }

    public FileResult findByFileResultID(long findByFileResultID) {
        return fileResultRepository.findByFileResultID(findByFileResultID);
    }

    public FileResult setFileResult(int uniqueOperator, int uniqueOperand, int numOperator, int numOperand, String fileName, int ccValue, String ccRate, int comment, int loc, long directoryId){
        FileResult fr = new FileResult();
        fr.setNumOperator(numOperator);
        fr.setNumComment(comment);
        fr.setNumOperand(numOperand);
        fr.setUniqueOperator(uniqueOperator);
        fr.setUniqueOperand(uniqueOperand);
        fr.setNumComment(comment);
        fr.setLineOfCode(loc);
        fr.setDirectoryID(directoryId);
        fr.setCCValue(ccValue);
        fr.setCCRate(ccRate);
        fr.setFileName(fileName);
        HalsteadMetrics hm = new HalsteadMetrics();
        hm.setValueParameters(uniqueOperator, uniqueOperand, numOperator, numOperand);
        fr.setVocabularyProgram(hm.getProgramVocabulary());
        fr.setProgramlength(hm.getProgramLength());
        MaintainabilityIndex maintainabilityIndex = new MaintainabilityIndex();
        double resultMI = maintainabilityIndex.getMaintainabilityIndex(hm.getProgramVolume(), ccValue, loc, comment);
        String miRate = maintainabilityIndex.getMaintainabilityRate(resultMI);
        fr.setMaintainabilityIndex(resultMI);
        fr.setMIRate(miRate);
        fr.setDifficulty(hm.getDifficulty());
        fr.setProgramVolume(hm.getProgramVolume());
        fr.setEstimateProgLength(hm.getEstimateProgLength());
        fr.setEffort(hm.getEffort());
        fr.setNumOfDelBugs(hm.getNumOfDelBugs());
        fr.setTimeReqToProg(hm.getTimeReqToProgram());
        return fileResultRepository.save(fr);
    }


    public DirResult findByDirResultID(long findByDirResultID) {
        return dirResultRepository.findByDirResultID(findByDirResultID);
    }

    public DirResult findDirResultByDirectoryID(long directoryID) {
        return dirResultRepository.findByDirectoryID(directoryID);
    }

    public List<FileResult> findFileResultByDirectoryID(long directoryID) {
        return fileResultRepository.findByDirectoryID(directoryID);
    }

    public DirResult setDirResult(int uniqueOperators, int uniqueOperands, int totalOperators, int totalOperands, String dirName, long directoryId, int totalCC, String totalCCRate, int count, int totalComment, int totalLOC){
        DirResult hmt = new DirResult();
        hmt.setDirectoryID(directoryId);
        hmt.setTotalOperand(totalOperands);
        hmt.setTotalOperator(totalOperators);
        hmt.setTotalUniqueOp(uniqueOperators);
        hmt.setTotalUniqueOpr(uniqueOperands);
        hmt.setDirName(dirName);
        hmt.setTotalCCRate(totalCCRate);
        HalsteadMetrics hm = new HalsteadMetrics();
        hm.setValueParameters(uniqueOperators, uniqueOperands, totalOperators, totalOperands);
        hmt.setTotalProgVocab(hm.getProgramVocabulary());
        hmt.setTotalProgLength(hm.getProgramLength());
        avghalsteadVolume = hm.getProgramVolume()/count;
        avgCC = totalCC/count;
        avgLOC = totalLOC/count;
        avgComment = totalComment/count;
        MaintainabilityIndex maintainabilityIndex = new MaintainabilityIndex();
        double miResult = maintainabilityIndex.getAvgMaintainabilityIndex(avghalsteadVolume, avgCC, avgLOC, avgComment);
        String miRate = maintainabilityIndex.getMaintainabilityRate(miResult);
        hmt.setAvgMainatainability(miResult);
        hmt.setAvgMIRate(miRate);
        hmt.setTotalDifficulty(hm.getDifficulty());
        hmt.setAvgProgVolume(avghalsteadVolume);
        hmt.setAvgComment(avgComment);
        hmt.setAvgCC(avgCC);
        hmt.setAvgLineOfCode(avgLOC);
        hmt.setTotalCalcProgLengt(hm.getEstimateProgLength());
        hmt.setTotalEffort(hm.getEffort());
        hmt.setTotalNumOfDelBugs(hm.getNumOfDelBugs());
        hmt.setTotalTimeReqToProg(hm.getTimeReqToProgram());
        return dirResultRepository.save(hmt);
    }

    //refactored from https://github.com/aametwally/Halstead-Complexity-Measures
    public static List<char[]> parseListFile(List<String> listJavaFiles) throws IOException{
        if(listJavaFiles.isEmpty())
        {
            System.out.println("There is no source code");
            System.exit(0);
        }

        List<char[]> filesString= new ArrayList<>();

        for(int i=0; i< listJavaFiles.size(); i++)
        {
            filesString.add(parseFileToChar(listJavaFiles.get(i)));
        }

        return filesString;
    }

    //refactored from https://github.com/aametwally/Halstead-Complexity-Measures
    public static ASTVisitorCheck parseFile(char[] str) {
        ASTParser newASTParser = ASTParser.newParser(AST.JLS3);
        newASTParser.setSource(str);
        newASTParser.setKind(ASTParser.K_COMPILATION_UNIT);
        newASTParser.setResolveBindings(true);
        final CompilationUnit compilationUnit = (CompilationUnit) newASTParser.createAST(null);

        ASTVisitorCheck extraction= new ASTVisitorCheck();
        compilationUnit.accept(extraction);

        return extraction;
    }

    //refactored from https://github.com/aametwally/Halstead-Complexity-Measures
    public static char[] parseFileToChar(String javaFilePath) throws IOException {
        StringBuilder dataFile = new StringBuilder(1000);
        BufferedReader fileReader = new BufferedReader(new FileReader(javaFilePath));
        char[] buf = new char[10];
        int fileCount = 0;
        while ((fileCount = fileReader.read(buf)) != -1) {
            String data = String.valueOf(buf, 0, fileCount);
            dataFile.append(data);
            buf = new char[1024];
        }
        fileReader.close();

        return dataFile.toString().toCharArray();
    }

}
