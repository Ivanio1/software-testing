package se.ifmo.trig;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

public class Cot extends TrigFunction {

    private final Sin sin;
    private final Cos cos;

    public Cot() {
        this.sin = new Sin();
        this.cos = new Cos();
    }

    public Cot(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public Double calculate(Double x, Double eps) {
        x = checkX(x);
        double resultSin = sin.calculate(x,eps);
        double resultCos = cos.calculate(x,eps);

        if (resultSin == 0.0 && resultCos > 0) {
            return POSITIVE_INFINITY;
        } else if (resultSin == 0.0 && resultCos < 0) {
            return NEGATIVE_INFINITY;
        }

        return resultCos / resultSin;
    }
}