package se.ifmo.trig;

import static java.lang.Math.PI;

public class Sin extends TrigFunction {

    @Override
    public Double calculateValue(Double x, Double eps) {
        x = x % (2 * PI);
        if ((Double.compare(x, 0D) == 0 || Double.compare(x, PI) == 0) || Double.compare(x, -PI) == 0) {
            return 0.0;
        }
        int n = 1;
        double resultSin = 0;
        double xx = x * x;
        double pow = x;
        double fact = 1;
        int sign = 1;
        double term = sign * pow / fact;

        while (Math.abs(term) > eps) {
            resultSin += term;

            sign = -sign;
            fact *= (n + 1) * (n + 2);
            pow *= xx;
            n += 2;
            term = sign * pow / fact;
        }
        return resultSin;
    }
}