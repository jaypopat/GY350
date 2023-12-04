import java.math.BigInteger;
import java.util.Objects;

// DiffieHellman class implements the Diffie-Hellman key exchange algorithm
public class DiffieHellman {

    // Private and public keys for the Diffie-Hellman algorithm
    final int privateKey;
    final int publicKey;
    // Prime number used in the Diffie-Hellman algorithm
    public static final int primeNum = generatePrime();

    // Constructor for the DiffieHellman class
    public DiffieHellman(int privateKey) {
        this.privateKey = privateKey;
        // Generate the primitive root of the prime number
        int primitiveRoot = getPrimitiveRoot(primeNum);
        // Generate the public key using the private key and the primitive root
        this.publicKey = (int) Math.pow(primitiveRoot, privateKey) % primeNum;
    }

    // Main method to simulate the Diffie-Hellman key exchange
    public static void main(String[] args) {
        // Create two DiffieHellman objects for Alice and Bob
        DiffieHellman alice = new DiffieHellman(4);
        DiffieHellman bob = new DiffieHellman(3);

        // Generate the secret keys for Alice and Bob
        int aliceBobSecretKeyGenerated = generator(alice.privateKey, bob.publicKey);
        int bobAliceSecretKeyGenerated = generator(bob.privateKey, alice.publicKey);

        // Check if the secret keys match
        System.out.println("secret keys match between alice and bob " + secretKeysMatch(aliceBobSecretKeyGenerated, bobAliceSecretKeyGenerated));
        System.out.println("alice-bob :" + aliceBobSecretKeyGenerated + "," + bobAliceSecretKeyGenerated);

        // Simulate a Man-in-the-Middle (MitM) attack
        DiffieHellman MitM_Mallory = new DiffieHellman(2);

        // Generate the secret keys for Alice and Mallory
        int aliceMallorySecret = generator(alice.privateKey, MitM_Mallory.publicKey);
        int malloryAliceSecret = generator(MitM_Mallory.privateKey, alice.publicKey);
        System.out.println("***** MitM *****\nsecret keys match between alice and mallory " + secretKeysMatch(aliceMallorySecret, malloryAliceSecret));
        System.out.println("alice-mallory :" + aliceMallorySecret + "," + malloryAliceSecret);

        // Generate the secret keys for Bob and Mallory
        int bobMallorySecret = generator(bob.privateKey, MitM_Mallory.publicKey);
        int malloryBobSecret = generator(MitM_Mallory.privateKey, bob.publicKey);
        System.out.println("secret keys match between bob and mallory " + secretKeysMatch(bobMallorySecret, malloryBobSecret));
        System.out.println("bob-mallory :" + bobMallorySecret + "," + malloryBobSecret);
    }

    // Method to check if two keys match
    public static boolean secretKeysMatch(int user1ToUser2, int user2ToUser1) {
        return user1ToUser2 == user2ToUser1;
    }

    // Method to generate a prime number
    public static int generatePrime() {
        // Declare an integer variable to hold the prime number
        int p;
        // Generate a random number between 10^4 and 10^5
        do {
            p = (int) (Math.random() * (100000 - 10000) + 10000);
        } while (!isPrime(p)); // Runs till the randomly generated int is a prime number

        // Return the generated prime number
        return p;
    }


    // Method to check if a number is prime
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Method to check if a number is a primitive root
    public static boolean isPrimitiveRoot(int candidate, int primeNum) {
        // If the GCD of the candidate and the prime number is not 1, then the candidate is not a primitive root
        if (gcd(candidate, primeNum) != 1) {
            return false;
        }

        // Check if the candidate^k (mod primeNum) is not 1 for all k from 1 to primeNum-2
        for (int k = 1; k < primeNum - 1; k++) {
            // Convert the candidate and the prime number to BigInteger for efficient computation
            BigInteger bigCandidate = BigInteger.valueOf(candidate);
            BigInteger bigPrimeNum = BigInteger.valueOf(primeNum);
            // Compute the result of the candidate raised to the power of k modulo the prime number
            BigInteger result = bigCandidate.pow(k).mod(bigPrimeNum);

            // If the result is 1, then the candidate is not a primitive root
            if (Objects.equals(result, BigInteger.ONE)) {
                return false;
            }
        }
        // If no counterexample is found, then the candidate is a primitive root
        return true;
    }

    // Method to calculate the greatest common divisor (GCD) of two numbers
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Method to find the primitive root of a given prime number
    public static int getPrimitiveRoot(int primeNum) {
        // Iterate over all numbers from 2 to primeNum
        for (int g = 2; g < primeNum; g++) {
            // If the current number is a primitive root of the prime number, return it
            if (isPrimitiveRoot(g, primeNum)) {
                return g;
            }
        }
        // If no primitive root is found, return -1
        return -1;
    }

    // Method to generate the secret key using the private key and the public key received
    public static int generator(int privateKey, int publicKeyReceived) {
        return (int) Math.pow(publicKeyReceived, privateKey) % primeNum;
    }
}
