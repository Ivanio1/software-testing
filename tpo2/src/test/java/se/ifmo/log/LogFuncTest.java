package se.ifmo.log;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import se.ifmo.utils.CsvLogger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogFuncTest {

    private final Ln ln = new Ln();
    private final Log2 log2 = new Log2(ln);
    private final Log3 log3 = new Log3(ln);
    private final Log5 log5 = new Log5(ln);
    private final Log10 log10 = new Log10(ln);
    private final CsvLogger csvLogger = new CsvLogger();
    private final LogarithmicCalculator logarithmicCalculator = new LogarithmicCalculator(ln, log2, log3, log5, log10);
    private final double accuracy = 0.01;
    private final double eps = 0.0001;


    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/lnData.csv")
    @DisplayName("ln(x) test")
    void lnTest(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/ln.csv");
            double result = ln.calculate(x, eps);
            csvLogger.logger(x, result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/log3Data.csv")
    @DisplayName("log3(x) test")
    void log3Test(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/log3.csv");
            double result = log3.calculate(x, eps);
            csvLogger.logger(x, result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/log2Data.csv")
    @DisplayName("log2(x) test")
    void log2Test(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/log2.csv");
            double result = log2.calculate(x, eps);
            csvLogger.logger(x, result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/log5Data.csv")
    @DisplayName("log5(x) test")
    void log5Test(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/log5.csv");
            double result = log5.calculate(x, eps);
            csvLogger.logger(x, result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/log10Data.csv")
    @DisplayName("log10(x) test")
    void log10Test(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/log10.csv");
            double result = log10.calculate(x, eps);
            csvLogger.logger(x, result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function test")
    void logFuncTest(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/logFunc.csv");
            double result = logarithmicCalculator.calculate(x, eps);
            csvLogger.logger(x, result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        }catch (IllegalArgumentException e){
            assertEquals("Division by zero!", e.getMessage());

        }

    }

}
