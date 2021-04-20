package TDMeasurement;

import TDMeasurement.MaintainabilityIndexService.controller.CyclomaticComplexity;
import gr.spinellis.ckjm.CkjmOutputHandler;
import gr.spinellis.ckjm.ClassMetrics;
import gr.spinellis.ckjm.MetricsFilter;
import javancss.Javancss;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import static org.junit.Assert.assertEquals;
//TODO: delete this test
public class CyclomaticComplexityTest {

    public String getJavaPath(){
        Path serverFilePath = Paths.get("");
        return serverFilePath.toAbsolutePath().toString()+"\\src\\main\\java\\TDMeasurement\\MaintainabilityIndexService\\controller\\CyclomaticComplexity.java";
    }


    @Test
    public void testGetCCValue(){
        Javancss javancss = new Javancss(getJavaPath());
        Vector vFunctions = javancss.getFunctionMetrics();
        int cc = (Integer) ((Vector) vFunctions.elementAt(0)).elementAt(2);
        assertEquals(15,cc);
    }


    @Test
    public void testGetCCRateExtraHigh(){
        CyclomaticComplexity ccRate = new CyclomaticComplexity();
        String rate = ccRate.getCCRate(70);
        assertEquals("Extra High", rate);
    }

    @Test
    public void testGetCCRateVeryHigh(){
        CyclomaticComplexity ccRate = new CyclomaticComplexity();
        String rate = ccRate.getCCRate(46);
        assertEquals("Very High", rate);
    }

    @Test
    public void testGetCCRateHigh(){
        CyclomaticComplexity ccRate = new CyclomaticComplexity();
        String rate = ccRate.getCCRate(30);
        assertEquals("High", rate);
    }

    @Test
    public void testGetCCRateNominal(){
        CyclomaticComplexity ccRate = new CyclomaticComplexity();
        String rate = ccRate.getCCRate(15);
        assertEquals("Nominal", rate);
    }

    @Test
    public void testGetCCRateLow(){
        CyclomaticComplexity ccRate = new CyclomaticComplexity();
        String rate = ccRate.getCCRate(7);
        assertEquals("Low", rate);
    }

    @Test
    public void testGetCCRateVeryLow(){
        CyclomaticComplexity ccRate = new CyclomaticComplexity();
        String rate = ccRate.getCCRate(3);
        assertEquals("Very Low", rate);
    }
}
