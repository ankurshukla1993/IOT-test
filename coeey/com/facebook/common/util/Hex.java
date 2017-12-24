package com.facebook.common.util;

import humanize.util.Constants;

public class Hex {
    private static final byte[] DIGITS = new byte[103];
    private static final char[] FIRST_CHAR = new char[256];
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] SECOND_CHAR = new char[256];

    static {
        int i;
        byte i2;
        for (i = 0; i < 256; i++) {
            FIRST_CHAR[i] = HEX_DIGITS[(i >> 4) & 15];
            SECOND_CHAR[i] = HEX_DIGITS[i & 15];
        }
        for (i = 0; i <= 70; i++) {
            DIGITS[i] = (byte) -1;
        }
        for (i2 = (byte) 0; i2 < (byte) 10; i2 = (byte) (i2 + 1)) {
            DIGITS[i2 + 48] = i2;
        }
        for (i2 = (byte) 0; i2 < (byte) 6; i2 = (byte) (i2 + 1)) {
            DIGITS[i2 + 65] = (byte) (i2 + 10);
            DIGITS[i2 + 97] = (byte) (i2 + 10);
        }
    }

    public static String byte2Hex(int value) {
        if (value <= 255 && value >= 0) {
            return String.valueOf(FIRST_CHAR[value]) + String.valueOf(SECOND_CHAR[value]);
        }
        throw new IllegalArgumentException("The int converting to hex should be in range 0~255");
    }

    public static String encodeHex(byte[] array, boolean zeroTerminated) {
        char[] cArray = new char[(array.length * 2)];
        int j = 0;
        for (byte b : array) {
            int index = b & 255;
            if (index == 0 && zeroTerminated) {
                break;
            }
            int i = j + 1;
            cArray[j] = FIRST_CHAR[index];
            j = i + 1;
            cArray[i] = SECOND_CHAR[index];
        }
        return new String(cArray, 0, j);
    }

    public static byte[] decodeHex(String hexString) {
        int length = hexString.length();
        if ((length & 1) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        }
        boolean badHex = false;
        byte[] out = new byte[(length >> 1)];
        int i = 0;
        int j = 0;
        while (j < length) {
            int j2 = j + 1;
            int c1 = hexString.charAt(j);
            if (c1 > 102) {
                badHex = true;
                break;
            }
            byte d1 = DIGITS[c1];
            if (d1 == (byte) -1) {
                badHex = true;
                break;
            }
            j = j2 + 1;
            int c2 = hexString.charAt(j2);
            if (c2 > 102) {
                badHex = true;
                j2 = j;
                break;
            }
            byte d2 = DIGITS[c2];
            if (d2 == (byte) -1) {
                badHex = true;
                j2 = j;
                break;
            }
            out[i] = (byte) ((d1 << 4) | d2);
            i++;
        }
        if (!badHex) {
            return out;
        }
        throw new IllegalArgumentException("Invalid hexadecimal digit: " + hexString);
    }

    public static byte[] hexStringToByteArray(String s) {
        return decodeHex(s.replaceAll(Constants.SPACE, ""));
    }
}
