import java.io.*;
import java.util.Objects;

public class Stegano1 {
    /**
     * Constructor for objects of class Stegano1
     */
    public Stegano1() {}

    public static void main(String[] args) throws IOException {
        String arg1, arg2, arg3, arg4; // declared strings to hold value of command line arguments
        boolean err = false; // initially initialized to false and will hold true if error is found

        if (args != null && args.length > 1) { // Check for minimum number of arguments
            // initialised with command line arguments
            arg1 = args[0]; // initialised with command line arguments
            arg2 = args[1];

            if (Objects.equals(arg2, "")) {
                err = true; // error if argument is empty
            } else if ((Objects.equals(arg1, "A")) && (args.length > 3)) {
                // initialised with command line arguments
                arg3 = args[2];
                arg4 = args[3];
                if (Objects.equals(arg3, "") || Objects.equals(arg4, "")) {
                    err = true;  // error if arguments are empty
                } else {
                    // Hide bitstring
                    hide(arg2, arg3, arg4); // calls hide function with command line arguments as parameters
                }
            } else if (Objects.equals(arg1, "E")) {
                // Extract bitstring from text
                retrieve(arg2); // calling retrieve on the inp.txt file - retrieves secret bit message
            } else {
                err = true;
            }
        } else {
            err = true;
        }

        if (err) { // if err was changed to true in the error checks - an error message is displayed
            System.out.println();
            System.out.println("Use: Stegano1 <A:E><Input File><OutputFile><Bitstring>");
            System.out.println("Example: Stegano1 A inp.txt out.txt 0010101");
            System.out.println("Example: Stegano1 E inp.txt");

        }
    }

    static void hide(String inpFile, String outFile, String binString) {
        BufferedReader reader; // Declare a BufferedReader for reading the input file
        BufferedWriter writer; // Declare a BufferedWriter for writing to the output file

        try {
            // Create BufferedReader and BufferedWriter instances
            reader = new BufferedReader(new FileReader(inpFile));
            writer = new BufferedWriter(new FileWriter(outFile));
            String line = reader.readLine(); // Read the first line of the input file
            int binStringIndex = 0; // Initialize an index for the binary string

            // Loop through each line of the input file
            while (line != null) {
                // If the binary string index is within the bounds of the binary string
                if (binStringIndex < binString.length()) {
                    // If the current character of the binary string is '0', append a single space to the line
                    if (binString.charAt(binStringIndex) == '0') {
                        line += " ";
                    }
                    // If the current character of the binary string is '1', append two spaces to the line
                    else if (binString.charAt(binStringIndex) == '1') {
                        line += "  ";
                    }
                }
                binStringIndex++; // Increment the binary string index
                writer.write(line); // Write the current line to the output file
                writer.newLine(); // Write a new line character to the output file
                line = reader.readLine(); // Read the next line of the input file
            }
            // Close the BufferedReader and BufferedWriter instances
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace of the IOException if error is caught in try catch block
        }
    }


    static void retrieve(String inpFile) throws IOException { // This method retrieves a binary string from an input file and prints it as ASCII text
        BufferedReader reader = new BufferedReader(new FileReader(inpFile)); // Declare and initialize a BufferedReader for reading the input file
        String line = reader.readLine(); // Read the first line of the input file
        String binLine = ""; // Initialize a string to hold the binary string

        // Loop through each line of the input file
        while (line != null) {
            // If the line ends with two spaces, append '1' to the binary string
            if (line.endsWith("  ")) {
                binLine += "1";
            }
            // If the line ends with a single space, append '0' to the binary string
            else if (line.endsWith(" ")) {
                binLine += "0";
            } else {
            }
            line = reader.readLine(); // Read the next line of the input file

        }

        // Close the BufferedReader instance
        reader.close();
        // Print the binary string and its corresponding ASCII text
        System.out.println(binLine);
        System.out.println(binaryToAscii(binLine));

    }

    // This method converts a binary string to an ASCII string
    public static String binaryToAscii(String binary) {
        StringBuilder ascii = new StringBuilder(); // Declare a StringBuilder to hold the ASCII string

        // Loop through the binary string eight characters at a time
        for (int i = 0; i < binary.length(); i += 8) {
            int endIndex = Math.min(i + 8, binary.length()); // Calculate the end index of the current substring
            String str = binary.substring(i, endIndex); // Get the current substring of the binary string
            int asciiValue = Integer.parseInt(str, 2); // Convert the current substring from binary to an integer
            ascii.append((char) asciiValue); // Append the ASCII character corresponding to the integer to the StringBuilder
        }
        return ascii.toString(); // Return the ASCII string
    }

}

