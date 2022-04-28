package org.example;

import java.util.Scanner;

public class Main {
    // działania +-*/; cyfry 0-9
    public static void main(String[] args) {

        while (true) {
            Input input = new Input(System.in, System.out);
            input.start();

            String expression = input.getExpression();
            if (expression != null && !expression.isEmpty()) {
                ConversionToRPN conversionToRPN = new ConversionToRPN(input);
                conversionToRPN.convert();

                Calculator calculator = new Calculator(conversionToRPN, conversionToRPN.rpn);
                calculator.count(calculator.elements);
                calculator.print();
            } else {
                System.out.println("Input should not be empty. Try again and enter an expression.");
                continue;
            }

            System.out.println("Exit? Y-> yes; N-> no");
            String question = new Scanner(System.in).nextLine();
            if (question.equals("y")) {
                break;
            }
            System.out.println("Ok! Let's carry on!");
        }
        System.out.println("Bye!");
    }
}