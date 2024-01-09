import java.util.Arrays;
import java.util.Random;

public class q3 {

    public static void main(String[] args) {
        long res_randomString;
//        int searchCount = 0;
        int hashCollisions = 0;

        System.out.println("\nStart searching for number of collisions in 10 seconds");

        long startTime = System.currentTimeMillis();  // Get the start time

        while (System.currentTimeMillis() - startTime < 10000) {  // Run the loop for 10 seconds
            String random = randomString(); // get random string generated by method at the bottom. This is done for every iteration
            res_randomString = hashF1(random); // gets an int after hashing the random generated string

//            System.out.println(random + "   " + res_randomString);
//            searchCount++;

            if (res_randomString == 1079524045) { // checks if the output hash matches the Bamb0 hash
                hashCollisions++; // increases counter if the if statement is true
//                System.out.println(random + "   " + res_randomString);
            }
        }

        System.out.println("Number of hash collisions: " + hashCollisions);
//        System.out.println("Number of iterations: " + searchCount);
    }


    private static long hashF1(String s) {
        // Declare a long variable to store the final hash value
        long ret;
        // Declare an integer variable for the loop counter
        int i;
        // Declare a long array to store the intermediate hash values.
        // The hashA array is used to store the intermediate hash values, which are then combined to form the final hash value.
        long[] hashA = new long[]{1, 1, 1, 1};
        String filler, sIn;

        // Define a string of 64 characters to be appended to the input string
        filler = "ABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGH";

        //checks if the input string s is less than 1 or more than 64 characters long. If it is, the function returns -1.
        if ((s.length() > 64) || (s.length() < 1)) {
            ret = -1;
        } else {
            // concatenates filler string to paramter string which is the randomly generated string
            sIn = s + filler;
            sIn = sIn.substring(0, 64); // Limit string to first 64 characters
            for (i = 0; i < sIn.length(); i++) {
                char byPos = sIn.charAt(i); // Get i'th character

                //It gets the ASCII value of the character, multiplies it by a prime number,
                // and adds the result to the corresponding element in the hashA array.
                hashA[0] += (byPos * 17);
                hashA[1] += (byPos * 31);
                hashA[2] += (byPos * 101);
                hashA[3] += (byPos * 79);
            }
            //applies the modulus operation with 2^32-1 to each element in hashA. - the size of an unsigned long is 2^32
            // This operation keeps the hash value within the range of an unsigned long,
            //increases the range of possible hash values, reducing the likelihood of collisions.
            hashA[0] %= (Math.pow(2,32) - 1);
            hashA[1] %= (Math.pow(2,32) - 1);
            hashA[2] %= (Math.pow(2,32) - 1);
            hashA[3] %= (Math.pow(2,32) - 1);

            //calculates the final hash value by summing the products of the elements in hashA and powers of 256.
            ret = (long) (hashA[0] + (hashA[1] * Math.pow((256),1)) + (hashA[2] * Math.pow((256),2)) + (hashA[3] * Math.pow((256),3)));

            if (ret < 0) //checks if the final hash value is negative
                ret *= -1;//multiplies the hash value by -1 to make it positive
        }
        return ret; // returns hash (long)
    }


    private static String randomString() {
        // Define a string of characters to be used in the random string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // Create a new instance of the Random class
        Random random = new Random();
        // Create a new StringBuilder to build the random string
        StringBuilder result = new StringBuilder();
        // Get the length of the characters string
        int charBufferLength = characters.length();

        // Loop 10 times to generate a random string of length 10
        for (int i = 0; i < 10; i++) {
            // Generate a random index within the range of the characters string
            int index = random.nextInt(charBufferLength);
            // Append the character at the random index to the result string
            result.append(characters.charAt(index));
        }

        // Return the random string
        return result.toString();
    }


}