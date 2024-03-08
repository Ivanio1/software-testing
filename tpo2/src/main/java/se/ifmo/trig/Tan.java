package se.ifmo.trig;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

public class Tan extends TrigFunction {
    private final Sin sin;
    private final Cos cos;

    public Tan() {
        this.sin = new Sin();
        this.cos = new Cos();
    }

    public Tan(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public Double calculate(Double x, Double eps) {
        x = checkX(x);

        double resultSin = sin.calculate(x,eps);
        double resultCos = cos.calculate(x,eps);

        if (resultCos == 0.0 && resultSin > 0) {
            return POSITIVE_INFINITY;
        } else if (resultCos == 0.0 && resultSin < 0) {
            return NEGATIVE_INFINITY;
        }

        return resultSin / resultCos;
    }
}
