package org.example;

import java.util.Scanner;

public class Main {
    // działania +-*/; cyfry 0-9
    public static void main(String[] args) {

        ConversionToRPN conversionToRPN = new ConversionToRPN();
        while (true) {
            Input input = new Input(System.in, System.out);
            String expression = input.start();

            if (expression != null && !expression.isEmpty()) {

                String rpn = conversionToRPN.convert(expression);

                Calculator calculator = new Calculator( rpn);
                calculator.count();
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