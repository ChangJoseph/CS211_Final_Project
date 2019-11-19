import java.util.ArrayList;

public class Scale extends Calculator {
    private ArrayList<Double> grades = new ArrayList<Double>();
    private float percentage;

    public Scale(float percentage) {
        this.setScale(percentage);
    }

    public Scale() {
        percentage = 0;
    }

    public void setScale(float percentage) throws ScaleException {
        if (percentage <= 0) {
            throw new ScaleException("Can't be equal or less than zero.");
        } 
        else {
            this.percentage = percentage;
        }
    }

    public float getScale() {
        return percentage;
    }

    public void add(double grade) throws ScaleException {
        if (grade < 0) {
            throw new ScaleException("Cannot accept grade below zero.");
        }
        else {
            grades.add(grade);  
        }
    }

    public int total() {
        int gradesSize = grades.size();
        return gradesSize;
    }

    @Override
    public double calculate() throws ScaleException {
        int gradesSize = grades.size();
        if (gradesSize <= 0) {
            throw new ScaleException("Grade Array is Empty!");
        }
        else {
            double total = 0.0;
            for (double i: grades) {
                total += i;
            }
            double calc = total/gradesSize;
            calc *= percentage * 100;
            calc = Math.round(calc *100.0) / 100.0;
            return calc;
        }
    }


}