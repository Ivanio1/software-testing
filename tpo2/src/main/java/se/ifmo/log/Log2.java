package se.ifmo.log;

public class Log2 extends LogFunction {
    private final Ln ln;
    private static final Double ln2 = 0.69314718055;
    public Log2(Ln ln) {
        this.ln = ln;
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        return ln.calculateValue(x, eps) / ln2;
    }
}
