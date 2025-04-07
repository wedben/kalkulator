import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class kalkulator {
    private final Scanner scanner;
    private final DecimalFormat df;

    public kalkulator() {
        this.scanner = new Scanner(System.in);
        this.df = new DecimalFormat("#.####################");
    }

    public void run() {
        System.out.println("Введите первое число:");
        BigDecimal result = readBigDecimal();

        while (true) {
            System.out.println("Введите оператор (+, -, *, /) или 'q' для выхода:");
            String input = scanner.next();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Завершение работы калькулятора.");
                break;
            }

            if (input.length() != 1 || !isValidOperator(input.charAt(0))) {
                System.out.println("Ошибка: неверный оператор! Пожалуйста, введите один из символов: +, -, *, /");
                continue;
            }

            char operator = input.charAt(0);

            System.out.println("Введите следующее число:");
            BigDecimal num = readBigDecimal();

            try {
                switch (operator) {
                    case '+':
                        result = result.add(num);
                        break;
                    case '-':
                        result = result.subtract(num);
                        break;
                    case '*':
                        result = result.multiply(num);
                        break;
                    case '/':
                        if (num.compareTo(BigDecimal.ZERO) != 0) {
                            result = result.divide(num, 20, RoundingMode.HALF_UP);
                        } else {
                            System.out.println("Ошибка: деление на ноль!");
                            continue;
                        }
                        break;
                }
            } catch (ArithmeticException e) {
                System.out.println("Ошибка при вычислении: " + e.getMessage());
                continue;
            }

            System.out.println("Текущий результат: \u001B[32m" + df.format(result) + "\u001B[0m");
        }

        scanner.close();
    }

    private boolean isValidOperator(char operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/';
    }

    private BigDecimal readBigDecimal() {
        while (true) {
            try {
                String input = scanner.next();
                input = input.replace(',', '.');
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено некорректное число. Пожалуйста, используйте точку или запятую как разделитель (например, 5.5 или 3,2):");
            }
        }
    }
}