package se.ifmo.trig;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

public class Sec extends TrigFunction {

    private final Cos cos;

    public Sec() {
        this.cos = new Cos();
    }

    @Override
    public Double calculate(Double x, Double eps) {
        x = checkX(x);

        double resultCos = cos.calculate(x, eps);
        double result = 1.0 / resultCos;

        if (result == POSITIVE_INFINITY)
            return POSITIVE_INFINITY;
        return result;
    }
}
