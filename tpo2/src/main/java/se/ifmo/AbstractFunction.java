package se.ifmo;

import lombok.Data;

import static java.lang.Double.*;

@Data
public abstract class AbstractFunction {

    public abstract Double calculate(Double x, Double eps);

    public Double checkX(double x) {
        if (isNaN(x) || isInfinite(x)) {
            return NaN;
        }
        return x;
    }


}
