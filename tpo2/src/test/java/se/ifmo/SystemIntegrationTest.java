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


import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DisplayName("CalculatorTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SystemIntegrationTest {
    private final double accuracy = 0.01;
    private final double eps = 0.0000001;

    public TrigonometricCalculator trigCalculator = mock(TrigonometricCalculator.class);
    public  LogarithmicCalculator logCalculator = mock(LogarithmicCalculator.class);

    @BeforeAll
    public void setup() {
        fillMock(logCalculator);
        fillMock(trigCalculator);
    }

    private void fillMock(TrigonometricCalculator tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/inputTrig/trigFuncData.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double divisible = Double.parseDouble(record[0]);
                final double divider = Double.parseDouble(record[1]);
                final double y = Double.parseDouble(record[2]);
                double x = divisible * Math.PI / divider;
                when(tf.calculate(x, eps)).thenReturn(y);
            }
        } catch (IOException | CsvException ignored) {
        }
    }

    private void fillMock(LogarithmicCalculator tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/inputLog/logFuncData.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.calculate(x, eps)).thenReturn(y);
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
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
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
            LogarithmicCalculator logCalculator = new LogarithmicCalculator();
            MainCalculator calculator = new MainCalculator(logCalculator, trigCalculator);
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
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
            var trigCalculator = new TrigonometricCalculator();
            MainCalculator calculator = new MainCalculator(logCalculator, trigCalculator);
            double result = calculator.calculate(x, eps);
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
            var trigCalculator = new TrigonometricCalculator();
            var logCalculator = new LogarithmicCalculator();
            MainCalculator calculator = new MainCalculator(logCalculator, trigCalculator);
            double result = calculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }
}
