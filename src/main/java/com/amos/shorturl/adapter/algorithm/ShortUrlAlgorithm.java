package com.amos.shorturl.adapter.algorithm;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

/**
 * DESCRIPTION: 生成短链接算法
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/11/26
 */
public class ShortUrlAlgorithm {

    private static final Integer LENGTH = 6;

    private final static String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String[] shortText(String string) {
        String key = "AMOS";
        // 26 x 2 + 10 = 62
        // 62 ^ 6 = 56,800,235,584 约等于 568亿
        String[] chars = new String[]{
                "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x",
                "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D",
                "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };

        String hex = md5Encode(key + string);
        int hexLen = hex.length();
        int subHexLen = hexLen / 8;
        String[] shortStr = new String[4];

        for (int i = 0; i < subHexLen; i++) {
            StringBuilder outChars = new StringBuilder();
            int j = i + 1;
            String subHex = hex.substring(i * 8, j * 8);
            long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

            for (int k = 0; k < LENGTH; k++) {
                int index = (int) (Long.valueOf("0000003D", 16) & idx);
                outChars.append(chars[index]);
                idx = idx >> 5;
            }
            shortStr[i] = outChars.toString();
        }

        return shortStr;
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte value : b) {
            resultSb.append(byteToHexString(value));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    public static String md5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");

            resultString = resultString.trim();

            resultString = byteArrayToHexString(md.digest(resultString.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception ignored) {
        }
        return resultString;
    }

    public static void main(String[] args) {
        String url = "https://amos.wang/2020/10/29/notes/front/demo/JS监听数组push方法/";

        for (int i = 0; i < 10; i++) {
            String[] strings = shortText(url);
            for (String string : strings) {
                System.out.print(string + "\t");
            }

            System.out.println(" 选一个 >>> " + strings[new Random().nextInt(strings.length)]);
        }

    }

}
