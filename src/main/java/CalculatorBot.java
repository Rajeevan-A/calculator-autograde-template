public class CalculatorBot {
// Note ##
    public static double calculate(double a, double b, String operation) {

        switch (operation.toLowerCase()) {
            case "add":
                return a + b;
            case "sub":
                return a - b;
            case "mul":
                return a * b;
            case "div":
                if (b == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }
    }
}
