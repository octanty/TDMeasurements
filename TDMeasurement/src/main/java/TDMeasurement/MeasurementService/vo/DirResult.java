package TDMeasurement.MeasurementService.vo;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DirResult {

    private Long dirResultID;
    private Long directoryID;
    private String DirName;
    private int TotalOperator;
    private int TotalOperand;
    private int TotalUniqueOp;
    private int TotalUniqueOpr;
    private int TotalComment;
    private int TotalProgVocab;
    private double TotalProgVolume;
    private int TotalProgLength;
    private double TotalEstimateProgLengt;
    private double TotalTimeReqToProg;
    private double TotalDifficulty;
    private double TotalEffort;
    private double TotalNumOfDelBugs;
    private int TotalCC;
    private String TotalCCRate;
    private double AvgMainatainability;
    private String AvgMIRate;
}