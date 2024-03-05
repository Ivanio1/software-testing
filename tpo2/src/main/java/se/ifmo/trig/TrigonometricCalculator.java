package se.ifmo.trig;

public class TrigonometricCalculator {
    private final Sin sin;
    private final Cos cos;
    private final Tan tan;
    private final Cot cot;
    private final Sec sec;
    private final Csc csc;

    public TrigonometricCalculator(Sin sin, Cos cos, Tan tan, Cot cot, Sec sec, Csc csc) {
        this.sin = sin;
        this.cos = cos;
        this.tan = tan;
        this.cot = cot;
        this.sec = sec;
        this.csc = csc;
    }

    public Double calculate(Double x, Double eps) {
        double sinResult = sin.calculate(x,eps);
        double cosResult = cos.calculate(x,eps);
        double tanResult = tan.calculate(x,eps);
        double cotResult = cot.calculate(x,eps);
        double secResult = sec.calculate(x,eps);
        double cscResult = csc.calculate(x,eps);

        return 0.0;
    }
}
