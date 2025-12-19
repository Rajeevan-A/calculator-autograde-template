import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorBotTest {

    @Test
    void runJsonBasedTests() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("calculator_tests.json");

        List<CalculatorTestData> tests =
                mapper.readValue(is,
                        new TypeReference<List<CalculatorTestData>>() {});

        for (CalculatorTestData t : tests) {
            double result =
                    CalculatorBot.calculate(t.a, t.b, t.operation);

            assertEquals(t.expected, result, 0.0001,
                    "Failed: " + t.testName);
        }
    }
}
