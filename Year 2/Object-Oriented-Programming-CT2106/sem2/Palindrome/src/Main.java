import java.util.function.Function;

public class Main {


    private static int numPalindromes_bothBases = 0;

    private int numOperations_reverseCheck = 0;
    private int numOperations_startEndCharCheck = 0;
    private int numOperations_stackAndQueues = 0;
    private int numOperations_recursiveReverse = 0;

    private int numPalindromes_bothBases_recursiveReverse = 0;

    private static boolean palindromeCounted_bothBases = false;

    public static void main(String[] args) {


        for (int i = 0; i <= 1000000; i++) {
            System.out.println("reverseCheck");
            String binary = decimalToBinary(i);

            timeFunctionCall(binary, String.valueOf(i), Main::reverseCheck);
            timeFunctionCall(binary, String.valueOf(i), Main::startEndCharCheck);
            timeFunctionCall(binary, String.valueOf(i), Main::stackAndQueues);
            timeFunctionCall(binary, String.valueOf(i), Main::recursiveReverse);
        }
    }

    private static void timeFunctionCall(String input, String decimal, Function<String, Boolean> function) {
        System.out.println(function.getClass().getName());

        long startTime = System.currentTimeMillis();
        boolean result_binary = function.apply(input);
        boolean result_decimal = function.apply(decimal);

        if (!palindromeCounted_bothBases) {
            if (result_binary && result_decimal) {
                numPalindromes_bothBases++;
            }
        }
        palindromeCounted_bothBases = true;

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println(function.getClass().getName() + " took " + timeElapsed + "ms");
    }


    public static boolean reverseCheck(String input) {
        String reversedString = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversedString += input.charAt(i);
        }
        return reversedString.equals(input);
    }


    public static boolean startEndCharCheck(String input) {
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j < input.length(); j++) {
                if (input.charAt(i) != input.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean stackAndQueues(String input) {
        ArrayQueue queue = new ArrayQueue();
        ArrayStack stack = new ArrayStack();
        boolean valid = true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            queue.enqueue(c);
            stack.push(c);
        }
        while (!queue.isEmpty() && !stack.isEmpty()) {
            if (queue.dequeue() != stack.pop()) {
                valid = false;
            }
        }
        return valid;
    }

    public static boolean recursiveReverse(String input) {
        return input.equals(reverse(input));
    }

    public static String reverse(String input) {
        if (input.length() <= 1) {
            return input;
        } else {
            return reverse(input.substring(1)) + input.charAt(0);
        }
    }


    public static String decimalToBinary(int num) {
        String binary = "";
        while (num > 0) {
            int remainder = num % 2;
            binary = remainder + binary;
            num /= 2;
        }
        return binary;
    }


}
