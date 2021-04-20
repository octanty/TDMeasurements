package TDMeasurement;

import TDMeasurement.MaintainabilityIndexService.controller.CLOC;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class CommentTest {

    public String getJavaPath(){
        Path serverFilePath = Paths.get("");
        return serverFilePath.toAbsolutePath().toString()+"\\src\\main\\java\\TDMeasurement\\MaintainabilityIndexService\\controller\\CyclomaticComplexity.java";
    }

    @Test
    public void testGetComment() throws IOException {
        CLOC comment = new CLOC();
        int numComment = comment.getCommentNumber(getJavaPath());
        assertEquals(2, numComment);
    }

}
