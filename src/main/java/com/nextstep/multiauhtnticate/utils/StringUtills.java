package com.nextstep.multiauhtnticate.utils;
import java.security.SecureRandom;
import java.util.Random;

public class StringUtills {
    // Character set to be used for generating random strings
    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // SecureRandom instance for generating cryptographically strong random numbers
    private static final SecureRandom random = new SecureRandom();

    /**
     * Generates a random alphanumeric string of the specified length.
     *
     * @param length the length of the random string
     * @return a random alphanumeric string
     * @throws IllegalArgumentException if the specified length is less than or equal to zero
     */
    public static String generateRandomAlphaNumeric(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be a positive integer");
        }

        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // Get a random index from the CHARSET
            int randomIndex = random.nextInt(CHARSET.length());
            // Append the character at the random index to the result
            result.append(CHARSET.charAt(randomIndex));
        }
        return result.toString();
    }

    /**
     * Generates a random alphanumeric string of a default length (5).
     *
     * @return a random alphanumeric string of length 5
     */
    public static String generateRandomAlphaNumeric() {

        return generateRandomAlphaNumeric(5);
    }

}
