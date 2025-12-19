import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorBotTest {

    private List<CalculatorTestData> loadTests() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream("calculator_tests.json");
        return mapper.readValue(is, new TypeReference<List<CalculatorTestData>>() {});
    }

    private void runOnly(String op) throws Exception {
        List<CalculatorTestData> tests = loadTests().stream()
                .filter(t -> t.operation.equalsIgnoreCase(op))
                .collect(Collectors.toList());

        for (CalculatorTestData t : tests) {
            double result = CalculatorBot.calculate(t.a, t.b, t.operation);
            assertEquals(t.expected, result, 0.0001, "Failed: " + t.testName);
        }
    }

    @Test void grade_add() throws Exception { runOnly("add"); } // e.g. 25%
    @Test void grade_sub() throws Exception { runOnly("sub"); } // e.g. 25%
    @Test void grade_mul() throws Exception { runOnly("mul"); } // e.g. 25%
    @Test void grade_div() throws Exception { runOnly("div"); } // e.g. 25%
}
