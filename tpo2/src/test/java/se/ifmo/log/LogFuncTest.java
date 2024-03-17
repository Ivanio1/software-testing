package se.ifmo.log;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import se.ifmo.utils.CsvLogger;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogFuncTest {

    private final Ln ln = new Ln();
    private final Log2 log2 = new Log2(ln);
    private final Log3 log3 = new Log3(ln);
    private final Log5 log5 = new Log5(ln);
    private final Log10 log10 = new Log10(ln);
    private final CsvLogger csvLogger = new CsvLogger();
    private final LogarithmicFunctionCalculator logarithmicFunctionCalculator = new LogarithmicFunctionCalculator(ln, log2, log3, log5, log10);
    private final double accuracy = 0.1;
    private final double eps = 0.0000001;

    @BeforeAll
    public void clearFiles() {
        String directoryPath = "src/test/resources/results/log/";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            csvLogger.setFilePath(String.valueOf(file));
            csvLogger.clearFile();
        }
    }


    @Test
    @DisplayName("checkX test")
    void checkTest() {
        try {
            ln.checkAndCalculate(Double.NaN, eps);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/lnData.csv")
    @DisplayName("ln(x) test")
    void lnTest(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/log/ln.csv");
            double result = ln.checkAndCalculate(x, eps);
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
            csvLogger.setFilePath("src/test/resources/results/log/log3.csv");
            double result = log3.checkAndCalculate(x, eps);
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
            csvLogger.setFilePath("src/test/resources/results/log/log2.csv");
            double result = log2.checkAndCalculate(x, eps);
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
            csvLogger.setFilePath("src/test/resources/results/log/log5.csv");
            double result = log5.checkAndCalculate(x, eps);
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
            csvLogger.setFilePath("src/test/resources/results/log/log10.csv");
            double result = log10.checkAndCalculate(x, eps);
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
            csvLogger.setFilePath("src/test/resources/results/log/logFunc.csv");
            double result = logarithmicFunctionCalculator.checkAndCalculate(x, eps);
            csvLogger.logger(x, result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }

    }

}
