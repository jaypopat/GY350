/*  CT255 Assignment 2
 *  This class provides functionality to build rainbow tables (with a different reduction function per round) for 8 character long strings, which
    consist of the symbols "a .. z", "A .. Z", "0 .. 9", "!" and "#" (64 symbols in total).
    Properly used, it creates the following value pairs (start value - end value) after 10,000 iterations of hashFunction() and reductionFunction():
          start value  -  end value
          Kermit12        lsXcRAuN
          Modulus!        L2rEsY8h
          Pigtail1        R0NoLf0w
          GalwayNo        9PZjwF5c
          Trumpets        !oeHRZpK
          HelloPat        dkMPG7!U
          pinky##!        eDx58HRq
          01!19!56        vJ90ePjV
          aaaaaaaa        rLtVvpQS
          036abgH#        klQ6IeQJ


 *
 * @author Michael Schukat
 * @version 1.0
 */
public class Q1 {
    public static void main(String[] args) {
        // Declare variables
        int i;
        String plaintext, first;
        // check if there are any command line arguments
        if (args != null && args.length > 0) { // Check for <input> value
            // if true - assigns the argument to a variable => first
            first = args[0];
            // assigns the first argument to the variable => plaintext
            plaintext = first;

            // check if length of argument is 8 chars - if not program exits else the plaintext is hashed and reduced 10000 times in a for loop
            if (first.length() != 8) {
                System.out.println("Input " + first + " must be 8 characters long - Exit");
            } else {
                for (i = 0; i < 10000; i++) { // loops 10000 times
                    long ciphertext = hashFunction(plaintext); // generates hash from plaintext by hashing using the hashFunction() passing in plaintext as a argument
                    plaintext = reductionFunction(ciphertext, i); // reduces the hash generated back to a plaintext by applying reductionFunction() with hash and iterator count(to vary reduction) as argument
                }
                System.out.println(plaintext); // prints plaintext after 10000 iterations of hashing and reducing

            }
        } else { // No <input>
            System.out.println("Use: RainbowTable <Input>"); // prints error message if command line argument wasn't provided
        }
    }

    private static long hashFunction(String s) {
        long ret; // declared long variable which will hold the hash value after all the operations done on the plaintext
        long[] hashA = new long[]{1, 1, 1, 1}; // initialised a long array with all the elements being initialised with 1

        String filler, sIn;

        int DIV = 65536;

        filler = "ABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGH"; // string buffer of 64 chars

        sIn = s + filler; // Add characters, now have "<input>HABCDEF..." // adds plaintext to the buffer
        sIn = sIn.substring(0, 64); // // Limit string to first 64 characters

        for (int i = 0; i < sIn.length(); i++) {
            char byPos = sIn.charAt(i); // get i'th character
            hashA[0] += (byPos * 17111); // Note: A += B means A = A + B
            hashA[1] += (hashA[0] + byPos * 31349);
            hashA[2] += (hashA[1] - byPos * 101302);
            hashA[3] += (byPos * 79001);
            // each array element is mutated - value at each element is added to operations done to the ascii value of the char
            // this occurs for each character of the string formed after limiting the buffer to the first 64 chars
        }

        ret = (hashA[0] + hashA[2]) + (hashA[1] * hashA[3]); // the sum of each of the array elements is assigned to a ret variable
        if (ret < 0) ret *= -1; // if ret is negative - it is converted to a positive by multiplying it with -1
        return ret; // the hashed long is returned
    }

    private static String reductionFunction(long val, int round) {  // Note that for the first function call "round" has to be 0,
        String car, out;                                            // and has to be incremented by one with every subsequent call.
        int i;                                                      // I.e. "round" created variations of the reduction function.
        char dat;

        car = "0123456789ABCDEFGHIJKLMNOPQRSTUNVXYZabcdefghijklmnopqrstuvwxyz!#";
        out = ""; // initialises empty string which is mutated/concatenated with characters in the for loop below

        for (i = 0; i < 8; i++) {
            val -= round; // the hash provided is subtracted by the for loop iteration count
            dat = (char) (val % 63); // // Calculate the remainder of 'val' divided by 63 and cast it to a char.
            val = val / 83; // hash is divided by 83 and reassigned
            out = out + car.charAt(dat); // the string out is mutated/concatenated with a character (dat - calculated by casting the modulo result of hash%63 to a char)  from the string buffer
        }

        return out; // the concatenated string is returned
    }
}
