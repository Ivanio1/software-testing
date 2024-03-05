package se.ifmo.log;

import se.ifmo.AbstractFunction;

public abstract class LogFunction extends AbstractFunction {
    @Override
    public Double checkX(double x) {
        x = super.checkX(x);
        if (x <= 0) {
            throw new ArithmeticException("x should be > 0");
        }
        return x;
    }
}