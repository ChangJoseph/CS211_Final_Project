
package core;

import exception.ScaleException;

import java.util.ArrayList;
import java.util.List;

public class Scale extends Calculator {
    private List<Double> grades;
    private float percentage;

    public Scale(float percentage, List<Double> grades) {
        this.setScale(percentage);
        this.grades = grades;
    }
    public Scale(float percentage) {
        this(percentage, new ArrayList<Double>());
    }

    public Scale() {
        super();
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

    public void add(Double grade) throws ScaleException {
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
    public float calculate() throws ScaleException {
        int gradesSize = grades.size();
        if (gradesSize <= 0) {
            throw new ScaleException("Grade Array is Empty!");
        }
        else {
            double total = 0.0;
            for (double i: grades) {
                total += i;
            }
            total /= gradesSize;
            double calc = total * percentage;
            calc = Math.round(calc *100.0) / 100.0;
            return (float) calc;
        }
    }


}