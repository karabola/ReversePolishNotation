package org.example;

import java.util.Stack;

public class ConversionToRPN {
    boolean isNumber(char digit) {
        return digit - '0' >= 0 && digit - '0' <= 9;
    }

    boolean isOperator(char sign) {
        return sign == '+' || sign == '-' || sign == '*' || sign == '/' || sign == '(' || sign == ')';
    }

    public String convert(String expression) {
        String rpn = "";
        Stack<Character> operatorStack = new Stack<Character>();
        operatorStack.push('0');
        int lengthEx = expression.length();
        boolean negativeNumber = true;

        for (int i = 0; i < lengthEx; ) {
            char element = expression.charAt(i);

            while (i < lengthEx && element == ' ')
                i++;

            if (i == lengthEx)
                break;

            if (isNumber(element)) {
                if (i < lengthEx && isNumber(element)) {
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
                            /*negativeNumber = true;*/
                        }
                        rpn += " ";
                        break;
                    case '-':
                        if (operatorStack.peek() == '(' && negativeNumber && !rpn.isEmpty()) {
                            operatorStack.push(element);
                            rpn += " ";
                        } else if (operatorStack.peek() == '(' && negativeNumber) {
                            rpn += element;
                        } else if (operatorStack.peek() != '0' && operatorStack.peek() != '(') {
                            operatorStack.push(element);
                            rpn += " ";
                        } else {
                            /*negativeNumber = true;*/
                            rpn += " ";
                            operatorStack.push(element);
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
                        negativeNumber = true;
                        rpn += " ";
                        break;
                }
                i++;
            } else {
                if (/*negativeNumber &&*/ element == '-') {
                    rpn += " ";
                }
                rpn += element;
                negativeNumber = false;
            }
        }
        while (operatorStack.peek() != '0')
            rpn += " " + operatorStack.pop();

        rpn = rpn.replaceAll("  ", " ");
        return rpn;
    }
}