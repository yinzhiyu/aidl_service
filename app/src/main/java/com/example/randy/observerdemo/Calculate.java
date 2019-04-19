package com.example.randy.observerdemo;

/**
 * @author randy
 */
public class Calculate {
    private double a;
    private double b;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    double calculateSum(double a, double b) {
        return a + b;
    }
}
