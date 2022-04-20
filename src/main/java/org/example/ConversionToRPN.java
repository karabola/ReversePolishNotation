package org.example;

import java.util.Stack;

public class ConversionToRPN {
    //(1 + 2*(2 /2))
    String rpn = "";

    private Input input;

//    public ConversionToRPN() {
//    }

    public ConversionToRPN(Input input) {
        this.input = input;
    }

    public boolean isNum(char digit) {
        return digit >= '0' && digit - '1' <= 100;
    }

    public boolean isOperator(char sign) {
        return sign == '+' || sign == '-' || sign == '*' || sign == '/' || sign == '(' || sign == ')';
    }

    public String getRpn() {
        return rpn;
    }

    public String convert() {
//        int lengthEx = expression.length();
        int lengthEx = input.getExpression().length();
        Stack<String> reversePolish = new Stack<String>();
        Stack<Character> operator = new Stack<Character>();

        operator.push('0');

        if (input.getExpression().isEmpty()) {
            throw new IllegalArgumentException("Try again and enter an expression");

        } else {
            for (int i = 0; i < lengthEx; /*i++*/) {

                while (i < lengthEx && input.getExpression().charAt(i) == ' ')
                    i++;

                if (i == lengthEx)
                    break;

                if (isNum(input.getExpression().charAt(i))) {
                    String num = "";
                    while (i < lengthEx && isNum(input.getExpression().charAt(i)))
                        num += input.getExpression().charAt(i++);
                    reversePolish.push(num);

                } else if ((isOperator(input.getExpression().charAt(i)))) {
                    char op = input.getExpression().charAt(i);

                    switch (op) {
                        case '(':
                            operator.push(op);
                            break;
                        case ')':
                            while (operator.peek() != '(')
                                reversePolish.push(Character.toString(operator.pop()));
                            operator.pop();
                            break;
                        case '+':
                        case '-':
                            if (operator.peek() == '(')
                                operator.push(op);
                            else {
                                while (operator.peek() != '0' && operator.peek() != '(')
                                    reversePolish.push(Character.toString(operator.pop()));
                                operator.push(op);
                            }
                            break;
                        case '*':
                        case '/':
                            if (operator.peek() == '(')
                                operator.push(op);
                            else {
                                while (operator.peek() != '0' && operator.peek() != '+' &&
                                        operator.peek() != '-' && operator.peek() != '(')
                                    reversePolish.push(Character.toString(operator.pop()));
                                operator.push(op);
                            }
                            break;
                    }
                    i++;
                }
            }
            while (operator.peek() != '0')
                reversePolish.push(Character.toString(operator.pop()));
            while (!reversePolish.isEmpty())
                rpn = rpn.length() == 0 ? reversePolish.pop() + rpn : reversePolish.pop() + " " + rpn;
            return rpn;
        }

    }
}
