import java.math.BigDecimal;

public interface CalculatorOperations
{
    BigDecimal add(BigDecimal a, BigDecimal b);
    BigDecimal subtract(BigDecimal a, BigDecimal b);
    BigDecimal multiply(BigDecimal a, BigDecimal b);
    BigDecimal divide(BigDecimal a, BigDecimal b) throws ArithmeticException;
    boolean isValidOperator(char operator);
}