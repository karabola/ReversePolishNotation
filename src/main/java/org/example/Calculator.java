package org.example;

import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator {

    public String[] elements;
    protected ConversionToRPN conversionToRPN;
    int result;
    Stack<Integer> stack = new Stack();
    private String rpn1;
    private Input input;

    public Calculator(ConversionToRPN conversionToRPN, String rpn) {
        this.conversionToRPN = conversionToRPN;
        this.rpn1 = rpn;
        elements = rpn1.split(" ");
    }

    public int count(String[] elements) {
        for (String element : elements) {
//            if(element.matches("-?\\d+(\\.\\d+)?")) {stack.add(Integer.parseInt(element));}

            if (element.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException(
                        "Incorrect argument (only numbers and operators can contain the exception)");
            }
            String operators = "+-*/";

            if (!operators.contains(element)) {
                stack.push(Integer.valueOf(element));
            } else {

                if (stack.isEmpty()) {
                    System.out.println("First argument must be a number! ");
                    throw new EmptyStackException();
                }
                int value1 = stack.pop();
                int value2 = stack.pop();

                switch (element) {
                    case "+":
                        stack.push(value2 + value1);
                        break;
                    case "-":
                        stack.push(value2 - value1);
                        break;
                    case "*":
                        stack.push(value2 * value1);
                        break;
                    case "/":
                        try {
                            stack.push(value2 / value1);
                        } catch (ArithmeticException ex) {
                            System.out.println("Divided by zero!");
                            throw new ArithmeticException();
                        }
                        break;
                    default:
                        System.out.println("Incorrect operator");
                }
            }
        }
        result = stack.pop();
        return result;
    }

    public void print() {
        String format = String.format("The reverse polish notation of the expression is: %n \" %s \" %nThe result of the expression is %d.", rpn1, result);
        System.out.println(format);
    }

}


