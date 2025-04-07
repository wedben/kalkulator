public class main {
    public static void main(String[] args) {
        CalculatorOperations calculator = new BasicCalculator();
        CalculatorUI ui = new CalculatorUI(calculator);
        ui.run();
    }
}