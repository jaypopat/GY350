import java.util.ArrayList;

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
public class Q2 {
    public static void main(String[] args) {
        // declare variables
        long res;
        String current;

        // initialise an arraylist of type long to store hash values
        ArrayList<Long> hashes = new ArrayList<Long>();
        hashes.add(895210601874431214L);
        hashes.add(750105908431234638L);
        hashes.add(111111111115664932L);
        hashes.add(977984261343652499L);

        // Define an array of strings to be used as hash chains
        String[] hashchainsArr = {"Kermit12", "Modulus!", "Pigtail1", "GalwayNo", "Trumpets", "HelloPat", "pinky##!", "01!19!56", "aaaaaaaa", "036abgH#"};

        // Loop through each hash chain
        for (String hashchain : hashchainsArr) {

            // Print the start of the hash chain
            System.out.println("Start: " + hashchain);

            // Set the current hash chain
            current = hashchain;

            // Calculate the hash of the current hash chain
            res = hashFunction(current);

            // Check if the calculated hash is in the ArrayList of hashes
            int collisionIndex = hashes.indexOf(res);

            // If the hash is found in the ArrayList
            if (collisionIndex != -1) {
                // Print the found string and the hash chain
                System.out.println("String found for " + hashes.get(collisionIndex) + " : " + current + " in Chain: " + hashchain);


            }
            // for loop runs 10000 times
            for (int i = 0; i < 10000; i++) {
                // apply the reduction function to the current hash with iteration as argument - creating variations of the reduction function
                current = reductionFunction(res, i);

                // calculate the hash of the current chain
                res = hashFunction(current); //check Hash

                // checks if hash is found in the arraylist of hashes
                int index = hashes.indexOf(res);
                if (index != -1) { // if found - prints the string found and the corresponding hash-chain
                    System.out.println("String found for " + hashes.get(index) + " : " + current + " in Chain: " + hashchain);
                }
            }
            // Print the end of the hash chain
            System.out.println("end " + current);
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
