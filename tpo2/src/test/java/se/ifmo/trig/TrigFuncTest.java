package se.ifmo.trig;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import se.ifmo.utils.CsvLogger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrigFuncTest {

    private final Sin sin = new Sin();
    private final Cos cos = new Cos();
    private final Tan tan = new Tan();
    private final Cot cot = new Cot();
    private final Sec sec = new Sec();
    private final Csc csc = new Csc();
    private final CsvLogger csvLogger = new CsvLogger();
    private final TrigonometricCalculator trigonometricCalculator= new TrigonometricCalculator(sin,cos,tan,cot,sec,csc);
    private final double accuracy = 0.1;
    private final double eps = 0.001;

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/sinData.csv")
    @DisplayName("sin(x) test")
    void sinTest(Double divisible, Double divider, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/sin.csv");
            double x = divisible * Math.PI / divider;
            double result = sin.calculate(x,eps);
            csvLogger.logger(x,result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/cosData.csv")
    @DisplayName("cos(x) test")
    void cosTest(Double divisible, Double divider, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/cos.csv");
            double x = divisible * Math.PI / divider;
            double result = cos.calculate(x,eps);
            csvLogger.logger(x,result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/tanData.csv")
    @DisplayName("tan(x) test")
    void tanTest(Double divisible, Double divider, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/tan.csv");
            double x = divisible * Math.PI / divider;
            double result = tan.calculate(x,eps);
            csvLogger.logger(x,result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/cotData.csv")
    @DisplayName("cot(x) test")
    void cotTest(Double divisible, Double divider, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/cot.csv");
            double x = divisible * Math.PI / divider;
            double result = cot.calculate(x,eps);
            csvLogger.logger(x,result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/secData.csv")
    @DisplayName("sec(x) test")
    void secTest(Double divisible, Double divider, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/sec.csv");
            double x = divisible * Math.PI / divider;
            double result = sec.calculate(x,eps);
            csvLogger.logger(x,result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/cscData.csv")
    @DisplayName("csc(x) test")
    void cscTest(Double divisible, Double divider, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/csc.csv");
            double x = divisible * Math.PI / divider;
            double result = csc.calculate(x,eps);
            csvLogger.logger(x,result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test")
    void trigFuncTest(Double divisible, Double divider, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/trigFunc.csv");
            double x = divisible * Math.PI / divider;
            double result = csc.calculate(x,eps);
            csvLogger.logger(x,result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }
}
