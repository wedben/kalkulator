import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CalculatorUI {
    private final CalculatorOperations calculator;
    private final Scanner scanner;
    private final DecimalFormat df;

    public CalculatorUI(CalculatorOperations calculator) {
        this.calculator = calculator;
        this.scanner = new Scanner(System.in);
        this.df = new DecimalFormat("#.####################");
    }

    public void run() {
        System.out.println("Калькулятор запущен (для выхода введите 'q')");

        BigDecimal result = readNumber("Введите первое число:");

        while (true) {
            String input = readInput("Введите оператор (+, -, *, /) или 'q' для выхода:");

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Завершение работы калькулятора.");
                break;
            }

            if (input.length() != 1 || !calculator.isValidOperator(input.charAt(0))) {
                System.out.println("Ошибка: неверный оператор!");
                continue;
            }

            char operator = input.charAt(0);
            BigDecimal num = readNumber("Введите следующее число:");

            try {
                result = performOperation(result, num, operator);
                printResult(result);
            } catch (ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private BigDecimal performOperation(BigDecimal a, BigDecimal b, char operator) {
        switch (operator) {
            case '+': return calculator.add(a, b);
            case '-': return calculator.subtract(a, b);
            case '*': return calculator.multiply(a, b);
            case '/': return calculator.divide(a, b);
            default: throw new IllegalArgumentException("Неизвестный оператор");
        }
    }

    private void printResult(BigDecimal result) {
        System.out.println("Текущий результат: \u001B[32m" + df.format(result) + "\u001B[0m");
    }

    private BigDecimal readNumber(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                String input = scanner.next().replace(',', '.');
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число!");
            }
        }
    }

    private String readInput(String prompt) {
        System.out.println(prompt);
        return scanner.next();
    }
}