package RPN;

import java.util.Stack;

public class Calculator {

    public String[] elements;// = a.split(" ");
    protected ConversionToRPN conversionToRPN;
    int result;

    private String rpn1;
    Stack<Integer> stackNum = new Stack();
//      String line1 = "2 1 + 3 *";

    public Calculator(ConversionToRPN conversionToRPN, String rpn) {
        this.conversionToRPN = conversionToRPN;
        this.rpn1 = rpn;
        elements = rpn1.split(" ");
    }

    public int count(String[] elements) {
        for (String element : elements) {

            String operators = "+-*/";
            if (!operators.contains(element)) {
                stackNum.push(Integer.valueOf(element));
            } else {
                int value1 = stackNum.pop();
                int value2 = stackNum.pop();

                switch (element) {
                    case "+":
                        stackNum.push(value2 + value1);
                        break;
                    case "-":
                        stackNum.push(value2 - value1);
                        break;
                    case "*":
                        stackNum.push(value2 * value1);
                        break;
                    case "/":
                        stackNum.push(value2 / value1);
                        break;
                    default:
                        System.out.println("Incorrect operator");
                }
            }
        }
        result = stackNum.pop();
        return result;
    }

    public void print() {
        String format = String.format("The reverse polish notation of the expression is: %n \" %s \" %nThe result of the expression is %d.", rpn1, result);
        System.out.println(format);
    }
}
