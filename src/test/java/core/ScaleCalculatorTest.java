import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class ScaleCalculatorTest {
    public static void main(String args[]){
        org.junit.runner.JUnitCore.main("ScaleCalculatorTest");
    }

    // TODO add tests lol
    @Test(timeout=1000)
    public void ScaleSizeTest() {
       Scale aType = new Scale(0.25f);
       aType.add(88.0/100.0);
       aType.add(90.0/100.0);
       aType.add(78.0/100.0);
       aType.add(85.0/100.0);
       assertEquals("Incorrect, there is an issue with Size.", aType.total().equals(4),true);
    }
       
       @Test(timeout=1000)
    public void ScaleTest1() {
       Scale typeA = new Scale(0.2f);
       typeA.add(98.0/100.0);
       typeA.add(99.0/100.0);
       typeA.add(88.0/100.0);
       typeA.add(99.0/100.0);
       assertEquals("Incorrect, there is an issue with calculate.", typeA.calculate().equals(19.2),true);
    }
    
       @Test(timeout=1000)
    public void ScaleTest2() {
       Scale typeB = new Scale(0.25f);
       typeB.add(88.0/100.0);
       typeB.add(90.0/100.0);
       typeB.add(78.0/100.0);
       typeB.add(85.0/100.0);
       typeB.calculate();
       assertEquals("Incorrect, there is an issue with calculate.", typeB.calculate().equals(21.3),true);
}
    
}