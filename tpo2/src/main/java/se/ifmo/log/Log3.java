package se.ifmo.log;

public class Log3 extends LogFunction{
    private final Ln ln;
    private static final Double ln3 = 1.09861228867;
    public Log3(Ln ln) {
        this.ln = ln;
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        return ln.calculateValue(x,eps)/ ln3;
    }
}
