package me.xiaoying.secure.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 工具类 字符串加密
 */
public class EncryptUtil {
    /**
     * base64 加密
     *
     * @param str 被加密内容
     * @return 加密内容
     */
    public static String base64Encrypt(String str) {
        byte[] bytes = str.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * base64 解密
     * @param str 被解密内容
     * @return 解密内容
     */
    public static String base64Decrypt(String str) {
        byte[] bytes = str.getBytes();
        return new String(Base64.getDecoder().decode(bytes));
    }

    /**
     * MD5 加密
     *
     * @param str 被加密内容
     * @return 加密内容
     */
    public static String md5Encrypt(String str) {
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (byte b : hash) {
                if ((0xff & b) < 0x10) {
                    hexString.append("0").append(Integer.toHexString((0xFF & b)));
                } else {
                    hexString.append(Integer.toHexString(0xFF & b));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }

    /**
     * SHA256 加密
     *
     * @param str 被加密内容
     * @return 加密内容
     */
    public static String SHA256Encrypt(String str) {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodestr;
    }


    //15 转16进制

    /**
     * 将byte转为16进制
     *
     * @param bytes byte
     * @return String
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp = null;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}