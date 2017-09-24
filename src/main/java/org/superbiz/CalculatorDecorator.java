package org.superbiz;

public class CalculatorDecorator {

    private Calculator calculator = new Calculator();

    public String add(int a, int b) {
        final int result = calculator.add(a, b);
        return String.format("%d + %d = %d", a, b, result);
    }

    public String multiply(int a, int b) {
        final int result = calculator.multiply(a, b);
        return String.format("%d X %d = %d", a, b, result);
    }

}
