package se.ifmo.log;

public class LogarithmicCalculator {

    private final Ln ln;
    private final Log3 log3;
    private final Log5 log5;
    private final Log10 log10;

    public LogarithmicCalculator(Ln ln, Log3 log3, Log5 log5, Log10 log10) {
        this.ln = ln;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    public Double calculate(Double x, Double eps) {
        return 0.0;
    }
}
