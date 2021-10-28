package org.stroganov.util;

import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Instances of this class can be used concurrently by multiple threads.
 */
public final class PasswordAuthentication {
    /**
     * Each token produced by this class uses this identifier as a prefix.
     */
    public static final String ID = "$31$";

    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    public static final String MISSING_ALGORITHM_MESSAGE = "Missing algorithm: " + ALGORITHM;

    public static final String INVALID_SECRET_KEY_FACTORY = "Invalid SecretKeyFactory";

    public static final String INVALID_TOKEN_FORMAT_MESSAGE = "Invalid token format";

    private static final int SIZE = 128;

    private static final Pattern PATTERN = Pattern.compile("\\$31\\$(\\d\\d?)\\$(.{43})");

    private static final SecureRandom random = new SecureRandom();

    private static final Logger LOGGER = Logger.getLogger(PasswordAuthentication.class);


    private static int iterations(int cost) {
        if ((cost < 0) || (cost > 30))
            throw new IllegalArgumentException("cost: " + cost);
        return 1 << cost;
    }

    /**
     * Hash a password for storage.
     *
     * @return a secure authentication token to be stored for later authentication
     */

    public static String hash(char[] password) {
        int cost = 16;
        byte[] salt = new byte[SIZE / 8];
        random.nextBytes(salt);
        byte[] dk = pbkdf2(password, salt, 1 << cost);
        byte[] hash = new byte[salt.length + dk.length];
        System.arraycopy(salt, 0, hash, 0, salt.length);
        System.arraycopy(dk, 0, hash, salt.length, dk.length);
        Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
        return ID + cost + '$' + enc.encodeToString(hash);
    }

    /**
     * Authenticate with a password and a stored password token.
     *
     * @return true if the password and token match
     */
    public static boolean authenticate(char[] password, String token) {
        Matcher m = PATTERN.matcher(token);
        if (!m.matches())
            throw new IllegalArgumentException(INVALID_TOKEN_FORMAT_MESSAGE);
        int iterations = iterations(Integer.parseInt(m.group(1)));
        byte[] hash = Base64.getUrlDecoder().decode(m.group(2));
        byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
        byte[] check = pbkdf2(password, salt, iterations);
        int zero = 0;
        for (int idx = 0; idx < check.length; ++idx)
            zero |= hash[salt.length + idx] ^ check[idx];
        return zero == 0;
    }

    /**
     * @return return array of hash
     */

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations) {
        KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
            return f.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(MISSING_ALGORITHM_MESSAGE, ex);
            throw new IllegalStateException(MISSING_ALGORITHM_MESSAGE, ex);
        } catch (InvalidKeySpecException ex) {
            LOGGER.error(INVALID_SECRET_KEY_FACTORY);
            throw new IllegalStateException(INVALID_SECRET_KEY_FACTORY, ex);
        }
    }
}


