import java.math.BigDecimal;
import java.math.RoundingMode;

public class BasicCalculator implements CalculatorOperations {
    private static final int SCALE = 20;

    @Override
    public BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    @Override
    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    @Override
    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    @Override
    public BigDecimal divide(BigDecimal a, BigDecimal b) throws ArithmeticException {
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return a.divide(b, SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public boolean isValidOperator(char operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/';
    }
}