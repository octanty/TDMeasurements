package TDMeasurement.SqualeService.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Squale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int squaleID;

    private String dirName;
    private int imID;
    private double imEC ;
    private double imAC ;
    private double imSAK ;
    private double imCC;
    private double imMS;
    private double imNOM;
    private double imSC;
    private double imSOCRate;
    private double imDup;
    private double imDistance;
    private double imDC;
    private double wimID;
    private double wimEC ;
    private double wimAC ;
    private double wimSAK ;
    private double wimCC;
    private double wimMS;
    private double wimNOM;
    private double wimSC;
    private double wimDup;
    private double wimDistance;
    private double wimDC;
    private double wimSOCRate;

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public int getSqualeID() {
        return squaleID;
    }

    public void setSqualeID(int squaleID) {
        this.squaleID = squaleID;
    }

    public int getimID() {
        return imID;
    }

    public void setimID(int imID) {
        this.imID = imID;
    }

    public double getimEC() {
        return imEC;
    }

    public void setimEC(double imEC) {
        this.imEC = imEC;
    }

    public double getimAC() {
        return imAC;
    }

    public void setimAC(double imAC) {
        this.imAC = imAC;
    }

    public double getimSAK() {
        return imSAK;
    }

    public void setimSAK(double imSAK) {
        this.imSAK = imSAK;
    }

    public double getimCC() {
        return imCC;
    }

    public void setimCC(double imCC) {
        this.imCC = imCC;
    }

    public double getimMS() {
        return imMS;
    }

    public void setimMS(double imMS) {
        this.imMS = imMS;
    }

    public double getimNOM() {
        return imNOM;
    }

    public void setimNOM(double imNOM) {
        this.imNOM = imNOM;
    }

    public double getimSC() {
        return imSC;
    }

    public void setimSC(double imSC) {
        this.imSC = imSC;
    }

    public double getimSOCRate() {
        return imSOCRate;
    }

    public void setimSOCRate(double imSOCRate) {
        this.imSOCRate = imSOCRate;
    }

    public double getimDup() {
        return imDup;
    }

    public void setimDup(double imDup) {
        this.imDup = imDup;
    }

    public double getimDistance() {
        return imDistance;
    }

    public void setimDistance(double imDistance) {
        this.imDistance = imDistance;
    }

    public double getimDC() {
        return imDC;
    }

    public void setimDC(double imDC) {
        this.imDC = imDC;
    }

    public void setWimID(double wimID) {
        this.wimID = wimID;
    }

    public double getWimID() {
        return wimID;
    }

    public void setWimEC(double wimEC) {
        this.wimEC = wimEC;
    }

    public double getWimEC() {
        return wimEC;
    }

    public void setWimAC(double wimAC) {
        this.wimAC = wimAC;
    }

    public double getWimAC() {
        return wimAC;
    }

    public double getWimSAK() {
        return wimSAK;
    }

    public void setWimSAK(double wimSAK) {
        this.wimSAK = wimSAK;
    }

    public double getWimCC() {
        return wimCC;
    }

    public void setWimCC(double wimCC) {
        this.wimCC = wimCC;
    }

    public double getWimMS() {
        return wimMS;
    }

    public void setWimMS(double wimMS) {
        this.wimMS = wimMS;
    }

    public double getWimNOM() {
        return wimNOM;
    }

    public void setWimNOM(double wimNOM) {
        this.wimNOM = wimNOM;
    }

    public double getWimSC() {
        return wimSC;
    }

    public void setWimSC(double wimSC) {
        this.wimSC = wimSC;
    }

    public double getWimSOCRate() {
        return wimSOCRate;
    }

    public void setWimSOCRate(double wimSOCRate) {
        this.wimSOCRate = wimSOCRate;
    }

    public double getWimDup() {
        return wimDup;
    }

    public void setWimDup(double wimDup) {
        this.wimDup = wimDup;
    }

    public double getWimDistance() {
        return wimDistance;
    }

    public void setWimDistance(double wimDistance) {
        this.wimDistance = wimDistance;
    }

    public double getWimDC() {
        return wimDC;
    }

    public void setWimDC(double wimDC) {
        this.wimDC = wimDC;
    }
}
