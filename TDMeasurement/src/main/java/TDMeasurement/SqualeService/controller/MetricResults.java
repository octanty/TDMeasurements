package TDMeasurement.SqualeService.controller;

import javancss.Javancss;
import jdepend.framework.JDepend;
import jdepend.framework.JavaPackage;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class MetricResults {

    public int getLOC(String pathFile) {
        Javancss javancss = new Javancss(pathFile);
        return javancss.getLOC();
    }

    public int getCyC(String pathFile) {
        Javancss javancss = new Javancss(pathFile);
        Vector vFunctions = javancss.getFunctionMetrics();
        int cc = (Integer) ((Vector) vFunctions.elementAt(0)).elementAt(2);
        return cc;
    }

    public int getIMInheritanceDept(int dit) {
        if (dit > 7) {
            return 0;
        } else if (dit > 6 && dit <= 7) {
            return 1;
        } else if (dit >= 6 && dit > 5) {
            return 2;
        } else if (dit <= 5) {
            return 3;
        } else {
            return 0;
        }
    }

    private int getWeight(String weight){
        if(weight=="hard"){
            return 30;
        }
        else if(weight=="medium"){
            return 9;
        }
        else if(weight=="soft"){
            return 3;
        }
        else {
            return 0;
        }
    }

    public int getWeightID(){
        Weight weight = Weight.SOFT;
        return weight.getValue();
    }

    public int getWeightEC(){
        Weight weight = Weight.SOFT;
        return weight.getValue();
    }

    public int getWeightAC(){
        Weight weight = Weight.MEDIUM;
        return weight.getValue();
    }

    public int getWeightSAK(){
        Weight weight = Weight.SOFT;
        return weight.getValue();
    }

    public int getWeightSOC(){
        Weight weight = Weight.SOFT;
        return weight.getValue();
    }

    public int getWeightNOM(){
        Weight weight = Weight.MEDIUM;
        return weight.getValue();
    }

    public int getWeightMS(){
        Weight weight = Weight.MEDIUM;
        return weight.getValue();
    }

    public int getWeightCC(){
        Weight weight = Weight.SOFT;
        return weight.getValue();
    }

    public int getWeightSC(){
        Weight weight = Weight.MEDIUM;
        return weight.getValue();
    }

    public int getWeightCP(){
        Weight weight = Weight.SOFT;
        return weight.getValue();
    }

    public int getWeightDistance(){
        Weight weight = Weight.SOFT;
        return weight.getValue();
    }

    public int getWeightDC(){
        Weight weight = Weight.SOFT;
        return weight.getValue();
    }


    public double getIMEfferentCoupling(int ce) {
        double ec = 0;
        double rank = (10 - ce) / 2;
        ec = Math.pow(2, rank);
        return ec;
    }

    public double getIMAfferentCoupling(int ca) {
        double ac = 0;
        double rank = (30 - ca) / 7;
        ac = Math.pow(2, rank);
        return ac;
    }

    public int getIMSwissArmyKnife(int lcom, int ca, int rfc) {
        if ((ca > 20) && (lcom > 50) && (rfc > 30)) {
            return 0;
        } else if ((ca <= 20) && (lcom <= 50) && (rfc <= 30)) {
            return 3;
        } else {
            return 0;
        }
    }

    public int getIMClassCohession(int lcom) {
        if (lcom > 100) {
            return 0;
        } else if (lcom > 50) {
            return 1;
        } else if (lcom > 0) {
            return 2;
        } else if (lcom <= 0) {
            return 3;
        } else {
            return 0;
        }
    }

    public double getIMMethodSize(int sloc) {
        double rank = (70 - sloc) / 21;
        double ms = Math.pow(2, rank);
        return ms;
    }


    public int getNOM(String pathFile) {
        Javancss javancss = new Javancss(pathFile);
        Vector vFunctions = javancss.getFunctionMetrics();
        return vFunctions.size();
    }

    public double getIMNOM(int nom, int cyc) {
        double rank = 0;
        if (cyc >= 80) {
            rank = (30 - nom) / 10;
            return Math.pow(2, rank);
        } else if (cyc >= 50 && nom >= 15) {
            return 2 + (20 - nom) / 30;
        } else if (cyc >= 30) {
            return 3 + (15 - nom) / 15;
        } else {
            return 3;
        }
    }

    public double getIMSpaghetyCode(int sloc, int cyc) {
        if (sloc > 30) {
            double rank = (6 - cyc) / 3;
            return Math.pow(2, rank);
        } else {
            return 0;
        }
    }

    public double getIMSOCRate(int ncloc, int sloc, int cyc) {
        double rank = -(cyc) / 15;
        if (cyc >= 5 || sloc > 30) {
            return (ncloc) * 9 / (ncloc + sloc) / (1 - Math.pow(10, rank));
        } else {
            return 0;
        }
    }

    public double getIMCopyPaste(int numDuplication, int sloc) {
        double rank = 100 * numDuplication / sloc;
        return 3 * Math.pow(2 / 3, rank);
    }

    //TODO: fix this function

    public int getDistance(String dir) throws IOException {
        int distance = 0;
        JDepend jdepend = new JDepend();
        jdepend.addDirectory(dir);
        Collection packages = jdepend.analyze();
        Iterator i = packages.iterator();
        while (i.hasNext()) {
            JavaPackage jPackage = (JavaPackage) i.next();
            distance += jPackage.distance();
        }
        return distance;
    }

    public double getIMDistance(String dir) throws IOException {
        double distance = 0;
        distance = getDistance(dir);
        double result = (25 - distance) / 25;
        return 3 + 2 * result;
    }

    public int getIMDependencyCycle(String dir) throws IOException {
        JDepend jdepend = new JDepend();
        jdepend.addDirectory(dir);
        jdepend.analyze();

        if (jdepend.containsCycles()) {
            return 3;
        } else {
            return 0;
        }
    }

    public double getWeightingIM(int weighting, double IM){
        double weightingIM = Math.pow(weighting, -(IM));
        return weightingIM;
    }

  /*  public double calculateGlobalMark(int totalIM){
        double globalMark = -(Math.log())
    }*/
}

    //TODO: create method for inverse function average for weighted function IM
    //TODO: create method for calculateGlobalMark
    //test in postman

