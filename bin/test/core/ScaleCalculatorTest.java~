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
       Scale assType = new Scale(0.25);
       assType.add(88);
       assType.add(90);
       assType.add(78);
       assType.add(85);
       assType.total();
        assertEquals("Whoopsies",4);  
    }
    
    @Test(timeout=1000)
    public void ScaleCalcTest1() {
       Scale assType = new Scale(0.25);
       assType.add(88);
       assType.add(90);
       assType.add(78);
       assType.add(85);
       assType.calculate();
       assertEquals("Whoopsies",21.3);
}