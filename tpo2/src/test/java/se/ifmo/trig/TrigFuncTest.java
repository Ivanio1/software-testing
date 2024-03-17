package se.ifmo.trig;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import se.ifmo.utils.CsvLogger;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrigFuncTest {

    private final Sin sin = new Sin();
    private final Cos cos = new Cos();
    private final Tan tan = new Tan();
    private final Cot cot = new Cot();
    private final Sec sec = new Sec();
    private final Csc csc = new Csc();
    private final CsvLogger csvLogger = new CsvLogger();
    private final TrigonometricFunctionCalculator trigonometricFunctionCalculator = new TrigonometricFunctionCalculator(sin, cos, tan, cot, sec, csc);
    private final double accuracy = 0.1;
    private final double eps = 0.0000001;

    @BeforeAll
    public void clearFiles() {
        String directoryPath = "src/test/resources/results/trig/";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            csvLogger.setFilePath(String.valueOf(file));
            csvLogger.clearFile();
        }
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/sinData.csv")
    @DisplayName("sin(x) test")
    void sinTest(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/trig/sin.csv");
            double result = sin.checkAndCalculate(x, eps);
            csvLogger.logger(x, result);
            double delta = result < 0 ? result * -1 * eps : result * eps;
            assertEquals(trueResult, result, delta);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/cosData.csv")
    @DisplayName("cos(x) test")
    void cosTest(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/trig/cos.csv");
            double result = cos.checkAndCalculate(x, eps);
            csvLogger.logger(x, result);
            double delta = result < 0 ? result * -1 * eps : result * eps;
            assertEquals(trueResult, result, delta);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/tanData.csv")
    @DisplayName("tan(x) test")
    void tanTest(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/trig/tan.csv");
            double result = tan.checkAndCalculate(x, eps);
            csvLogger.logger(x, result);
            double delta = result < 0 ? result * -1 * eps : result * eps;
            assertEquals(trueResult, result, delta);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/cotData.csv")
    @DisplayName("cot(x) test")
    void cotTest(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/trig/cot.csv");
            double result = cot.checkAndCalculate(x, eps);
            csvLogger.logger(x, result);
            double delta = result < 0 ? result * -1 * eps : result * eps;
            assertEquals(trueResult, result, delta);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/secData.csv")
    @DisplayName("sec(x) test")
    void secTest(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/trig/sec.csv");
            double result = sec.checkAndCalculate(x, eps);
            csvLogger.logger(x, result);
            double delta = result < 0 ? result * -1 * eps : result * eps;
            assertEquals(trueResult, result, delta);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/cscData.csv")
    @DisplayName("csc(x) test")
    void cscTest(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/trig/csc.csv");
            double result = csc.checkAndCalculate(x, eps);
            csvLogger.logger(x, result);
            double delta = result < 0 ? result * -1 * eps : result * eps;
            assertEquals(trueResult, result, delta);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test")
    void trigFuncTest(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/trig/trigFunc.csv");
            double result = trigonometricFunctionCalculator.checkAndCalculate(x, eps);
            csvLogger.logger(x, result);
            double delta = result < 0 ? result * -1 * eps : result * eps;
            assertEquals(trueResult, result, delta);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());

        }
    }
}
