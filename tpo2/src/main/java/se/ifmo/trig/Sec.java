package se.ifmo.trig;

import static java.lang.Double.POSITIVE_INFINITY;

public class Sec extends TrigFunction {

    private final Cos cos;

    public Sec() {
        this.cos = new Cos();
    }
    public Sec(Cos cos) {
        this.cos = cos;
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
