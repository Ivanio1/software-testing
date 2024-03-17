package se.ifmo.trig;

import static java.lang.Math.PI;

public class Csc extends TrigFunction {

    private final Sin sin;

    public Csc() {
        this.sin = new Sin();
    }

    public Csc(Sin sin) {
        this.sin = sin;
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        x = x % (2 * PI);

        double resultSin = sin.calculateValue(x, eps);

        return 1.0 / resultSin;
    }
}
