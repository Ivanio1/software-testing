package se.ifmo.log;

public class Log5 extends LogFunction {
    private final Ln ln;
    private static final Double ln5 = 1.60943791243;

    public Log5(Ln ln) {
        this.ln = ln;
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        return ln.calculateValue(x, eps) / ln5;
    }
}
