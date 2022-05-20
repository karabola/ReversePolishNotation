package org.example;

import java.util.Stack;

public class Calculator {

    private int result;
    private String rpnExpression;

    public Calculator(String rpn) {
        this.rpnExpression = rpn;
    }

    public int count() {

        String[] elements = rpnExpression.split(" ");
        Stack<Integer> stack = new Stack();
        String operators = "+-*/";

        for (int i = 0; i < elements.length; i++) {

            if (elements[i].matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Incorrect argument (only numbers and operators can contain the exception)");
            }
            if (!operators.contains(elements[i])) {
                stack.push(Integer.valueOf(elements[i]));
            } else {
                extracted(elements[i], stack);
            }
        }
        return result = stack.pop();
    }

    private void extracted(String element, Stack<Integer> stack) {
        int value1 = stack.pop();
        int value2 = stack.pop();

        int result = extracted(element, value1, value2);
        stack.push(result);
    }

    private int extracted(String element, int value1, int value2) {
        switch (element) {
            case "+":
                result = value2 + value1;
                break;
            case "-":
                result = value2 - value1;
                break;
            case "*":
                result = value2 * value1;
                break;
            case "/":
                try {
                    result = value2 / value1;
                } catch (ArithmeticException ex) {
                    System.out.println("Divided by zero!");
                    throw new ArithmeticException();
                }
                break;
            default:
                System.out.println("Incorrect operator");
        }
        return result;
    }

    public void print() {
        String format = String.format("The reverse polish notation of the expression is: %n \" %s \" %nThe result of the expression is %d.", rpnExpression, result);
        System.out.println(format);
    }
}


