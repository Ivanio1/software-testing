package se.ifmo.log;

public class Log5 extends LogFunction{
    private final Ln ln;
    public Log5(Ln ln) {
        this.ln = ln;
    }

    @Override
    public Double calculate(Double x, Double eps) {
        x = checkX(x);

        return ln.calculate(x,eps)/ 1.60943791243;
    }
}
