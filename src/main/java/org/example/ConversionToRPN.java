package org.example;

import java.util.Stack;

public class ConversionToRPN {
    boolean isNumber(char digit) {
        return digit - '0' >= 0 && digit - '0' <= 9;
    }

    boolean isOperator(char sign) {
        return sign == '+' || sign == '-' || sign == '*' || sign == '/' || sign == '(' || sign == ')';
    }

    private boolean isNum(char digit) {
        try {
            Double.parseDouble(String.valueOf(digit));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String convert(String expression) {
        String rpn = "";
        Stack<Character> operatorStack = new Stack<Character>();
        operatorStack.push('0');
        int lengthEx = expression.length();

        for (int i = 0; i < lengthEx; ) {

            char element = expression.charAt(i);
            while (i < lengthEx && element == ' ')
                i++;

            if (i == lengthEx)
                break;

            if (isNumber(element)) {
                if (i < lengthEx) {
                    rpn += expression.charAt(i++);

                }

            } else if ((isOperator(element))) {
                switch (element) {
                    case '(':
                        operatorStack.push(element);
                        break;
                    case ')':
                        if (operatorStack.peek() != '(')
                            rpn += " " + operatorStack.pop();
                        operatorStack.pop();
                        break;
                    case '+':
                        if (operatorStack.peek() == '(')
                            operatorStack.push(element);
                        else {
                            while (operatorStack.peek() != '0' && operatorStack.peek() != '(')
                                rpn += " " + operatorStack.pop();
                            operatorStack.push(element);
                        }
                        rpn += " ";
                        break;
                    case '-':
                        if (rpn.isEmpty() && operatorStack.peek() == '(') {
                            rpn += element;
                        } else if (rpn.isEmpty() && operatorStack.peek() == '0') {
                            rpn += element;
                        } else if (expression.charAt(i - 1) == '(') {
                            rpn += "-" + expression.charAt(i + 1);
                            i++;
                        } else if (!rpn.isEmpty() && operatorStack.peek() == '0') {
                            operatorStack.push(element);
                            rpn += " ";
                        } else if (!rpn.isEmpty() && operatorStack.peek() == '(') {
                            operatorStack.push(element);
                            rpn += " ";
                        }
                        break;
                    case '*':
                    case '/':
                        if (operatorStack.peek() == '(')
                            operatorStack.push(element);
                        else {
                            while (operatorStack.peek() != '0' && operatorStack.peek() != '+' &&
                                    operatorStack.peek() != '-' && operatorStack.peek() != '(')
                                rpn += " " + operatorStack.pop();
                            operatorStack.push(element);
                        }
                        rpn += " ";
                        break;
                }
                i++;
            } else {
                rpn += element;
            }
        }
        while (operatorStack.peek() != '0')
            rpn += " " + operatorStack.pop();

        rpn = rpn.replaceAll("  ", " ");
        return rpn;
    }
}