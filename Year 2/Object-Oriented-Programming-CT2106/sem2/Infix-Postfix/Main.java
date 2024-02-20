import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String infixExpression; // will hold user input
        boolean valid; // flag which controls while loop

        do {
            infixExpression = JOptionPane.showInputDialog("Please input an infix expression between  3 and  20 Characters"); // input box for user to enter infix expression into
            valid = isValidExpression(infixExpression); // storing validity as a boolean flag
            if (!valid) { // if invalid expression entered shows error message dialog boxes
                JOptionPane.showMessageDialog(null, "Only the following characters are valid +,-,*./,^,(,), and numbers  0-9... Please Try Again");
                infixExpression = JOptionPane.showInputDialog("Please Try Again");
            }
        } while (!valid); // runs till valid input received
        infixExpression = infixExpression.replaceAll(" ", ""); // removes whitespaces from infix expression

        ArrayStack operators = new ArrayStack(); // creates arraystack for the operators
        ArrayList<Character> output = new ArrayList<>(); // Create an ArrayList to hold the output

        pushPopLogic(infixExpression, operators, output); // converting infix to postfix through push/pop logic
        resolvePostfix(output, operators); // resolving the postfix notation into an answer for the expression
        // displaying the postfix and answer of expression through a OptionPane
        JOptionPane.showMessageDialog(null, "The result of the expression is :\nInfix: " + infixExpression + "\nPostfix: " + output + "\nResult: " + operators.top());
    }


    public static void resolvePostfix(ArrayList<Character> output, ArrayStack operands) {
        // Iterate through each character
        for (char ch : output) {
            // if the character is a digit, push its numeric value onto the stack
            if (Character.isDigit(ch)) {
                operands.push((float) Character.getNumericValue(ch));
            } else {
                // ensure there are enough operands on the stack for the operation
                if (operands.size() < 2) {
                    throw new IllegalStateException("Not enough operands for operation");
                }
                // pop the top two elements from the stack
                float num1 = (float) operands.pop();
                float num2 = (float) operands.pop();
                // perform the operation based on the operator and push the result back onto the stack
                switch (ch) {
                    // division operator
                    case '/' -> {
                        if (num2 == 0) throw new ArithmeticException("Division by zero");
                        operands.push(num1 / num2);
                    }
                    // exponentiation operator
                    case '^' -> operands.push((float) Math.pow(num1, num2));
                    // multiplication operator
                    case '*' -> operands.push(num1 * num2);
                    // addition operator
                    case '+' -> operands.push(num1 + num2);
                    // subtraction operator
                    case '-' -> operands.push(num1 - num2);
                    // invalid operator
                    default -> throw new IllegalArgumentException("Invalid operator: " + ch);
                }
            }
            System.out.println(operands.top());
        }
    }


    public static void pushPopLogic(String infixExpression, ArrayStack operators, ArrayList<Character> output) {
        for (int i = 0; i < infixExpression.length(); i++) {
            // Iterate through each character
            char ch = infixExpression.charAt(i);

            // If the character is a digit, add to the output list
            if (Character.isDigit(ch)) { // if number
                output.add(ch);
            }
            // If the character is an opening parenthesis, push it onto the operators stack
            if (ch == '(') { // if (
                operators.push(ch);
            }
            // If the character is a closing parenthesis, pop operators from the stack until an opening parenthesis is found
            else if (ch == ')') { // if )
                // Check if the stack is not empty and opening bracket not on top of stack
                while (!operators.isEmpty() && (Character) operators.top() != '(') { // While the top of the stack is not '('
                    output.add((Character) operators.pop()); // Pop the top element from the stack and add it to the output
                }
                operators.pop(); // Pop the '(' from the stack
            } else if (returnPriority(ch) != -1) { // if operator
                while (!operators.isEmpty() && returnPriority(ch) <= returnPriority((Character) operators.top())) {
                    output.add((Character) operators.pop()); // Pop the top element from the stack and add it to the output
                }
                // Push the current operator onto the stack
                operators.push(ch);
            }
        }
        // After processing the entire expression, pop any remaining operators from the stack and add them to the output
        while (!operators.isEmpty()) {
            output.add((Character) operators.pop());
        }
    }

    public static boolean isValidExpression(String expression) {
        boolean isValid = false; // Flag to track if the expression is valid, initially set to false

        // Loop through each character in the expression
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i); // Get the current character

            // Check if the character is a digit, a parenthesis, or an operator with a known priority
            if (returnPriority(c) != -1 || c == ')' || c == '(' || Character.isDigit(c)) {
                isValid = true; // If it is, set expression flag to true
            } else {
                isValid = false; // Otherwise, set expression flag to false
                break; // Exit the loop early since we found an invalid character
            }
        }
        // If we've gone through the whole loop without finding an invalid character, then the expression is valid
        return isValid;
    }


    public static int returnPriority(char operator) { // returns priority && returns -1 if not a valid operator which is used in expression validation
        return switch (operator) {
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> -1;
        };
    }
}