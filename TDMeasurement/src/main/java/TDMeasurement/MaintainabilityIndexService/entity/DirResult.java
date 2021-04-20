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
public class DirResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dirResultID;

    private Long directoryID;
    private String DirName;
    private int TotalOperator;
    private int TotalOperand;
    private int TotalUniqueOp;
    private int TotalUniqueOpr;
    private double AvgComment;
    private double AvgLineOfCode;
    private int TotalProgVocab;
    private double AvgProgVolume;
    private int TotalProgLength;
    private double TotalEstimateProgLengt;
    private double TotalTimeReqToProg;
    private double TotalDifficulty;
    private double TotalEffort;
    private double TotalNumOfDelBugs;
    private double AvgCC;
    private String TotalCCRate;
    private double AvgMainatainability;
    private String AvgMIRate;

    public Long getDirResultID() {
        return dirResultID;
    }

    public void setDirResultID(Long dirResultID) {
        this.dirResultID = dirResultID;
    }

    public Long getDirectoryID() {
        return directoryID;
    }

    public void setDirectoryID(Long directoryID) {
        this.directoryID = directoryID;
    }

    public String getDirName() {
        return DirName;
    }

    public void setDirName(String dirName) {
        DirName = dirName;
    }

    public int getTotalOperator() {
        return TotalOperator;
    }

    public void setTotalOperator(int totalOperator) {
        TotalOperator = totalOperator;
    }

    public int getTotalOperand() {
        return TotalOperand;
    }

    public void setTotalOperand(int totalOperand) {
        TotalOperand = totalOperand;
    }

    public double getTotalCalcProgLengt() {
        return TotalEstimateProgLengt;
    }

    public void setTotalCalcProgLengt(double totalCalcProgLengt) {
        TotalEstimateProgLengt = totalCalcProgLengt;
    }

    public double getTotalDifficulty() {
        return TotalDifficulty;
    }

    public void setTotalDifficulty(double totalDifficulty) {
        TotalDifficulty = totalDifficulty;
    }

    public double getTotalEffort() {
        return TotalEffort;
    }

    public void setTotalEffort(double totalEffort) {
        TotalEffort = totalEffort;
    }

    public double getTotalNumOfDelBugs() {
        return TotalNumOfDelBugs;
    }

    public void setTotalNumOfDelBugs(double totalNumOfDelBugs) {
        TotalNumOfDelBugs = totalNumOfDelBugs;
    }

    public double getTotalTimeReqToProg() {
        return TotalTimeReqToProg;
    }

    public void setTotalTimeReqToProg(double totalTimeReqToProg) {
        TotalTimeReqToProg = totalTimeReqToProg;
    }

    public double getAvgProgVolume() {
        return AvgProgVolume;
    }

    public void setAvgProgVolume(double avgProgVolume) {
        AvgProgVolume = avgProgVolume;
    }

    public double getAvgLineOfCode() {
        return AvgLineOfCode;
    }

    public void setAvgLineOfCode(double avgLineOfCode) {
        AvgLineOfCode = avgLineOfCode;
    }

    public int getTotalUniqueOp() {
        return TotalUniqueOp;
    }

    public void setTotalUniqueOp(int totalUniqueOp) {
        TotalUniqueOp = totalUniqueOp;
    }

    public int getTotalUniqueOpr() {
        return TotalUniqueOpr;
    }

    public void setTotalUniqueOpr(int totalUniqueOpr) {
        TotalUniqueOpr = totalUniqueOpr;
    }

    public double getAvgComment() {
        return AvgComment;
    }

    public void setAvgComment(double avgComment) {
        AvgComment = avgComment;
    }



    public int getTotalProgLength() {
        return TotalProgLength;
    }

    public void setTotalProgLength(int totalProgLength) {
        TotalProgLength = totalProgLength;
    }

    public int getTotalProgVocab() {
        return TotalProgVocab;
    }

    public void setTotalProgVocab(int totalProgVocab) {
        TotalProgVocab = totalProgVocab;
    }

    public double getAvgCC() {
        return AvgCC;
    }

    public void setAvgCC(double avgCC) {
        AvgCC = avgCC;
    }

    public String getTotalCCRate() {
        return TotalCCRate;
    }

    public void setTotalCCRate(String totalCCRate) {
        TotalCCRate = totalCCRate;
    }

    public double getAvgMainatainability() {
        return AvgMainatainability;
    }

    public void setAvgMainatainability(double avgMainatainability) {
        AvgMainatainability = avgMainatainability;
    }

    public String getAvgMIRate() {
        return AvgMIRate;
    }

    public void setAvgMIRate(String avgMIRate) {
        AvgMIRate = avgMIRate;
    }
}
