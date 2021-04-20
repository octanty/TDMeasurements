package TDMeasurement;

import TDMeasurement.SqualeService.controller.MetricResults;
import org.apache.commons.digester.RuleSet;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
public class MetricResultTest {
    public String getDirectory(){
        Path serverFilePath = Paths.get("");
        return serverFilePath.toAbsolutePath().toString()+"\\JavaProject\\FolderTest\\01";
    }

    @Test
    public void testIMACValue() {
        MetricResults mrt = new MetricResults();
        double imac = mrt.getIMAfferentCoupling(37);
        assertEquals(0.5, imac,0.001);
    }

    @Test
    public void testIMECValue1() {
        MetricResults mrt = new MetricResults();
        double imec = mrt.getIMEfferentCoupling(9);
        assertEquals(1.4, imec, 0.001);
    }

    @Test
    public void testIMECValue2() {
        MetricResults mrt = new MetricResults();
        double imec = mrt.getIMEfferentCoupling(7);
        assertEquals(2.8, imec, 0.001);
    }

    @Test
    public void testIMSC1() {
        MetricResults mrt = new MetricResults();
        double imsc = mrt.getIMSpaghetyCode(14,2);
        assertEquals(2.5, imsc, 0.001);
    }

    @Test
    public void testIMSC2() {
        MetricResults mrt = new MetricResults();
        double imsc = mrt.getIMSpaghetyCode(14, 9);
        assertEquals(0.5, imsc, 0.001);
    }

    @Test
    public void testIMDC1() throws IOException {
        MetricResults mrt = new MetricResults();
        int imDC = mrt.getIMDependencyCycle(getDirectory());
        assertEquals(0, imDC);
    }

    @Test
    public void testGetDistance() throws IOException {
        MetricResults mrt = new MetricResults();
        int distance = mrt.getDistance(getDirectory());
        assertEquals(0, distance);
    }


/*    @Test
    public void testIMDistance1() {
        MetricResults mrt = new MetricResults();
        double imDistance = mrt.getIMDistance(31);
        assertEquals(2.5, imDistance);
    }

    @Test
    public void testIMDistance1() {
        MetricResults mrt = new MetricResults();
        double imDistance = mrt.getIMDistance(31);
        assertEquals(2.5, imDistance);
    } */

}
