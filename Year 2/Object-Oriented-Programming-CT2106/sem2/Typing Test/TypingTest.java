import java.util.Scanner;

public class TypingTest {

    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner modeInput = new Scanner(System.in);
        System.out.println("Type the alphabet in order (hit enter between letters");

        // Ask the user if they want to go forwards or backwards
        System.out.println("Forwards or backwards (f/b) ?");

        // Read the user's input as a character
        char input = modeInput.next().charAt(0);

        // Use a switch statement to handle the user's input
        switch (input) {
            // If the user entered 'f', call the run function with true as the argument
            case 'f' -> run(true);

            // If the user entered 'b', call the run function with false as the argument
            case 'b' -> run(false);

            // If the user entered anything else, print an error message
            default -> System.out.println("Invalid mode input, restart program!");
        }
    }

    public static void run(boolean mode) {

        // Create a Scanner object to read user input from the console
        Scanner letterInput = new Scanner(System.in);

        // Record the system time at the start of the process
        long startTime = System.nanoTime();

        // Set the starting character based on the mode
        char startChar = mode ? 'a' : 'z';

        // Set the ending character based on the mode
        char endChar = mode ? 'z' : 'a';

        // Print a message to the user indicating character to type to get started on the typing test
        System.out.println("Type " + startChar + " to start");

r       // Loop through the characters from startChar to endChar
        for (int i = (int) startChar; mode ? i <= (int) endChar : i >= (int) endChar; i += mode ? 1 : -1) {
            // Cast the current integer to a character
            char currentChar = (char) i;

            // Read the user's input from the console
            String inputLine = letterInput.next();

            // Check if the length of the input is not equal to 1
            if (inputLine.length() != 1) {
                // If not, print an error message and decrement/increment the counter - based on mode
                System.out.println("Please enter only one character!");
                i -= mode ? 1 : -1;
            } else {
                // Otherwise, get the first character of the input
                char input = inputLine.charAt(0);

                // Compare the user's input with the current character
                if (input == currentChar) {
                    // If they match, check if the current character is the end character
                    if (currentChar == endChar) {
                        // If it is, record the end time and calculate the duration
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime); // Time taken in nanoseconds
                        double durationInSeconds = (double) duration / 1_000_000_000; // Convert to seconds

                        // Print a success message and the time taken
                        System.out.println("Correct! Well Done\n");
                        System.out.println("Time taken: " + durationInSeconds + " s");

                        // Break the loop as the task is completed
                        break;
                    }

                    // If the current character is not the end character, prompt the user to type the next character
                    System.out.println("Correct! Now type: " + (char) ((mode ? currentChar + 1 : currentChar - 1)));

                } else {
                    // If the user's input does not match the current character, print an error message and decrement/increment the counter - based on mode
                    System.out.println("Incorrect! Type " + currentChar);
                    i -= mode ? 1 : -1;
                }
            }
        }
    }

}
