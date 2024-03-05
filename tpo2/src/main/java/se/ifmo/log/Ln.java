package se.ifmo.log;

public class Ln extends LogFunction {
    @Override
    public Double calculate(Double x, Double eps) {
        x = checkX(x);
        double result = 0.0;
        int i = 1;
        double term = Math.pow(x - 1, i) / i;

        while (Math.abs(term) > eps) {
            result += Math.pow(-1, i - 1) * term;
            i++;
            term = Math.pow(x - 1, i) / i;
        }

        return result;
    }
}