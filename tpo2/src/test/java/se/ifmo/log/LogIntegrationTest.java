package se.ifmo.log;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LogIntegrationTest {

    private static final Ln ln = mock(Ln.class);
    private static final Log2 log2 = mock(Log2.class);
    private static final Log3 log3 = mock(Log3.class);
    private static final Log5 log5 = mock(Log5.class);
    private static final Log10 log10 = mock(Log10.class);
    private static final double eps = 0.0000001;
    private final double accuracy = 0.1;


    @BeforeAll
    public static void setup() {
        fillMock(ln, "src/test/resources/inputLog/lnData.csv");
        fillMock(log2, "src/test/resources/inputLog/log2Data.csv");
        fillMock(log3, "src/test/resources/inputLog/log3Data.csv");
        fillMock(log5, "src/test/resources/inputLog/log5Data.csv");
        fillMock(log10, "src/test/resources/inputLog/log10Data.csv");
    }

    private static void fillMock(LogFunction tf, String tableName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(tableName))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.calculate(x, LogIntegrationTest.eps)).thenReturn(y);
            }
        } catch (IOException | CsvException ignored) {
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function test with full mocks")
    void testFunctionWithMocks(Double x, Double trueResult) {
        try {
            LogarithmicCalculator calculator = new LogarithmicCalculator(ln, log2, log3, log5, log10);
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function test with ln")
    void testFunctionWithLn(Double x, Double trueResult) {
        try {
            LogarithmicCalculator calculator = new LogarithmicCalculator(new Ln(), log2, log3, log5, log10);
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function test with log2")
    void testFunctionWithLog2(Double x, Double trueResult) {
        try {
            LogarithmicCalculator calculator = new LogarithmicCalculator(new Ln(), new Log2(ln), log3, log5, log10);
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function test with log2 deeper")
    void testFunctionWithLog2Deeper(Double x, Double trueResult) {
        try {
            LogarithmicCalculator calculator = new LogarithmicCalculator(new Ln(), new Log2(new Ln()), log3, log5, log10);
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function test with log3")
    void testFunctionWithLog3(Double x, Double trueResult) {
        try {
            LogarithmicCalculator calculator = new LogarithmicCalculator(new Ln(), new Log2(new Ln()), new Log3(ln), log5, log10);
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function test with log3 deeper")
    void testFunctionWithLog3Deeper(Double x, Double trueResult) {
        try {
            LogarithmicCalculator calculator = new LogarithmicCalculator(new Ln(), new Log2(new Ln()), new Log3(new Ln()), log5, log10);
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function test with log5")
    void testFunctionWithLog5(Double x, Double trueResult) {
        try {
            LogarithmicCalculator calculator = new LogarithmicCalculator(new Ln(), new Log2(new Ln()), new Log3(ln), new Log5(ln), log10);
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function test with log5 deeper")
    void testFunctionWithLog5Deeper(Double x, Double trueResult) {
        try {
            LogarithmicCalculator calculator = new LogarithmicCalculator(new Ln(), new Log2(new Ln()), new Log3(new Ln()), new Log5(new Ln()), log10);
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function test with log10")
    void testFunctionWithLog10(Double x, Double trueResult) {
        try {
            LogarithmicCalculator calculator = new LogarithmicCalculator(new Ln(), new Log2(new Ln()), new Log3(ln), new Log5(new Ln()), new Log10(ln));
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/logFuncData.csv")
    @DisplayName("logarithmic function test with log10 deeper")
    void testFunctionWithLog10Deeper(Double x, Double trueResult) {
        try {
            LogarithmicCalculator calculator = new LogarithmicCalculator(new Ln(), new Log2(new Ln()), new Log3(new Ln()), new Log5(new Ln()), new Log10(new Ln()));
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be > 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }
}
