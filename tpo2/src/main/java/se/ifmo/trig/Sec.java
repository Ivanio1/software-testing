package se.ifmo.trig;

import static java.lang.Math.PI;

public class Sec extends TrigFunction {

    private final Cos cos;

    public Sec() {
        this.cos = new Cos();
    }

    public Sec(Cos cos) {
        this.cos = cos;
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        x = x % (2 * PI);

        double resultCos = cos.calculateValue(x, eps);
        return 1.0 / resultCos;
    }
}
