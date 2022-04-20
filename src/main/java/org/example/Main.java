package org.example;

public class Main {
    // działania +-*/; cyfry 0-9
    public static void main(String[] args) {
        Input input = new Input(System.in, System.out);
        input.start();
//        ConversionToRPN conversionToRPN = new ConversionToRPN(System.in, System.out);
        ConversionToRPN conversionToRPN = new ConversionToRPN(input);
//        conversionToRPN.start();
        conversionToRPN.convert();

        Calculator calculator = new Calculator(conversionToRPN, conversionToRPN.rpn);
        calculator.count(calculator.elements);
        calculator.print();


    }
}