package RPN;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class ConversionToRPN {

    Scanner scan = new Scanner(System.in);
    String expression = scan.nextLine();
//(1 + 2*(2 /2))

    int lengthEx = expression.length();
    String rpn = "";

    public boolean isNum(char digit) {
        return digit >= '0' && digit - '1' <= 100;
    }

    public boolean isOperator(char sign) {
        return sign == '+' || sign == '-' || sign == '*' || sign == '/' || sign == '(' || sign == ')';
    }


    public String getRpn() {
        return rpn;
    }

    public String convert(String expression) {
        Stack<String> reversePolish = new Stack<String>();
        Stack<Character> operator = new Stack<Character>();

        operator.push('0');

        if (expression.isEmpty()) {
        throw new IllegalArgumentException("Try again and enter an expression");

        } else {
            for (int i = 0; i < lengthEx; /*i++*/) {

                while (i < lengthEx && expression.charAt(i) == ' ')
                    i++;

                if (i == lengthEx)
                    break;

                if (isNum(expression.charAt(i))) {
                    String num = "";
                    while (i < lengthEx && isNum(expression.charAt(i)))
                        num += expression.charAt(i++);
                    reversePolish.push(num);

                } else if ((isOperator(expression.charAt(i))) ){
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
        return rpn;
    }

}}
