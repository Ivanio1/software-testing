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

    public TrigonometricCalculator() {
        this.sin = new Sin();
        this.cos = new Cos();
        this.tan = new Tan();
        this.cot = new Cot();
        this.sec = new Sec();
        this.csc = new Csc();
    }

    public Double calculate(Double x, Double eps) {
        double sinResult = sin.calculate(x, eps);
        double cosResult = cos.calculate(x, eps);
        double tanResult = tan.calculate(x, eps);
        double cotResult = cot.calculate(x, eps);
        double secResult = sec.calculate(x, eps);
        double cscResult = csc.calculate(x, eps);

        double result = Math.pow(((((((Math.pow(((Math.pow(Math.pow(Math.pow((((cscResult - secResult) / cscResult) / cosResult) * cotResult, 2), 2) / (sinResult * (Math.pow(secResult, 2))), 2) * (cotResult + cscResult)) / tanResult), 3)) - tanResult) / ((cotResult * (cscResult + tanResult)) * secResult)) - (cosResult + (secResult - (sinResult * cscResult)))) * sinResult) - ((cotResult * cscResult) / cscResult)), 3) + ((Math.pow(cosResult, 2)) * Math.pow(((cscResult - tanResult) / ((cscResult * secResult) - (Math.pow(cotResult, 2)))), 3));
        if (Double.isNaN(result)) throw new IllegalArgumentException("ODZ exception!");
        return result;
    }
}
