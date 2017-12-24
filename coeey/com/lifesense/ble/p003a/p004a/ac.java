package com.lifesense.ble.p003a.p004a;

import com.facebook.appevents.AppEventsConstants;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ac {
    private static String f2183a = "edb88320";
    private static String f2184b = "0123456789ABCDEF";

    private static int m1291a(char c, int i) {
        int digit = Character.digit(c, 16);
        if (digit != -1) {
            return digit;
        }
        throw new RuntimeException("Illegal hexadecimal character " + c + " at index " + i);
    }

    public static String m1292a() {
        return Long.toHexString(Calendar.getInstance().getTimeInMillis() / 1000);
    }

    public static String m1293a(int i) {
        if (i <= 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        return stringBuilder.toString();
    }

    public static String m1294a(String str, int i, int i2) {
        return str.substring(i * 2, (i2 + 1) * 2);
    }

    public static String m1295a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 255) < 16) {
                stringBuffer.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            stringBuffer.append(Long.toString((long) (bArr[i] & 255), 16));
        }
        return stringBuffer.toString();
    }

    public static Date m1296a(String str, boolean z) {
        long longValue = Long.valueOf(str, 16).longValue();
        Calendar instance = Calendar.getInstance();
        if (z) {
            instance.setTimeInMillis((System.currentTimeMillis() / 1000) - longValue);
        } else {
            instance.setTimeInMillis(longValue * 1000);
        }
        instance.add(13, -(TimeZone.getDefault().getRawOffset() / 1000));
        return instance.getTime();
    }

    public static byte[] m1297a(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (((byte) (f2184b.indexOf(str.charAt(i * 2)) << 4)) | ((byte) f2184b.indexOf(str.charAt((i * 2) + 1))));
        }
        return bArr;
    }

    public static byte[] m1298a(char[] cArr) {
        int i = 0;
        int length = cArr.length;
        if ((length & 1) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
        byte[] bArr = new byte[(length >> 1)];
        int i2 = 0;
        while (i2 < length) {
            i2++;
            i2++;
            bArr[i] = (byte) (((ac.m1291a(cArr[i2], i2) << 4) | ac.m1291a(cArr[i2], i2)) & 255);
            i++;
        }
        return bArr;
    }
}
