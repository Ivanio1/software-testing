package se.ifmo.log;

public class Log10 extends LogFunction {
    private final Ln ln;
    private static final Double ln10 = 2.30258509299;

    public Log10(Ln ln) {
        this.ln = ln;
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        return ln.calculateValue(x, eps) / ln10;
    }
}
