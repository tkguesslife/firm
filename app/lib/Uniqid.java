/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
public class Uniqid {

    /**
     * *
     * Copy of uniqid in php http://php.net/manual/fr/function.uniqid.php
     *
     * @param prefix
     * @param more_entropy
     * @return
     */
    public static  String uniqid(String prefix, boolean more_entropy) {
        long time = System.currentTimeMillis();
        //String uniqid = String.format("%fd%05f", Math.floor(time),(time-Math.floor(time))*1000000);
        //uniqid = uniqid.substring(0, 13);
        String uniqid = "";
        if (!more_entropy) {
            uniqid = String.format("%s%08x%05x", prefix, time / 1000, time);
        } else {
            SecureRandom sec = new SecureRandom();
            byte[] sbuf = sec.generateSeed(8);
            ByteBuffer bb = ByteBuffer.wrap(sbuf);

            uniqid = String.format("%s%08x%05x", prefix, time / 1000, time);
            uniqid +=String.format("%.8s", "" + bb.getLong());
        }

        return uniqid;
    }

    /**
     * Returns a pseudo-random number between min and max, inclusive. The difference between min and max can be at most <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value. Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

    // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

    // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
