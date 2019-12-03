package core;

public abstract class Calculator {
    float int1;
    float int2;

    public Calculator(float int1, float int2) {
        this.int1 = int1;
        this.int2 = int2;
    }
    public abstract float calculate();

    public float add(float int1, float int2) {
        return int1 + int2;
    }
    public float subtract(float int1, float int2) {
        return int1 - int2;
    }
    public float multiply(float int1, float int2) {
        return int1 * int2
    }
    public float divide(float int1, float int2) {
        return int1 / int2;
    }
}