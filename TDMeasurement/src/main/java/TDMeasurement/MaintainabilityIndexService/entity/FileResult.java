package TDMeasurement.MaintainabilityIndexService.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class FileResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileResultID;
    private Long directoryID;
    private int numOperator;
    private int numOperand;
    private int uniqueOperator;
    private int uniqueOperand;
    private String fileName;
    private int programVocabulary;
    private double programVolume;
    private int programLength;
    private int numComment;
    private int lineOfCode;
    private double difficulty;
    private double effort;
    private double numOfDelBugs;
    private double timeReqToProg;
    private double estimateProgLength;
    private int ccValue;
    private String ccRate;
    private double maintainabilityIndex;
    private String miRate;

    public Long getFileResultID() {
        return fileResultID;
    }

    public void setFileResultID(Long fileResultID) {
        this.fileResultID = fileResultID;
    }

    public Long getDirectoryID() {
        return directoryID;
    }

    public void setDirectoryID(Long directoryID) {
        this.directoryID = directoryID;
    }

    public int getNumOperator() {
        return numOperator;
    }

    public void setNumOperator(int numOperator) {
        this.numOperator = numOperator;
    }

    public int getNumOperand() {
        return numOperand;
    }

    public void setNumOperand(int numOperand) {
        this.numOperand = numOperand;
    }

    public int getUniqueOperator() {
        return uniqueOperator;
    }

    public void setUniqueOperator(int uniqueOperator) {
        this.uniqueOperator = uniqueOperator;
    }

    public int getUniqueOperand() {
        return uniqueOperand;
    }

    public void setUniqueOperand(int uniqueOperand) {
        this.uniqueOperand = uniqueOperand;
    }

    public int getNumComment() {
        return numComment;
    }

    public void setNumComment(int numComment) {
        this.numComment = numComment;
    }

    public int getLineOfCode() {
        return lineOfCode;
    }

    public void setLineOfCode(int lineOfCode) {
        this.lineOfCode = lineOfCode;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public int getProgramVocabulary() {
        return programVocabulary;
    }

    public void setVocabularyProgram(int programVocabulary) {
        this.programVocabulary = programVocabulary;
    }

    public double getProgramVolume() {
        return programVolume;
    }

    public void setProgramVolume(double programVolume) {
        this.programVolume = programVolume;
    }

    public int getProgramLength() {
        return programLength;
    }

    public void setProgramlength(int programLength) {
        this.programLength = programLength;
    }

    public double getEstimateProgLength() {
        return estimateProgLength;
    }

    public void setEstimateProgLength(double estimateProgLength) {
        this.estimateProgLength = estimateProgLength;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public double getEffort() {
        return effort;
    }

    public void setEffort(double effort) {
        this.effort = effort;
    }

    public double getNumofDelBugs() {
        return numOfDelBugs;
    }

    public void setNumOfDelBugs(double numOfDelBugs) {
        this.numOfDelBugs = numOfDelBugs;
    }

    public double getTimeReqToProg() {
        return timeReqToProg;
    }

    public void setTimeReqToProg(double timeReqToProg) {
        this.timeReqToProg = timeReqToProg;
    }

    public int getCCValue() {
        return ccValue;
    }

    public void setCCValue(int ccValue) {
        this.ccValue = ccValue;
    }

    public String getCCRate() {
        return ccRate;
    }

    public void setCCRate(String ccRate) {
        this.ccRate = ccRate;
    }

    public double getMaintainabilityIndex() {
        return maintainabilityIndex;
    }

    public void setMaintainabilityIndex(double maintainabilityIndex) {
        this.maintainabilityIndex = maintainabilityIndex;
    }

    public String getMIRate() {
        return miRate;
    }

    public void setMIRate(String MIRate) {
        this.miRate = MIRate;
    }
}