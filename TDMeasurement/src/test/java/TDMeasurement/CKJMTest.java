package TDMeasurement;

import gr.spinellis.ckjm.CkjmOutputHandler;
import gr.spinellis.ckjm.ClassMetrics;
import gr.spinellis.ckjm.MetricsFilter;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import static org.junit.Assert.assertEquals;


public class CKJMTest {
    public String getJavaPath() {
        Path serverFilePath = Paths.get("");
        return serverFilePath.toAbsolutePath().toString() + "\\src\\main\\java\\TDMeasurement\\MaintainabilityIndexService\\controller\\CyclomaticComplexity.java";
    }

    @Test
    public void test() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<ClassMetrics> ref = new AtomicReference<>();
        CkjmOutputHandler outputHandler = new CkjmOutputHandler() {
            @Override
            public void handleClass(String name, ClassMetrics c) {
                System.out.println("name: " + name + ", WMC: " + c.getWmc());
                ref.set(c);
                latch.countDown();
            }
        };
        File f = new File(getJavaPath());
        //assertTrue("File " + f.getAbsolutePath() + " not present", f.exists());
        //File file = changeExtension(f);
        MetricsFilter.runMetrics(new String[] { f.getAbsolutePath() }, outputHandler, true);
        latch.await(1, TimeUnit.SECONDS);
        assertEquals(7, ref.get().getWmc());
    }

    public static File changeExtension(File f) {
        int i = f.getName().lastIndexOf('.');
        String name = f.getName().substring(0,i);
        return new File(f.getParent(), name + ".class");
    }

}
