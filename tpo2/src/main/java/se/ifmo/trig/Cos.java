package se.ifmo.trig;

import static java.lang.Math.*;

public class Cos extends TrigFunction {

    private final Sin sin;

    public Cos() {
        this.sin = new Sin();
    }

    @Override
    public Double calculate(Double x, Double eps) {
        x = checkX(x);

        if (x == -PI / 2 || x == -3 * PI / 2) {
            return 0.0;
        }
        double resultSin = sin.calculate(x,eps);
        double resultCos = sqrt(1 - pow(resultSin, 2));

        if (x < -PI / 2 && x > -3 * PI / 2) {
            resultCos *= -1;
        }
        return resultCos;
    }
}