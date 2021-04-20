package TDMeasurement;

import TDMeasurement.MaintainabilityIndexService.controller.HalsteadMetrics;
import org.junit.Test;
import static org.junit.Assert.*;


public class HalsteadTest {
    @Test
    public void testGetLengthProgram() {
        HalsteadMetrics hmTest = new HalsteadMetrics();
        hmTest.setValueParameters(12,13,10, 5);
        assertEquals(25, hmTest.getProgramLength());
    }


    @Test
    public void testGetVocabularyProgram() {
        HalsteadMetrics testHal = new HalsteadMetrics();
        testHal.setValueParameters(12,13,10, 5);
        assertEquals(15, testHal.getProgramVocabulary());
    }


    @Test
    public void testGetDifficulty(){
        HalsteadMetrics hmTest = new HalsteadMetrics();
        hmTest.setValueParameters(12,13,10, 5);
        assertEquals(13, Math.round(hmTest.getDifficulty()));
    }

    @Test
    public void testGetProgramVolume(){
        HalsteadMetrics hmTest = new HalsteadMetrics();
        hmTest.setValueParameters(12,13,10, 5);
        hmTest.getProgramLength();
        hmTest.getProgramVocabulary();
        assertEquals(98, Math.round(hmTest.getProgramVolume()));
    }

    @Test
    public void testGetEstimateProgLength(){
        HalsteadMetrics hmTest = new HalsteadMetrics();
        hmTest.setValueParameters(12,13,10, 5);
        assertEquals(45, Math.round(hmTest.getEstimateProgLength()));
    }


    @Test
   public void testGetEffort(){
       HalsteadMetrics hmTest = new HalsteadMetrics();
       hmTest.setValueParameters(12,13,10, 5);
       hmTest.getDifficulty();
       hmTest.getProgramLength();
       hmTest.getProgramVocabulary();
       hmTest.getProgramVolume();
       assertEquals(1270, Math.round(hmTest.getEffort()));
   }

    @Test
    public void testGetNumberOfDelBugs(){
        HalsteadMetrics hmTest = new HalsteadMetrics();
        hmTest.setValueParameters(12,13,10, 5);
        hmTest.getProgramLength();
        hmTest.getProgramVocabulary();
        hmTest.getProgramVolume();
        assertEquals(0, Math.round(hmTest.getEffort()));
    }


    @Test
    public void testGetTimeToReqProgram(){
        HalsteadMetrics hmTest = new HalsteadMetrics();
        hmTest.setValueParameters(12,13,10, 5);
        hmTest.getDifficulty();
        hmTest.getProgramLength();
        hmTest.getProgramVocabulary();
        hmTest.getProgramVolume();
        hmTest.getEffort();
        assertEquals(71, Math.round(hmTest.getTimeReqToProgram()));
    }

}
