package com.andy.security.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.Security;
import java.util.Scanner;

/**
 * 消息摘要算法--MAC
 *
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-01 14:34
 **/
public class MAC {
    /*
    对于HmacMD5、HmacSHA1、HmacSHA256、HmacSHA384、HmacSHA512应用的步骤都是一模一样的
    MAC(Message Authentication Code)，兼容了MD和SHA的特性，并且在它们的基础上加入了密钥。因此MAC也称为HMAC（keyed-Hash Message Authentication Code）含有密钥的散列函数算法。
    MD系列：HmacMD2、HmacMD4、HmacMD5
    SHA系列：HmacSHA1、HmacSHA224、HmacSHA256、HmacSHA384、HmacSHA512
    */
    private static String FINAL_KEY = "a";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要加密的内容:");
        String input = scanner.nextLine();
        String result = MAC.HmacMD2(input);
        System.out.println(result);
    }


    public static String HmacMD2(String content) {

        try {
            // 添加BouncyCastle的支持
            Security.addProvider(new BouncyCastleProvider());
            // 初始化HmacMD5摘要算法的密钥产生器
            KeyGenerator generator = KeyGenerator.getInstance("HmacMD2");
            // 产生密钥
            SecretKey secretKey = generator.generateKey();
            // 获得密钥
            byte[] key = secretKey.getEncoded();
            // 添加BouncyCastle的支持
            Security.addProvider(new BouncyCastleProvider());
            // 还原密钥
            SecretKey secretKeySpec = new SecretKeySpec("a".getBytes(), "HmacMD2");
            // 实例化Mac
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            // 初始化mac
            mac.init(secretKeySpec);
            // 执行消息摘要
            byte[] digest = mac.doFinal(content.getBytes());
            return new HexBinaryAdapter().marshal(digest).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String HmacMD4(String content) {
        try {
            // 添加BouncyCastle的支持
            Security.addProvider(new BouncyCastleProvider());
            // 初始化HmacMD5摘要算法的密钥产生器
            KeyGenerator generator = KeyGenerator.getInstance("HmacMD4");
            // 产生密钥
            SecretKey secretKey = generator.generateKey();
            // 获得密钥
            byte[] key = secretKey.getEncoded();
            // 添加BouncyCastle的支持
            Security.addProvider(new BouncyCastleProvider());
            // 还原密钥
            SecretKey secretKeySpec = new SecretKeySpec("a".getBytes(), "HmacMD4");
            // 实例化Mac
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            // 初始化mac
            mac.init(secretKeySpec);
            // 执行消息摘要
            byte[] digest = mac.doFinal(content.getBytes());
            return new HexBinaryAdapter().marshal(digest).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String HmacMD5(String content) {
        try {
            // 初始化HmacMD5摘要算法的密钥产生器
            KeyGenerator generator = KeyGenerator.getInstance("HmacMD5");
            // 产生密钥
            SecretKey secretKey = generator.generateKey();
            // 获得密钥
            byte[] key = secretKey.getEncoded();
            // 还原密钥
            //SecretKey secretKeySpec = new SecretKeySpec(key, "HmacMD5");
            SecretKey secretKeySpec = new SecretKeySpec(FINAL_KEY.getBytes(), "HmacMD5");
            // 实例化Mac
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            //初始化mac
            mac.init(secretKeySpec);
            //执行消息摘要
            byte[] digest = mac.doFinal(content.getBytes());
            //转为十六进制的字符串
            //System.out.println(Hex.encodeHexString(digest));
            return new HexBinaryAdapter().marshal(digest).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String HmacSHA1(String content) {
        try {
            // 初始化HmacMD5摘要算法的密钥产生器
            KeyGenerator generator = KeyGenerator.getInstance("HmacSHA1");
            // 产生密钥
            SecretKey secretKey = generator.generateKey();
            // 获得密钥
            byte[] key = secretKey.getEncoded();
            System.out.println();
            // 还原密钥
//            SecretKey secretKeySpec = new SecretKeySpec(key, "HmacSHA1");
            SecretKey secretKeySpec = new SecretKeySpec(FINAL_KEY.getBytes(), "HmacSHA1");
            // 实例化Mac
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            //初始化mac
            mac.init(secretKeySpec);
            //执行消息摘要
            byte[] digest = mac.doFinal(content.getBytes());
            return new HexBinaryAdapter().marshal(digest).toLowerCase();//转为十六进制的字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String HmacSHA224(String content) {
        try {
            // 添加BouncyCastle的支持
            Security.addProvider(new BouncyCastleProvider());
            // 添加BouncyCastle的支持
            Security.addProvider(new BouncyCastleProvider());
            // 初始化HmacMD5摘要算法的密钥产生器
            KeyGenerator generator = KeyGenerator.getInstance("HmacSHA224");
            // 产生密钥
            SecretKey secretKey = generator.generateKey();
            // 获得密钥
            byte[] key = secretKey.getEncoded();
            // 添加BouncyCastle的支持
            Security.addProvider(new BouncyCastleProvider());
            // 还原密钥
            SecretKey secretKeySpec = new SecretKeySpec(key, "HmacSHA224");
            // 实例化Mac
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            // 初始化mac
            mac.init(secretKeySpec);
            // 执行消息摘要
            byte[] digest = mac.doFinal(content.getBytes());
            return new HexBinaryAdapter().marshal(digest);// 转为十六进制的字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String HmacSHA256(String content) {
        try {
            // 初始化HmacMD5摘要算法的密钥产生器
            KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
            // 产生密钥
            SecretKey secretKey = generator.generateKey();
            // 获得密钥
            byte[] key = secretKey.getEncoded();
            System.out.println();
            // 还原密钥
//            SecretKey secretKeySpec = new SecretKeySpec(key, "HmacSHA1");
            SecretKey secretKeySpec = new SecretKeySpec(FINAL_KEY.getBytes(), "HmacSHA256");
            // 实例化Mac
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            //初始化mac
            mac.init(secretKeySpec);
            //执行消息摘要
            byte[] digest = mac.doFinal(content.getBytes());
            return new HexBinaryAdapter().marshal(digest).toLowerCase();//转为十六进制的字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String HmacSHA384(String content) {
        try {
            // 初始化HmacMD5摘要算法的密钥产生器
            KeyGenerator generator = KeyGenerator.getInstance("HmacSHA384");
            // 产生密钥
            SecretKey secretKey = generator.generateKey();
            // 获得密钥
            byte[] key = secretKey.getEncoded();
            System.out.println();
            // 还原密钥
//            SecretKey secretKeySpec = new SecretKeySpec(key, "HmacSHA1");
            SecretKey secretKeySpec = new SecretKeySpec(FINAL_KEY.getBytes(), "HmacSHA384");
            // 实例化Mac
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            //初始化mac
            mac.init(secretKeySpec);
            //执行消息摘要
            byte[] digest = mac.doFinal(content.getBytes());
            return new HexBinaryAdapter().marshal(digest).toLowerCase();//转为十六进制的字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String HmacSHA512(String content) {
        try {
            // 初始化HmacMD5摘要算法的密钥产生器
            KeyGenerator generator = KeyGenerator.getInstance("HmacSHA512");
            // 产生密钥
            SecretKey secretKey = generator.generateKey();
            // 获得密钥
            byte[] key = secretKey.getEncoded();
            System.out.println();
            // 还原密钥
//            SecretKey secretKeySpec = new SecretKeySpec(key, "HmacSHA1");
            SecretKey secretKeySpec = new SecretKeySpec(FINAL_KEY.getBytes(), "HmacSHA512");
            // 实例化Mac
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            //初始化mac
            mac.init(secretKeySpec);
            //执行消息摘要
            byte[] digest = mac.doFinal(content.getBytes());
            return new HexBinaryAdapter().marshal(digest).toLowerCase();//转为十六进制的字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
