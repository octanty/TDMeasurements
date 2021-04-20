package TDMeasurement.MaintainabilityIndexService.controller;

//refactored from https://github.com/aametwally/Halstead-Complexity-Measures
public class HalsteadMetrics {

    public int totalOperators, totalOperands, uniqueOperators, uniqueOperands;
    private int programVocabulary=0;
    private int programLength=0;
    private double programVolume=0;
    private double difficulty=0;
    private double effort=0;
    private double timeReqProg=0;
    private double numOfDelBugs=0;
    private double estimateProgLength=0;

    public HalsteadMetrics() {
        totalOperators=0;
        totalOperands=0;
        uniqueOperators=0;
        uniqueOperands=0;
    }

    public void setValueParameters(int totalOp, int totalOpr, int uniqueOp, int uniqueOpr)
    {
        totalOperators=totalOp;
        totalOperands=totalOpr;
        uniqueOperators=uniqueOp;
        uniqueOperands=uniqueOpr;
    }

    public int getProgramLength()
    {
         programLength=totalOperators+totalOperands;
        return programLength;
    }


    public int getProgramVocabulary()
    {
        programVocabulary=uniqueOperators+uniqueOperands;
        return programVocabulary;
    }


    public double getDifficulty()
    {
        difficulty=(uniqueOperators/2)*(totalOperands/(double)uniqueOperands);
        return difficulty;
    }

    public double getProgramVolume()
    {
        programVolume=(programLength)*(Math.log(programVocabulary)/Math.log(2));
        return programVolume;
    }

    public double getEstimateProgLength()
    {
        estimateProgLength = uniqueOperators*(Math.log(uniqueOperators) / Math.log(2)) + uniqueOperands*(Math.log(uniqueOperands) / Math.log(2));
        return estimateProgLength;
    }

    public double getEffort()
    {
        effort= difficulty * programVolume;
        return effort;
    }

    public double getNumOfDelBugs()
    {
        numOfDelBugs = programVolume / 3000;
        return numOfDelBugs;
    }

    public double getTimeReqToProgram(){
        timeReqProg = effort / 18;
        return  timeReqProg;
    }
}
