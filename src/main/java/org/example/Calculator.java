package org.example;

import java.util.Stack;

public class Calculator {

    private final static String operators = "+-*/";
    private int result;
    private String rpnExpression;

    public Calculator(String rpn) {
        this.rpnExpression = rpn;
    }

    public int count() {
        String[] elements = rpnExpression.split(" ");
        Stack<Integer> stack = new Stack();

        for (String element : elements) {
            if (element.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Incorrect argument (only numbers and operators can contain the exception)");
            }
            if (!operators.contains(element)) {
                stack.push(Integer.valueOf(element));
            } else {
                extracted(element, stack);
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
                return value2 + value1;
            case "-":
                return value2 - value1;
            case "*":
                return value2 * value1;
            case "/":
                return value2 / value1;
            default:
                throw new IllegalArgumentException("Incorrect operator");
        }
    }

    public void print() {
        String firstSentece = "The reverse polish notation of the expression is:";
        String secondSentence = "The result of the expression is:";
        String format = String.format("%s %n \" %s \" %n %s %d.", firstSentece, rpnExpression, secondSentence, result);
        System.out.println(format);
    }
}


