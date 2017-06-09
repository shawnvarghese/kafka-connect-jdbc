package io.confluent.connect.jdbc.source;

/**
 * Created by shawnvarghese on 6/7/17.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataTransform {
    private static final Logger log = LoggerFactory.getLogger(JdbcSourceTask.class);

    public static String transformString(String value) {
        String hashtext = null;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");

            m.update(value.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            hashtext = bigInt.toString(16);

            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }
        } catch (NoSuchAlgorithmException e) {
            log.info("Hashing failed");
            e.printStackTrace();
        }
        return hashtext;
    }
}
