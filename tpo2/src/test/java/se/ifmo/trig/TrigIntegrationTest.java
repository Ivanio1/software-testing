package se.ifmo.trig;

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

public class TrigIntegrationTest {

    public static Sin sin = mock(Sin.class);
    public static Cos cos = mock(Cos.class);
    public static Tan tan = mock(Tan.class);
    public static Cot cot = mock(Cot.class);
    public static Sec sec = mock(Sec.class);
    public static Csc csc = mock(Csc.class);
    private static final double eps = 0.0000001;
    private final double accuracy = 0.01;

    @BeforeAll
    public static void setup(){
        fillMock(sin, "src/test/resources/inputTrig/sinData.csv");
        fillMock(cos, "src/test/resources/inputTrig/cosData.csv");
        fillMock(tan, "src/test/resources/inputTrig/tanData.csv");
        fillMock(cot, "src/test/resources/inputTrig/cotData.csv");
        fillMock(sec, "src/test/resources/inputTrig/secData.csv");
        fillMock(csc, "src/test/resources/inputTrig/cscData.csv");
    }

    private static void fillMock(TrigFunction tf, String tableName){
        try (CSVReader csvReader = new CSVReader(new FileReader(tableName))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.calculate(x, TrigIntegrationTest.eps)).thenReturn(y);
            }
        } catch (IOException | CsvException ignored) {

        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test with full mocks")
    void trigFuncTest(Double divisible, Double divider, Double trueResult) {
        try {
            TrigonometricCalculator trigonometricCalculator = new TrigonometricCalculator(sin, cos, tan, cot, sec, csc);
            double x = divisible * Math.PI / divider;
            double result = trigonometricCalculator.calculate(x, eps);
            assertEquals(trueResult, result,  accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test with sin")
    void trigFuncTestWithSin(Double divisible, Double divider, Double trueResult) {
        try {
            TrigonometricCalculator trigonometricCalculator = new TrigonometricCalculator(new Sin(), cos, tan, cot, sec, csc);
            double x = divisible * Math.PI / divider;
            double result = trigonometricCalculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test with cos")
    void trigFuncTestWithCos(Double divisible, Double divider, Double trueResult) {
        try {
            TrigonometricCalculator trigonometricCalculator = new TrigonometricCalculator(new Sin(), new Cos(sin), tan, cot, sec, csc);
            double x = divisible * Math.PI / divider;
            double result = trigonometricCalculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test with cos deeper")
    void trigFuncTestWithCosDeeper(Double divisible, Double divider, Double trueResult) {
        try {
            TrigonometricCalculator trigonometricCalculator = new TrigonometricCalculator(new Sin(), new Cos(new Sin()), tan, cot, sec, csc);
            double x = divisible * Math.PI / divider;
            double result = trigonometricCalculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test with tan")
    void trigFuncTestWithTan(Double divisible, Double divider, Double trueResult) {
        try {
            TrigonometricCalculator trigonometricCalculator = new TrigonometricCalculator(new Sin(), new Cos(sin), new Tan(sin, cos), cot, sec, csc);
            double x = divisible * Math.PI / divider;
            double result = trigonometricCalculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test with tan deeper")
    void trigFuncTestWithTanDeeper(Double divisible, Double divider, Double trueResult) {
        try {
            TrigonometricCalculator trigonometricCalculator = new TrigonometricCalculator(new Sin(), new Cos(new Sin()), new Tan(new Sin(), new Cos()), cot, sec, csc);
            double x = divisible * Math.PI / divider;
            double result = trigonometricCalculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test with cot")
    void trigFuncTestWithCot(Double divisible, Double divider, Double trueResult) {
        try {
            TrigonometricCalculator trigonometricCalculator = new TrigonometricCalculator(new Sin(), new Cos(sin), new Tan(sin, cos), new Cot(sin, cos), sec, csc);
            double x = divisible * Math.PI / divider;
            double result = trigonometricCalculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test with cot deeper")
    void trigFuncTestWithCotDeeper(Double divisible, Double divider, Double trueResult) {
        try {
            TrigonometricCalculator trigonometricCalculator = new TrigonometricCalculator(new Sin(), new Cos(new Sin()), new Tan(new Sin(), new Cos()), new Cot(new Sin(), new Cos()), sec, csc);
            double x = divisible * Math.PI / divider;
            double result = trigonometricCalculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test with sec")
    void trigFuncTestWithSec(Double divisible, Double divider, Double trueResult) {
        try {
            TrigonometricCalculator trigonometricCalculator = new TrigonometricCalculator(new Sin(), new Cos(sin), new Tan(sin, cos), new Cot(sin, cos), new Sec(cos), csc);
            double x = divisible * Math.PI / divider;
            double result = trigonometricCalculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test with sec deeper")
    void trigFuncTestWithSecDeeper(Double divisible, Double divider, Double trueResult) {
        try {
            TrigonometricCalculator trigonometricCalculator = new TrigonometricCalculator(new Sin(), new Cos(new Sin()), new Tan(new Sin(), new Cos()), new Cot(new Sin(), new Cos()), new Sec(new Cos()), csc);
            double x = divisible * Math.PI / divider;
            double result = trigonometricCalculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test with csc")
    void trigFuncTestWithCsc(Double divisible, Double divider, Double trueResult) {
        try {
            TrigonometricCalculator trigonometricCalculator = new TrigonometricCalculator(new Sin(), new Cos(sin), new Tan(sin, cos), new Cot(sin, cos), new Sec(cos), new Csc(sin));
            double x = divisible * Math.PI / divider;
            double result = trigonometricCalculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/trigFuncData.csv")
    @DisplayName("trigonometric function test with csc deeper")
    void trigFuncTestWithCscDeeper(Double divisible, Double divider, Double trueResult) {
        try {
            TrigonometricCalculator trigonometricCalculator = new TrigonometricCalculator(new Sin(), new Cos(new Sin()), new Tan(new Sin(), new Cos()), new Cot(new Sin(), new Cos()), new Sec(new Cos()), new Csc(new Sin()));
            double x = divisible * Math.PI / divider;
            double result = trigonometricCalculator.calculate(x, eps);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        } catch (IllegalArgumentException e) {
            assertEquals("ODZ exception!", e.getMessage());
        }
    }
}
