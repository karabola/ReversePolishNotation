package org.example;

import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator {

    private int result;
    private String rpn;

    public Calculator(String rpn) {
        this.rpn = rpn;
    }

    public int count() {

        String[] elements = rpn.split(" ");
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < elements.length; i++) {


            if (elements[i].matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException(
                        "Incorrect argument (only numbers and operators can contain the exception)");
            }
            String operators = "+-*/#";
            if (!operators.contains(elements[i])) {
                stack.push(Integer.valueOf(elements[i]));

            } else if (elements[i].equals("#")) {
                stack.push(0 - (Integer.parseInt(elements[i + 1])));
            } else {
                if (stack.isEmpty()) {
                    System.out.println("First argument must be a number! ");
                    throw new EmptyStackException();
                }


                int value1 = stack.pop();
                int value2 = stack.pop();

                switch (elements[i]) {
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

    result =stack.pop();
                return result;


    }
    public void print() {
        String format = String.format("The reverse polish notation of the expression is: %n \" %s \" %nThe result of the expression is %d.", rpn, result);
        System.out.println(format);
    }
}


