package se.ifmo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import se.ifmo.log.*;
import se.ifmo.trig.*;
import se.ifmo.utils.CsvLogger;


import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DisplayName("CalculatorTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SystemIntegrationTest {
    private final double accuracy = 0.1;
    private final double eps = 0.0000001;
    private final CsvLogger csvLogger = new CsvLogger();
    public TrigonometricFunctionCalculator trigCalculator = mock(TrigonometricFunctionCalculator.class);
    public LogarithmicFunctionCalculator logCalculator = mock(LogarithmicFunctionCalculator.class);

    @BeforeAll
    public void setup() {
        fillMock(logCalculator);
        fillMock(trigCalculator);
    }

    private void fillMock(TrigonometricFunctionCalculator tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/inputTrig/trigFuncData.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.checkAndCalculate(x, eps)).thenReturn(y);
            }
        } catch (IOException | CsvException ignored) {
        }
    }

    private void fillMock(LogarithmicFunctionCalculator tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/inputLog/logFuncData.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.checkAndCalculate(x, eps)).thenReturn(y);
            }
        } catch (IOException | CsvException ignored) {
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calcData.csv")
    @DisplayName("allMock")
    void allMock(Double x, Double trueResult) {
        try {
            LogIntegrationTest.setup();
            TrigIntegrationTest.setup();
            MainCalculator calculator = new MainCalculator(logCalculator, trigCalculator);
            double result = calculator.checkAndCalculate(x, eps);
            double delta;
            String numberStr = String.valueOf(result);
            if (numberStr.contains("E")) {
                delta = result < 0 ? result * -1 * eps : result * eps;
            }
            else{
                delta = accuracy;
            }
            assertEquals(trueResult, result, delta);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calcData.csv")
    @DisplayName("trigMock")
    void trigMock(Double x, Double trueResult) {
        try {
            LogarithmicFunctionCalculator logCalculator = new LogarithmicFunctionCalculator();
            MainCalculator calculator = new MainCalculator(logCalculator, trigCalculator);
            double result = calculator.checkAndCalculate(x, eps);
            double delta;
            String numberStr = String.valueOf(result);
            if (numberStr.contains("E")) {
                delta = result < 0 ? result * -1 * eps : result * eps;
            }
            else{
                delta = accuracy;
            }
            assertEquals(trueResult, result, delta);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calcData.csv")
    @DisplayName("logMock")
    void logMock(Double x, Double trueResult) {
        try {
            var trigCalculator = new TrigonometricFunctionCalculator();
            MainCalculator calculator = new MainCalculator(logCalculator, trigCalculator);
            double result = calculator.checkAndCalculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calcData.csv")
    @DisplayName("noMock")
    void noMock(Double x, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/mainFunc.csv");
            var trigCalculator = new TrigonometricFunctionCalculator();
            var logCalculator = new LogarithmicFunctionCalculator();
            MainCalculator calculator = new MainCalculator(logCalculator, trigCalculator);
            double result = calculator.checkAndCalculate(x, eps);
            csvLogger.logger(x, result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }
}
