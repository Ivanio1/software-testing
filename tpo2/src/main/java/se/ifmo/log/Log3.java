package se.ifmo.log;

public class Log3 extends LogFunction{
    private final Ln ln;
    public Log3(Ln ln) {
        this.ln = ln;
    }

    @Override
    public Double calculate(Double x, Double eps) {
        x = checkX(x);

        return ln.calculate(x,eps)/ 1.09861228867;
    }
}
