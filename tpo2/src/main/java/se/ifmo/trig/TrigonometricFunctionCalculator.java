package se.ifmo.trig;

import java.text.DecimalFormat;

public class TrigonometricFunctionCalculator extends TrigFunction {
    private final Sin sin;
    private final Cos cos;
    private final Tan tan;
    private final Cot cot;
    private final Sec sec;
    private final Csc csc;

    public TrigonometricFunctionCalculator(Sin sin, Cos cos, Tan tan, Cot cot, Sec sec, Csc csc) {
        this.sin = sin;
        this.cos = cos;
        this.tan = tan;
        this.cot = cot;
        this.sec = sec;
        this.csc = csc;
    }

    public TrigonometricFunctionCalculator() {
        this.sin = new Sin();
        this.cos = new Cos();
        this.tan = new Tan();
        this.cot = new Cot();
        this.sec = new Sec();
        this.csc = new Csc();
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        double sinResult = sin.checkAndCalculate(x, eps);
        double cosResult = cos.checkAndCalculate(x, eps);
        double tanResult = tan.checkAndCalculate(x, eps);
        double cotResult = cot.checkAndCalculate(x, eps);
        double secResult = sec.checkAndCalculate(x, eps);
        double cscResult = csc.checkAndCalculate(x, eps);
        double result = Math.pow(((((((Math.pow(((Math.pow(Math.pow(Math.pow((((cscResult - secResult) / cscResult) / cosResult) * cotResult, 2), 2) / (sinResult * (Math.pow(secResult, 2))), 2) * (cotResult + cscResult)) / tanResult), 3)) - tanResult) / ((cotResult * (cscResult + tanResult)) * secResult)) - (cosResult + (secResult - (sinResult * cscResult)))) * sinResult) - ((cotResult * cscResult) / cscResult)), 3) + ((Math.pow(cosResult, 2)) * Math.pow(((cscResult - tanResult) / ((cscResult * secResult) - (Math.pow(cotResult, 2)))), 3));
        if (Double.isNaN(result)) throw new IllegalArgumentException("ODZ exception!");
        return result;
    }
}
