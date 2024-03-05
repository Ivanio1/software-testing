package se.ifmo.trig;


import se.ifmo.AbstractFunction;

import static java.lang.Math.PI;

public abstract class TrigFunction extends AbstractFunction {
    @Override
    public Double checkX(double x) {
        x = super.checkX(x);
        if (x > 0) {
            throw new ArithmeticException("x should be <= 0");
        }
        return x % (2 * PI);
    }
}