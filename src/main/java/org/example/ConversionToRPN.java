package org.example;

import java.util.Stack;

public class ConversionToRPN {
    //(1 + 2*(2 /2))
    String rpn = "";

    private Input input;

    public ConversionToRPN(Input input) {
        this.input = input;
    }

    boolean isNum(char digit) {
        /*if (!digit.matches("-?\\d+(\\.\\d+)?")) {
        return false;
    }else {return true;}}*/return digit -'0'>= 0 && digit - '0' <= 100;
    }

    boolean isOperator(char sign) {
        return sign == '+' || sign == '-' || sign == '*' || sign == '/' || sign == '(' || sign == ')';
    }

    public String convert() {

        Stack<String> reversePolish = new Stack<String>();
        Stack<Character> operator = new Stack<Character>();

        operator.push('0');

            final String expression = input.getExpression();
            int lengthEx = expression.length();

            for (int i = 0; i < lengthEx; /*i++*/) {

                while (i < lengthEx && expression.charAt(i) == ' ')
                    i++;
//                if (i < lengthEx && expression.charAt(i) == ' ') {
//                    continue;
//                }
                if (i == lengthEx)
                    break;

                if (isNum(expression.charAt(i))) /*(isNum(expression))*/{
                    String num = "";
                    while (i < lengthEx && isNum(expression.charAt(i)))
                        num += expression.charAt(i++);
                    reversePolish.push(num);

                } else if ((isOperator(expression.charAt(i)))) /*(expression.matches("\\(*+-/\\)"))*/{
                    char op = expression.charAt(i);

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

//        }
        return rpn;
    }
}
