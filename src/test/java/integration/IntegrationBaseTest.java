import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class IntegrationBaseTest {
    public static void main(String args[]){
        org.junit.runner.JUnitCore.main("IntegrationBaseTest");
    }

    // TODO add tests
    @Test(timeout=1000)
    private void testName() {
        assertEquals("",true,true);
    }
}