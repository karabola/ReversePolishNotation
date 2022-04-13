package RPN;

public class Main {
// działania +-*/; cyfry 0-9
    public static void main(String[] args) {

        System.out.println("Enter an expression!");

        ConversionToRPN conversionToRPN = new ConversionToRPN();
        conversionToRPN.convert(conversionToRPN.expression);

        Calculator calculator = new Calculator(conversionToRPN, conversionToRPN.rpn);
        calculator.count(calculator.elements);
        calculator.print();

    }
}