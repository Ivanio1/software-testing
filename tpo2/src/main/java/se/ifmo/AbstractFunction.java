package se.ifmo;

import lombok.Data;

import static java.lang.Double.*;

@Data
public abstract class AbstractFunction {

    protected abstract Double calculateValue(Double x, Double eps);

    public Double checkX(double x) {
        if (isNaN(x) || isInfinite(x)) {
            return NaN;
        }
        return x;
    }

    public final Double checkAndCalculate(Double x, Double eps) {
        checkX(x);
        return calculateValue(x, eps);
    }
}
