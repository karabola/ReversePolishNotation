package org.example;

import java.util.Stack;

public class ConversionToRPN {
    boolean isNum(char digit) {
        return digit - '0' >= 0 && digit - '0' <= 100;
    }

    boolean isOperator(char sign) {
        return sign == '+' || sign == '-' || sign == '*' || sign == '/' || sign == '(' || sign == ')';
    }

    public String convert(String expression) {
        String rpn = "";
        Stack<String> rpnStack = new Stack<String>();
        Stack<Character> operatorStack = new Stack<Character>();
        operatorStack.push('0');
        int lengthEx = expression.length();

        for (int i = 0; i < lengthEx; ) {
            char element = expression.charAt(i);
            while (i < lengthEx && element == ' ')
                i++;

            if (i == lengthEx)
                break;
            if (isNum(element)) {
                String num = "";
                if (!rpnStack.isEmpty() && rpnStack.peek().contains("-") ) {
                    i++;
                    continue;
                }
                if(i < lengthEx && isNum(expression.charAt(i))) {
                    num += expression.charAt(i++);
                    rpnStack.push(num);
                }
            } else if ((isOperator(element))) {
                switch (element) {
                    case '(':
                        operatorStack.push(element);
                        break;
                    case ')':
                        if (operatorStack.peek() != '(')
                            rpnStack.push(Character.toString(operatorStack.pop()));
                        operatorStack.pop();
                        break;
                    case '+':
                        if (operatorStack.peek() == '(')
                            operatorStack.push(element);
                        else {
                            while (operatorStack.peek() != '0' && operatorStack.peek() != '(')
                                rpnStack.push(Character.toString(operatorStack.pop()));
                            operatorStack.push(element);
                        }
                        break;
                    case '-':
                        if ((expression.charAt(i - 1) == '(') && (isNum(expression.charAt(i + 1)))) {
                            rpnStack.push("-" + (expression.charAt(i + 1)));
                        } else if (operatorStack.peek() == '(')
                            operatorStack.push(element);
                        else {
                            while (operatorStack.peek() != '0' && operatorStack.peek() != '(')
                                rpnStack.push(Character.toString(operatorStack.pop()));
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
                                rpnStack.push(Character.toString(operatorStack.pop()));
                            operatorStack.push(element);
                        }
                        break;
                }
                i++;
            }
        }
        while (operatorStack.peek() != '0')
            rpnStack.push(Character.toString(operatorStack.pop()));
        while (!rpnStack.isEmpty())
            rpn = rpn.length() == 0 ? rpnStack.pop() + rpn : rpnStack.pop() + " " + rpn;
        return rpn;
    }
}