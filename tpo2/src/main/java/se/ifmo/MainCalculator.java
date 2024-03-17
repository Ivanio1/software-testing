package se.ifmo;

import se.ifmo.log.LogarithmicFunctionCalculator;
import se.ifmo.trig.TrigonometricFunctionCalculator;

public class MainCalculator extends AbstractFunction{

    private final LogarithmicFunctionCalculator logCalculator;
    private final TrigonometricFunctionCalculator trigCalculator;

    public MainCalculator(LogarithmicFunctionCalculator logCalculator, TrigonometricFunctionCalculator trigCalculator) {
        this.logCalculator = logCalculator;
        this.trigCalculator = trigCalculator;
    }

    @Override
    public Double calculateValue(Double x, Double eps) {
        if (x > 0) {
            return logCalculator.checkAndCalculate(x, eps);
        } else {
            return trigCalculator.checkAndCalculate(x, eps);
        }
    }

}
