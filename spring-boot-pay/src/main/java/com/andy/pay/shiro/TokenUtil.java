package com.andy.pay.shiro;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * @author Leone
 * @since 2018-08-05
 **/
public class TokenUtil {

    private TokenUtil() {
    }

    private static final HexBinaryAdapter HEX_BINARY_ADAPTER = new HexBinaryAdapter();

    private static final String rule = "$#@*^";

    public static String MD5(String content) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(content.getBytes("UTF-8"));
            byte[] hashCode = messageDigest.digest();
            return HEX_BINARY_ADAPTER.marshal(hashCode).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String encode(String content) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(rule.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            Key key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            return new HexBinaryAdapter().marshal(encodeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode(String content) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(rule.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            Key key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodeResult = cipher.doFinal(DatatypeConverter.parseHexBinary(content));
            return new String(decodeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(encode("he.llo"));
        System.out.println(decode("E34D28E5A0133C86C8E869E68779FD25"));
        System.out.println("6166.token".split("\\.").length);
    }

}
