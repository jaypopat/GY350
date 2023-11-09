import java.io.*;
import java.util.Objects;

public class Stegano2 {
    /**
     * Constructor for objects of class Stegano1
     */
    public Stegano2() {
    }

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
        //
        BufferedReader reader;
        BufferedWriter writer;

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
                    String bitsToHide;

                    // Check if there are at least two characters left in the binary string
                    if (binStringIndex + 2 <= binString.length()) {
                        // If there are, extract two bits
                        bitsToHide = binString.substring(binStringIndex, binStringIndex + 2);
                    } else {
                        // If there aren't, extract the remaining characters and pad with '0' on the right
                        bitsToHide = binString.substring(binStringIndex);
                        bitsToHide = String.format("%2s", bitsToHide).replace(' ', '0');
                    }
                    binStringIndex += 2;

                    // Encoding the bits as spaces and appending to the line
                    switch (bitsToHide) {
                        case "00":
                            line += " "; // One space for '00'
                            break;
                        case "01":
                            line += "  "; // Two spaces for '01'
                            break;
                        case "10":
                            line += "   "; // Three spaces for '10'
                            break;
                        case "11":
                            line += "    "; // Four spaces for '11'
                            break;
                    }
                }

                writer.write(line); // Write the current line to the output file
                writer.newLine(); // Write a new line character to the output file
                // Read the next line
                line = reader.readLine();
            }

            // Close the BufferedReader and BufferedWriter instances
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace of the IOException if error is caught
        }

    }


    // This method retrieves a output file as argument and prints the hidden message and the ascii for the binary (implemented binary to ascii - function)
    static void retrieve(String inpFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inpFile));
        String binLine = "";
        String line = reader.readLine();

        // Loop through each line of the input file
        while (line != null) {
            // If the line ends with four spaces, append '11' to the binary string

            if (line.endsWith("    ")) {
                binLine += "11";

            }
            // If the line ends with three spaces, append '10' to the binary string

            else if (line.endsWith("   ")) {
                binLine += "10";
            }
            // If the line ends with two spaces, append '01' to the binary string
            else if (line.endsWith("  ")) {
                binLine += "01";
            }
            // If the line ends with a single space, append '00' to the binary string
            else if (line.endsWith(" ")) {
                binLine += "00";
            } else {
            }
            // Read the next line
            line = reader.readLine();

        }
        // Close the BufferedReader instance
        reader.close();
        // Print the binary string and its corresponding ASCII text

        System.out.println(binLine);
        String ascii = binaryToAscii(binLine);
        System.out.println(ascii);
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

