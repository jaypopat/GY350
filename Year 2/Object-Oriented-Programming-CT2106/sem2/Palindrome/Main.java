import java.io.*;
import java.util.function.Function;

public class Main {

    // Counter variables for different operations, initialized to 0
    private static int numOperations_reverseCheck = 0;
    private static int numOperations_startEndCharCheck = 0;
    public static int numOperations_stackAndQueues = 0;
    private static int numOperations_recursiveReverse = 0;
    // Counter for palindromes found in both binary and decimal formats
    private static int numPalindromes_bothBases = 0;
    private static PrintWriter reverseCheckWriter;
    private static PrintWriter startEndCharWriter;
    private static PrintWriter stackAndQueuesWriter;
    private static PrintWriter recursiveWriter;


    public static void main(String[] args) {

        generateOperationCountForInterval(1000000,50000);
    }
    private static void generateOperationCountForInterval(int upperbound, int increment){

        try {


            // Open CSV files for writing
            reverseCheckWriter = new PrintWriter("ReverseCheck.csv");
            startEndCharWriter = new PrintWriter("StartEndChar.csv");
            stackAndQueuesWriter = new PrintWriter("StackAndQueues.csv");
            recursiveWriter = new PrintWriter("Recursive.csv");
            // Loop through the range of numbers from 0 to UPPERBOUND, incrementing by INCREMENT
            for (int i = 0; i <= upperbound; i += increment) {
                // Measure the time taken by each operation and count the number of operations
                double reverseCheckTime = timeFunctionCall(Main::reverseCheck, i);
                double startEndCharTime = timeFunctionCall(Main::startEndCharCheck, i);
                double stackAndQueuesTime = timeFunctionCall(Main::stackAndQueues, i);
                double recursiveTime = timeFunctionCall(Main::recursiveReverse, i);

                // Print the results for the current upper bound
                System.out.println("UPPER BOUND: " + i);
                // this is after the 4 methods hence divided to get true value
                System.out.println("Palindrome in both bases - Count: " + numPalindromes_bothBases / 4);

                // Print and write the results for each operation
                System.out.println("Reverse Check");
                System.out.println(reverseCheckTime + " ms");
                System.out.println(numOperations_reverseCheck + " operations");
                reverseCheckWriter.println(numOperations_reverseCheck);

                System.out.println("Start End Char");
                System.out.println(startEndCharTime + " ms");
                System.out.println(numOperations_startEndCharCheck + " operations");
                startEndCharWriter.println(numOperations_startEndCharCheck);

                System.out.println("Stack and Queues");
                System.out.println(stackAndQueuesTime + " ms");
                System.out.println(numOperations_stackAndQueues + " operations");
                stackAndQueuesWriter.println(numOperations_stackAndQueues);

                System.out.println("Recursive");
                System.out.println(recursiveTime + " ms");
                System.out.println(numOperations_recursiveReverse + " operations");
                recursiveWriter.println(numOperations_recursiveReverse);
                // Reset operation counters for the next iteration
                resetOperationVars();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        // Close the PrintWriter objects
        reverseCheckWriter.close();
        startEndCharWriter.close();
        stackAndQueuesWriter.close();
        recursiveWriter.close();
    }

    // Function to measure the time taken by a given function and count the number of operations
    private static long timeFunctionCall(Function < String, Boolean > function, int upperbound) {
        long startTime = System.currentTimeMillis(); // gets start time

        for (int i = 0; i <= upperbound; i++) {
            String binary = decimalToBinary(i);
            String decimal = String.valueOf(i);

            // calls palindrome checkers on decimal and binary value
            boolean result_binary = function.apply(binary);
            boolean result_decimal = function.apply(decimal);

            if (result_binary && result_decimal) {
                numPalindromes_bothBases++; // increments counter if palindrome occurs in both bases
            }
        }

        long endTime = System.currentTimeMillis(); // gets end time
        long timeElapsed = endTime - startTime; // gets time by subtracting initial and final times
        return timeElapsed;
    }

    // Method to reset operation counters
    public static void resetOperationVars() {
        numOperations_reverseCheck = 0;
        numOperations_startEndCharCheck = 0;
        numOperations_stackAndQueues = 0;
        numOperations_recursiveReverse = 0;
        numPalindromes_bothBases = 0;
    }

    //  checks if palindrome by reversing the string
    public static boolean reverseCheck(String input) {
        String reversedString = ""; // Initialize an empty string to store the reversed string
        numOperations_reverseCheck += 2; // initializing reversedString and the loop variable - 2 operations
        for (int i = input.length() - 1; i >= 0; i--) {
            numOperations_reverseCheck+=2; // initializing i, checking if i is valid, and incrementing i - 2 operations
            reversedString += input.charAt(i); // Append the character to the reversed string
            numOperations_reverseCheck++; // concatenating strings - 1 operation
        }
        boolean equals = reversedString.equals(input); // Compare the reversed string with the original string
        numOperations_reverseCheck++; // comparing strings using equals method - 1 operation

        return equals;
    }

    // checks if palindrome by comparing characters from the start and end
    public static boolean startEndCharCheck(String str) {
        int length = str.length(); // Get the length of the string
        numOperations_startEndCharCheck += 2; //initializing length and the loop variable ( 2 operations)
        for (int i = 0; i < length / 2; i++) {
            numOperations_startEndCharCheck += 3; //initializing i, checking if i is valid, and incrementing i (3 operations)
            if (str.charAt(i) != str.charAt(length - 1 - i)) { // Compare characters from the start and end
                numOperations_startEndCharCheck += 3; // getting characters at different indexes(2) and performing NOT operation(1) - 3 operations
                return false;
            }
        }
        return true;
    }

    // checks if palindrome using stack and queue data structures
    public static boolean stackAndQueues(String input) {
        ArrayQueue queue = new ArrayQueue();
        ArrayStack stack = new ArrayStack();
        boolean valid = true; // Initialize the flag
        numOperations_stackAndQueues++; // initializing the flag

        numOperations_stackAndQueues++;//initializing the loop variable - 1 operation
        for (int i = 0; i < input.length(); i++) {
            numOperations_stackAndQueues += 2; // initializing i, checking if i is valid, and incrementing i
            char c = input.charAt(i); // Get the character at index i
            numOperations_stackAndQueues++; // getting a character - 1 operation
            queue.enqueue(c); // Enqueue the character
            stack.push(c); // Push the character onto the stack
            numOperations_stackAndQueues += 2; // enqueueing and pushing(2)
        }
        while (!queue.isEmpty() && !stack.isEmpty()) {
            numOperations_stackAndQueues += 5; // calling the isEmpty methods(2).Negating them(2).Comparing them(1)
            if (queue.dequeue() != stack.pop()) {
                numOperations_stackAndQueues += 3; // operations: dequeueing, popping, and comparing characters
                valid = false; // Set the flag to false
                numOperations_stackAndQueues++; // operation: setting the flag
            }
        }
        return valid;
    }

    // checks if the result from the recursive calls matches our input
    public static boolean recursiveReverse(String input) {
        numOperations_recursiveReverse++; // 1 operation for initializing the recursive call
        return input.equals(reverse(input)); // Compare the input string with its reversed version
    }

    // recursive method
    public static String reverse(String input) {
        if (input.length() <= 1) { //base case
            numOperations_recursiveReverse++; // checking if the string is empty or of length 1
            return input;
        } else {// recursive call
            numOperations_recursiveReverse += 3; // checking if the string is not empty or of length 1, substring, concatenation, and recursive call
            return reverse(input.substring(1)) + input.charAt(0); // Recursively reverse the string
        }
    }

    // Helper method to convert a decimal number to binary
    public static String decimalToBinary(int num) {
        String binary = ""; // 0/1 will be appended to this
        while (num > 0) {
            int remainder = num % 2; // value either a 0 or 1
            binary = remainder + binary; // value obtained after modulus appended to string
            num /= 2; // divided by 2 for further operations
        }
        return binary;
    }
}
