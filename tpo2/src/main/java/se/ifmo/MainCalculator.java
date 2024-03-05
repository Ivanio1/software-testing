package se.ifmo;

import se.ifmo.log.LogarithmicCalculator;
import se.ifmo.trig.TrigonometricCalculator;

public class MainCalculator {

    private final LogarithmicCalculator logCalculator;
    private final TrigonometricCalculator trigCalculator;

    public MainCalculator(LogarithmicCalculator logCalculator, TrigonometricCalculator trigCalculator) {
        this.logCalculator = logCalculator;
        this.trigCalculator = trigCalculator;
    }

    public Double calculate(Double x, Double eps) {
        if (x > 0) {
            return logCalculator.calculate(x, eps);
        } else {
            return trigCalculator.calculate(x, eps);
        }
    }

}
