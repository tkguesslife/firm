package lib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import play.Logger;

/**
 * MessageDigestPasswordEncoder uses a message digest algorithm.
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
public class MessageDigestPasswordEncoder {

    private static MessageDigest md;
    private static final String algorithm = "SHA-512";
    private static final int iterations = 5000;

    /**
     * 
     * @param password
     * @param salt
     * @return
     * @throws Exception 
     */
    public static String encodePassword(String password, String salt) throws Exception {
        
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            Logger.error("MessageDigestPasswordEncoder failed to get message digest algorithm: " + algorithm);
            Logger.error(e.getMessage());
        }

        String res = hashPw(password, salt);
        return res;

    }

    /**
     * 
     * @param pw
     * @param salt
     * @return 
     */
    private static String hashPw(String pw, String salt) {
        byte[] bSalt;

        String appendedSalt = new StringBuilder().append(pw).append('{').append(salt).append('}').toString();        
        bSalt = appendedSalt.getBytes();

        byte[] digest = md.digest(bSalt);
        for (int i = 1; i < iterations; i++) {
            digest = run(digest, bSalt);
        }

        return Base64.encodeBase64String(digest);
    }

    /**
     * 
     * @param input
     * @param salt
     * @return 
     */
    private static byte[] run(byte[] input, byte[] salt) {
        byte[] c = new byte[input.length + salt.length];
        System.arraycopy(input, 0, c, 0, input.length);
        System.arraycopy(salt, 0, c, input.length, salt.length);
        return md.digest(c);
    }
}
