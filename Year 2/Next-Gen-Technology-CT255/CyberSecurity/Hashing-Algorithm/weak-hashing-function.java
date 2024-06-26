import java.util.Random;

public class q2 {

    public static void main(String[] args) {
        int res_randomString;
//        int searchCount = 0;
        int hashCollisions = 0;

        System.out.println("\nStart searching for collisions");

        while (hashCollisions<10) {  // Run the loop till first 10 collisions are found
            String random = randomString(); // get random string generated by method at the bottom. This is done for every iteration
            res_randomString = hashF1(random); // gets an int after hashing the random generated string

//            System.out.println(random + "   " + res_randomString);
//            searchCount++;

            if (res_randomString == 1079524045) { // checks if the output hash matches the Bamb0 hash
                hashCollisions++; // increases counter if the if statement is true
                System.out.println(random + "   " + res_randomString);
            }
        }
        //        System.out.println("Number of iterations: " + searchCount);
    }



    private static int hashF1(String s) {
        int ret;
        int i;
        int[] hashA = new int[]{1, 1, 1, 1};

        String filler, sIn;

        filler = "ABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGH";

        if ((s.length() > 64) || (s.length() < 1)) { // String does not have required length
            ret = -1;
        } else {
            sIn = s + filler;
            sIn = sIn.substring(0, 64); // // Limit string to first 64 characters
            //System.out.println(sIn); // FYI
            for (i = 0; i < sIn.length(); i++) {
                char byPos = sIn.charAt(i); // get i'th character
                hashA[0] += (byPos * 17); // Note: A += B means A = A + B
                hashA[1] += (byPos * 31);
                hashA[2] += (byPos * 101);
                hashA[3] += (byPos * 79);
            }

            hashA[0] %= (Math.pow(2,8) - 1);
            hashA[1] %= (Math.pow(2,8) - 1);
            hashA[2] %= (Math.pow(2,8) - 1);
            hashA[3] %= (Math.pow(2,8) - 1);


            ret = (int) (hashA[0] + (hashA[1] * Math.pow((256),1)) + (hashA[2] * Math.pow((256),2)) + (hashA[3] * Math.pow((256),3)));
            if (ret < 0) ret *= -1;
        }
        return ret;
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
