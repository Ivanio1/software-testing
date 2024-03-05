package se.ifmo.log;

public class Log2 extends LogFunction {
    private final Ln ln;

    public Log2(Ln ln) {
        this.ln = ln;
    }

    @Override
    public Double calculate(Double x, Double eps) {
        x = checkX(x);

        return ln.calculate(x, eps) / 0.69314718055;
    }
}
