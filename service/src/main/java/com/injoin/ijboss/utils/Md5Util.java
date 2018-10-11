package com.injoin.ijboss.utils;

import java.util.Random;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

/**
 * @author figo
 */
public class Md5Util {

    /**
     * 随机加盐
     */
    public static String randomMD5(String source) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(999999999)).append(r.nextInt(999999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        source = md5Hex(source + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = source.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = source.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }
}
