package org.example;

import java.util.Scanner;

public class Main {
    // działania +-*/; cyfry 0-9
    public static void main(String[] args) {

        while (true) {
            Input input = new Input(System.in, System.out);
            input.start();

            ConversionToRPN conversionToRPN = new ConversionToRPN(input);
            conversionToRPN.convert();

            Calculator calculator = new Calculator(conversionToRPN, conversionToRPN.rpn);
            calculator.count(calculator.elements);
            calculator.print();

            Scanner scan1 = new Scanner(System.in);
            System.out.println("Exit? Y-> yes; N-> no");
            String question = scan1.nextLine();
            if (question.equals("y")) {
                break;
            }
            System.out.println("Ok! Let's carry on!");
        }
        System.out.println("Bye!");
    }
}