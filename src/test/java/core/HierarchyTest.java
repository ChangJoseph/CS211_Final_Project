import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class HierarchyTest {
    public static void main(String args[]){
        org.junit.runner.JUnitCore.main("HeirarchyTest");
    }
    
    @Test(timeout=1000)
    private void CumGPACalc1() {
      CumulativeGPACalculator test1 = new CumulativeGPACalculator(4.00, 16, 4.00, 100);
        assertEquals("Cumulative GPA Calculator error.", test1.calculate().equals(4.0),true);
        assertEquals("Cumulative GPA Calculator error.", test1.getGPAFromPercentage(80).equals(3.0),true);
        assertEquals("Cumulative GPA Calculator error.", test1.getLetterGrade(3.5).equals("A"),true);
    }
    
    @Test(timeout=1000)
    private void CumGPACalc2() {
      CumulativeGPACalculator test2 = new CumulativeGPACalculator(3.50, 12, 4.0, 12);
        assertEquals("Cumulative GPA Calculator error.", test2.calculate().equals(3.75),true);
        assertEquals("Cumulative GPA Calculator error.", test2.getGPAFromPercentage(66).equals(2.3),true);
        assertEquals("Cumulative GPA Calculator error.", test2.getLetterGrade(2.78).equals("B-"),true);
    }
    
    @Test(timeout=1000)
    private void Grades1() {
      List<Double> list = Arrays.asList(98.5, 78.0, 93.5);
      Grades test1 = new Grades(4, list);
      assertEquals("Grades error",test2.getGrades().equals([98.5, 78.0, 93.5]),true);
    }
    
    @Test(timeout=1000)
    private void Grades2() {
      List<Double> list = Arrays.asList(77.5, 35.0, 66.5);
      Grades test2 = new Grades(3, list);
      assertEquals("Grades error",test2.getGrades().equals([77.5, 35.0, 66.5]),true);
    }
    
    
    }