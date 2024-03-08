package se.ifmo.log;

public class LogarithmicCalculator {

    private final Ln ln;
    private final Log2 log2;
    private final Log3 log3;
    private final Log5 log5;
    private final Log10 log10;

    public LogarithmicCalculator(Ln ln, Log2 log2, Log3 log3, Log5 log5, Log10 log10) {
        this.ln = ln;
        this.log2 = log2;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    public LogarithmicCalculator() {
        this.ln = new Ln();
        this.log2 = new Log2(ln);
        this.log3 = new Log3(ln);
        this.log5 = new Log5(ln);
        this.log10 = new Log10(ln);
    }

    public Double calculate(Double x, Double eps) {
        double lnResult = ln.calculate(x, eps);
        double log2Result = log2.calculate(x, eps);
        double log3Result = log3.calculate(x, eps);
        double log5Result = log5.calculate(x, eps);
        double log10Result = log10.calculate(x, eps);

        double result = (((((log5Result / log2Result) / log3Result) - (lnResult + (log5Result - log3Result))) * (log10Result / log5Result)) * (Math.pow(log5Result, 3) + lnResult));
        if (Double.isNaN(result)) throw new IllegalArgumentException("ODZ exception!");
        return result;
    }
}
