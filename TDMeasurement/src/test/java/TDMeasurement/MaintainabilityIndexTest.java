package TDMeasurement;

import TDMeasurement.MaintainabilityIndexService.controller.MaintainabilityIndex;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MaintainabilityIndexTest {
    @Test
    public void testGetMaintainabilityIndex(){
        MaintainabilityIndex mi = new MaintainabilityIndex();
        double MaintainabilityIndex = mi.getMaintainabilityIndex(188.24,11,52,1 );
        assertEquals(127, Math.round(MaintainabilityIndex));
    }

    @Test
    public void testGetAvgMainTainabilityIndex(){
        MaintainabilityIndex mi = new MaintainabilityIndex();
        double AvgMaintainabilityIndex = mi.getAvgMaintainabilityIndex(896.80, 25.0, 116.0,1.0);
        assertEquals(103, Math.round(AvgMaintainabilityIndex));
    }

    @Test
    public void testGetHighRate(){
        MaintainabilityIndex mi = new MaintainabilityIndex();
        String miRate = mi.getMaintainabilityRate(90);
        assertEquals("High", miRate);
    }

    @Test
    public void testGetModerateRate(){
        MaintainabilityIndex mi = new MaintainabilityIndex();
        String miRate = mi.getMaintainabilityRate(70);
        assertEquals("Moderate", miRate);
    }

    @Test
    public void testLowRate() throws IOException {
        MaintainabilityIndex mi = new MaintainabilityIndex();
        String miRate = mi.getMaintainabilityRate(35);
        assertEquals("Low", miRate);

    }

}
